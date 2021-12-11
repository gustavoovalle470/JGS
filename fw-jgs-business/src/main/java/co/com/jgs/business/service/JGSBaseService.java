package co.com.jgs.business.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.jgs.bo.security.OperationsPK;
import co.com.jgs.bo.security.Services;
import co.com.jgs.bo.subscription.Licenses;
import co.com.jgs.bo.system.Messages;
import co.com.jgs.business.service.enums.ServiceReturns;
import co.com.jgs.business.service.exception.JGSServiceException;
import co.com.jgs.business.service.input.JGSInput;
import co.com.jgs.business.service.output.JGSOuput;
import co.com.jgs.persistence.DAO.security.UsersDAO;
import co.com.jgs.persistence.DAO.subscription.LicensesDAO;
import co.com.jgs.persistence.DAO.system.MessagesDAO;
import co.com.jgs.persistence.DAO.system.OperationsDAO;
import co.com.jgs.persistence.DAO.system.ServicesDAO;
import co.com.jgs.security.auditlog.model.JGSAuditData;
import co.com.jgs.security.client.SecurityClient;
import java.io.IOException;

/**
 * Define el flujo base de un micro servicio en el aplicativo JGS.
 * @author Gustavo Adolfo Ovalle Quintero
 * @param <IN> Tipo de entrada que extiende de JGSInput
 * @param <OUT> Tipo de salida que extiende de JGSOutput.
 * @version 1.0
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |						 COMENTARIOS								 |
 * ***********************************************************************************************************
 * |   1.0   | 02/12/2021 |  OvalleGA   | Definicion inicial flujos de WS, entradas y salidas genericas      |
 * ***********************************************************************************************************
 */
public abstract class JGSBaseService <IN extends JGSInput, OUT extends JGSOuput> {
	
	private static final Logger log = LoggerFactory.getLogger(JGSBaseService.class);
	@Autowired
	private ServicesDAO serviceDao;
	@Autowired
	private OperationsDAO operationDao;
	@Autowired
	private UsersDAO userDao;
        private SecurityClient securityClient;
        @Autowired        
        protected MessagesDAO messageDao;
        @Autowired
	private LicensesDAO licenseDao;
	protected JGSContext context;
	protected List<String> returnMessages;
        
	/**
	 * Devuelve el ID del servicio.
	 * @return Integer el ID del servicio.
	 */
	public abstract Integer getServiceID();
	
	/**
	 * Devuelve el ID de la operacion.
	 * @return OperationPK el ID de la operacion.
	 */
	public abstract OperationsPK getOperationID();
	
        public abstract String getURLServer();
        
	/**
	 * Ejecuta el flujo de un servicio REST en el aplicativo.
	 * @param input IN entrada del servicio que extiende de JGSInput
	 * @return OUT salida del servicio que extiende de JGSOutput
	 * @throws JGSServiceException si:
	 * <li>
	 * 	<ol>La sesion de usuario no es valida.</ol>
	 * 	<ol>La operacion y/o el servicio no estan registrados.</ol>
	 * 	<ol>La licencia del usuario no permite la operacion.</ol>
	 * </li>
	 */	
	public OUT executeService(IN input) throws JGSServiceException {
            returnMessages = returnMessages=new ArrayList<>();
            securityClient = new SecurityClient(getURLServer());
            context = beforeExecute(input);
            if(context!= null && returnMessages.isEmpty()){
                OUT output = execute(input);
                postExecute(input.toString(), output);
                return output;
            }else{
                return failedInvocationExit();
            }
	}

	/**
	 * Lanza los pasos previos a la ejecucion del servicio invocado.
	 * @param input IN la entrada el servicio que extiende de JGSInput.
	 * @return JGSContext El contexto de seguridad del servicio invocado.
	 * @throws JGSServiceException si:
	 * <li>
	 * 	<ol>La sesion de usuario no es valida.</ol>
	 * 	<ol>La operacion y/o el servicio no estan registrados.</ol>
	 * 	<ol>La licencia del usuario no permite la operacion.</ol>
	 * </li>
	 */
	private JGSContext beforeExecute(IN input) {
            validateSession(input.getUsername(), input.getSessionId());
            if(returnMessages.isEmpty()) {
                JGSContext serviceContext = new JGSContext();
                if(serviceDao.existsById(getServiceID()) && operationDao.existsById(getOperationID())) {
                    serviceContext.setService(serviceDao.findById(getServiceID()).get());
                    serviceContext.setOperation(operationDao.findById(getOperationID()).get());
                    serviceContext.setUser(userDao.findById(input.getUsername()).get());
                    if(validateLicenseAccess(input.getLicense(), serviceContext.getService())) {
                            return serviceContext;
                    }else {
                        Messages message = messageDao.findById(15).get();
                        message.replaceData(input.getLicense()
                                            +","+getOperationID().getId()
                                            +","+getServiceID());
                        returnMessages.add(ServiceReturns.LICENSE_ERROR.getMessageAndCode());
                        returnMessages.add(message.getMessage());
                    }
                }else {
                    Messages message = messageDao.findById(16).get();
                    message.replaceData(getServiceID()
                                        +","+getOperationID().getId());
                    returnMessages.add(ServiceReturns.LICENSE_ERROR.getMessageAndCode());
                    returnMessages.add(message.getMessage());
                }
            }
            return null;
	}
	
	/**
	 * Valida los input del servicio y determina si el proceso puede continuar o no.
	 * @param input IN la entrada el servicio que extiende de JGSInput.
	 * @return true si la validacion fue correcta, false en caso contrario.
	 */
	protected abstract boolean validateInput(IN input);
	
	/**
	 * Ejecuta las acciones a realizar en el servicio.
	 * @param input IN la entrada el servicio que extiende de JGSInput.
	 * @return OUT salida del servicio que extiende de JGSOutput
	 */
	protected abstract OUT execute(IN input);

	/**
	 * Ejecuta todas las acciones que se realizan despues de ejecutar el servicio.
	 * @param context JGSContext el contexto de seguridad del servicio.
	 * @param output salida del servicio que extiende de JGSOutput
	 */
	private void postExecute(String inputs, OUT output) {
            System.out.println("Validando para la pos ejecucion: ");
            System.out.println("Context operation: "+context.getOperation().getName());
            System.out.println("Auditable?: "+context.getOperation().getAuditable());
            if(context.getOperation().getAuditable() == 1) {
                try {
                    JGSAuditData data = new JGSAuditData();
                    data.setInput(inputs);
                    data.setOperation(context.getOperation());
                    data.setOutput(output);
                    data.setUser(context.getUser());
                    JGSOuput out = securityClient.registryAudit(data);
                    System.out.println("Se registro auditoria? "+out.isSuccess());
                    for(String mensaje: out.getErrorMessages()){
                        System.out.println("MENSAJE: "+mensaje);
                    }
                    
                } catch (JGSServiceException e) {
                    log.error(e.getMessage());
                }
            }
	}
        
        public boolean validateSession(String username, String sessionId){
            try {
                JGSOuput response = securityClient.validateSession(username, sessionId);
                if(response.isSuccess()){
                    return (boolean) securityClient.validateSession(username, sessionId).getObjectToReturn();
                }else{
                    returnMessages = response.getErrorMessages();
                    returnMessages.add(response.getMessageResponse().getMessage());
                    return false;
                }
            } catch (JGSServiceException | IOException e) {
                log.error(e.getMessage());
                return false;
            }
	}

    public abstract OUT failedInvocationExit();
    
    private boolean validateLicenseAccess(String licenseId, Services service) {
        if(licenseDao.existsById(licenseId)) {
            Licenses license = licenseDao.findById(licenseId).get();
            if (license.getModules().stream().anyMatch(module -> (service.getModuleId().equals(module)))) {
                return true;
            }
        }
        return false;
    }
}
