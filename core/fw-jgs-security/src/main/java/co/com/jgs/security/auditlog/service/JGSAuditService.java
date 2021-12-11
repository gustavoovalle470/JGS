package co.com.jgs.security.auditlog.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.jgs.bo.security.AuditLogs;
import co.com.jgs.business.service.output.JGSOuput;
import co.com.jgs.persistence.DAO.security.AuditLogDAO;
import co.com.jgs.persistence.DAO.system.MessagesDAO;
import co.com.jgs.security.auditlog.model.JGSAuditData;

/**
 * Define los servicios de auditoria.
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.0
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |						 COMENTARIOS								 |
 * ***********************************************************************************************************
 * |   1.0   | 03/12/2021 |  OvalleGA   | Creacion del servicio de auditoria.							     |
 * ***********************************************************************************************************
 */
@Service
public class JGSAuditService {

	@Autowired
	private AuditLogDAO auditlogDao;
	@Autowired
	private MessagesDAO messageDao;
	private JGSOuput response;
	
	/**
	 * Crea una nueva entrada de auditoria de una operacion.
	 * @param auditData JGSAuditData los datos de auditoria.
	 * @return JGSOuput el estado de la oepracion.
	 */
	public JGSOuput registerAudit(JGSAuditData auditData) {
		response = new JGSOuput();
		AuditLogs auditLog = new AuditLogs();
		auditLog.setMethodInvoked(auditData.getOperation().getName());
		auditLog.setOperationDate(Calendar.getInstance().getTime());
		auditLog.setParametersInvoked(auditData.getInput()!=null?auditData.getInput().toString():"Sin datos de entrada.");
		auditLog.setResponseMessage(auditData.getOutput().getMessageResponse()!=null?auditData.getOutput().getMessageResponse().getMessage():"Sin mensaje de respuesta.");
		auditLog.setSuccesOperation(""+auditData.getOutput().isSuccess());
		auditLog.setUsername(auditData.getUser());
		auditLog.setWsinvoked(auditData.getOperation().getPath());
		auditlogDao.save(auditLog);
		response.setSuccessOperation(auditLog, messageDao.findById(13).get());
		return response;
	}

}
