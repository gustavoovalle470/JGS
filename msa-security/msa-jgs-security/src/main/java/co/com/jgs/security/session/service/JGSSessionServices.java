package co.com.jgs.security.session.service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import co.com.jgs.bo.security.Operations;
import co.com.jgs.bo.security.OperationsPK;
import co.com.jgs.bo.security.Sessions;
import co.com.jgs.bo.security.SessionsPK;
import co.com.jgs.bo.security.Users;
import co.com.jgs.business.service.enums.ServiceReturns;
import co.com.jgs.business.service.exception.JGSServiceException;
import co.com.jgs.business.service.output.JGSOuput;
import co.com.jgs.persistence.DAO.security.SessionsDAO;
import co.com.jgs.persistence.DAO.security.UsersDAO;
import co.com.jgs.persistence.DAO.system.MessagesDAO;
import co.com.jgs.persistence.DAO.system.ModuleConfigurationsDAO;
import co.com.jgs.persistence.DAO.system.OperationsDAO;
import co.com.jgs.security.auditlog.model.JGSAuditData;
import co.com.jgs.security.client.SecurityClient;
import co.com.jgs.security.session.model.JGSCredentials;
import co.com.jgs.security.utils.JGSSecurityUtils;

/**
 * Define los servicios de sesion implementados y validados contra la base de datos.
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.0
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |						 COMENTARIOS								 |
 * ***********************************************************************************************************
 * |   1.0   | 03/12/2021 |  OvalleGA   | Creacion del servicio de sesiones.							     |
 * ***********************************************************************************************************
 */
@Service
public class JGSSessionServices {

	@Autowired
	private UsersDAO userDao;
	@Autowired
	private SessionsDAO sessionDao;
	@Autowired
	private ModuleConfigurationsDAO mcDao;
	@Autowired
	private MessagesDAO messageDao;
	@Autowired
	private OperationsDAO operationDao;
	
	private ConcurrentHashMap<String, Sessions> sessions;
	
	private JGSOuput response; 
	
	@Value("${JGS_HOST}")
	private String host;
	
	@Value("${server.port}")
	private int port;
	
	@Value("${JGS_ID_Service}")
	private int serviceId;
	
	/**
	 * Posterior a la creacion de esta instancia, realiza la carga de las sesiones en base de datos para tenerlas
	 * en cache.
	 */
	@PostConstruct
	public void recoverSessionsInDB() {
		ArrayList<Sessions>recoverySessions = new ArrayList<>();
		sessions = new ConcurrentHashMap<>();
		sessionDao.findAll().forEach(recoverySessions::add);
		for(Sessions session:recoverySessions) {
			sessions.put(session.getSessionsPK().getUsername(), session);
		}
	}
	
	/**
	 * Valida si una sesion dado su id de sesion y usario son validos.
	 * @param username String el usuario dueño de la sesion.
	 * @param sessionId String el id de la sesion.
	 * @return el objeto de retorno con true o false si la sesion es valida o no.
	 */
	public JGSOuput validateSession(String username, String sessionId){
		response = new JGSOuput();
		Sessions session = recoverSession(username, sessionId);
		
		if(session != null) {
			if(Calendar.getInstance().getTime().getTime()-session.getStartIn().getTime()
				       >=Long.parseLong(mcDao.findById(1).get().getValue())) {
				response.addMessage(ServiceReturns.SECURITY_ERROR.getMessageAndCode());
				response.setMessageResponse(messageDao.findById(10).get());
				sessionDao.delete(session);
				sessions.remove(username);
			}else {
				session.setStartIn(Calendar.getInstance().getTime());
				sessionDao.save(session);
				response.setSuccessOperation(true, messageDao.findById(3).get());
			}
		}else {
			response.addMessage(ServiceReturns.SECURITY_ERROR.getMessageAndCode());
			response.setMessageResponse(messageDao.findById(11).get());
		}
		return response;
	}
	
	/**
	 * Devuelve una sesion de usuario, bien sea del cache o de la base de datos.
	 * @param username String el usuario dueño de la sesion.
	 * @param sessionId String el id de la sesion.
	 * @return Session la sesion de usuario, si es encontrada, null en caso contrario.
	 */
	private Sessions recoverSession(String username, String sessionId) {
		if(sessions.contains(username)) {
			if(sessions.get(username).getSessionsPK().getSessionId().equals(sessionId)) {
				return sessions.get(username);
			}
		}else {
			SessionsPK sessionPk = new SessionsPK(username, sessionId);
			if(sessionDao.existsById(sessionPk)) {
				Sessions session = sessionDao.findById(sessionPk).get();
				sessions.put(session.getSessionsPK().getUsername(), session);
				return session;
			}
		}
		return null;
	}

	/**
	 * Realiza la auenticacion de un usuario.
	 * @param credentials JGSCredentials las credenciales del usuario.
	 * @return El objeto de retorno con la sesion de usuario si la operacion es finalizada con exito, null en caso contrario.
	 * @throws NoSuchAlgorithmException si:
	 * <ol><li>No se encuentra el algoritmo de cifrado de contraseña.</li></ol>
	 */
	public JGSOuput autenticateUser(JGSCredentials credentials) throws NoSuchAlgorithmException {
		response = new JGSOuput();
		validateInput(credentials);
		Users user=validateCredentials(credentials);
		if( response.getErrorMessages().isEmpty() && user != null) {
			Sessions session = sessionDao.findByUsername(user.getUsername());
			if(session == null) {
				session=generateSession(user);
				sessions.put(session.getSessionsPK().getUsername(), session);
				response.setSuccessOperation(session, messageDao.findById(2).get());
			}else {
				if(user.getUserStatus().getCatalogsPK().getItemId() == 1) {
					if((validateSession(user.getUsername(), session.getSessionsPK().getSessionId())).isSuccess()) {
						response.setSuccessOperation(session, messageDao.findById(2).get());
					}else {
						sessions.remove(user.getUsername());
						autenticateUser(credentials);
					}
				}else {
					response.addMessage(ServiceReturns.SECURITY_ERROR.getMessageAndCode());
					response.setMessageResponse(messageDao.findById(12).get());
				}
			}
		}
		auditOperation(1, credentials.toString(), response, user);
		return response;
	}

	private void auditOperation(int operationId, String inputs, JGSOuput output, Users user) {
		SecurityClient securityClient = new SecurityClient(host+":"+port);
		OperationsPK operationPK = new OperationsPK(serviceId, operationId);
		if(operationDao.existsById(operationPK)) {
			Operations operation = operationDao.findById(new OperationsPK(serviceId, operationId)).get();
			JGSAuditData data = new JGSAuditData();
			data.setInput(inputs);
			data.setOperation(operation);
			data.setOutput(output);
			data.setUser(user);
			try {
				securityClient.registryAudit(data);
			} catch (JGSServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	/**
	 * Genera una sesion para un usuario.
	 * @param user Users el usuario dueño de la sesion.
	 * @return Session la sesion de usuario ya persistida en base de datos.
	 */
	private Sessions generateSession(Users user) {
		String sessionId="";
		boolean isAvailableSessionId = false;
		while(!isAvailableSessionId) {
			sessionId = JGSSecurityUtils.getAlphaNumericString(450);
			isAvailableSessionId=sessionDao.findBySessionId(sessionId)==null;
		}
		Sessions session = new Sessions();
		session.setSessionsPK(new SessionsPK(user.getUsername(), sessionId));
		session.setStartIn(Calendar.getInstance().getTime());
		session.setUsers(user);
		sessionDao.save(session);
		return session;
	}

	/**
	 * Valida si las credenciales proporcionadas son validas.
	 * @param credentials JGSCredentials las credenciales de usuario.
	 * @return Users el usuario dueño de las credenciales si estas son validas, null en caso contrario.
	 * @throws NoSuchAlgorithmException si:
	 * <ol><li>No se encuentra el algoritmo de cifrado de contraseña.</li></ol>
	 */
	private Users validateCredentials(JGSCredentials credentials) throws NoSuchAlgorithmException {
		Users user = null;
		if(userDao.existsById(credentials.getUsername())) {
			user= userDao.findById(credentials.getUsername()).get();
			if(!user.getPassword().equals(JGSSecurityUtils.encryptPwd(credentials.getPassword()))) {
				response.addMessage(ServiceReturns.EXPECTED_ERROR.getMessageAndCode());
				user = null;
				response.setMessageResponse(messageDao.findById(4).get());
			}else if(user.getUserStatus().getCatalogsPK().getItemId() == 3 
				   ||user.getUserStatus().getCatalogsPK().getItemId() == 4 ) {
				response.addMessage(ServiceReturns.EXPECTED_ERROR.getMessageAndCode());
				user = null;
				response.setMessageResponse(messageDao.findById(5).get());
			}
		}else {
			response.addMessage(ServiceReturns.DATA_NOT_FOUND.getMessageAndCode());
			response.setMessageResponse(messageDao.findById(6).get());
		}
		return user;
	}

	/**
	 * Valida si la entrada de credenciales es valida.
	 * @param credentials JGSCredentials las credenciales a validar.
	 */
	private void validateInput(JGSCredentials credentials) {
		if(credentials == null) {
			response.addMessage(ServiceReturns.DATA_NOT_FOUND.getMessageAndCode());
			response.setMessageResponse(messageDao.findById(7).get());
		}else if(credentials.getUsername() == null || credentials.getUsername().equals("")) {
			response.addMessage(ServiceReturns.DATA_NOT_FOUND.getMessageAndCode());
			response.setMessageResponse(messageDao.findById(8).get());
		}else if(credentials.getPassword() == null || credentials.getPassword().equals("")) {
			response.addMessage(ServiceReturns.DATA_NOT_FOUND.getMessageAndCode());
			response.setMessageResponse(messageDao.findById(9).get());
		}
	}
}
