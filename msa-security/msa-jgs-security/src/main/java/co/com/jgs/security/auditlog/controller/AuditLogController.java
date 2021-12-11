package co.com.jgs.security.auditlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import co.com.jgs.business.service.enums.ServiceReturns;
import co.com.jgs.business.service.output.JGSOuput;
import co.com.jgs.security.auditlog.model.JGSAuditData;
import co.com.jgs.security.auditlog.service.JSGAuditService;

/**
 * Define el controlador de auditoria que generara los logs de utilizacion de operaciones auditables.
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.0
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |						 COMENTARIOS								 |
 * ***********************************************************************************************************
 * |   1.0   | 03/12/2021 |  OvalleGA   | Creacion del controlador de auditoria.						     |
 * ***********************************************************************************************************
 */
@RestController
@CrossOrigin
public class AuditLogController {
	@Autowired
	JSGAuditService auditService;
	
	@PostMapping("/v1/register-audit")
	public JGSOuput registerAudit(@RequestBody JGSAuditData auditData) {
		try {
			return auditService.registerAudit(auditData);
		} catch (Exception e) {
			JGSOuput faliedOperation = new JGSOuput();
			faliedOperation.addMessage(ServiceReturns.UNEXPECTED_ERROR.getCode()+"-"+ServiceReturns.UNEXPECTED_ERROR.getMessage());
			faliedOperation.addMessage(e.getMessage());
			return faliedOperation;
		}
	}
}
