package co.com.jgs.security.session.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import co.com.jgs.business.service.enums.ServiceReturns;
import co.com.jgs.business.service.output.JGSOuput;
import co.com.jgs.security.session.model.JGSCredentials;
import co.com.jgs.security.session.service.JGSSessionServices;

/**
 * Define el controlador de servicios para la sesion de usuario.
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.0
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |						 COMENTARIOS								 |
 * ***********************************************************************************************************
 * |   1.0   | 03/12/2021 |  OvalleGA   | Creacion del controlador de sesiones.							     |
 * ***********************************************************************************************************
 */
@RestController
@CrossOrigin
public class JGSSessionController {
	
	@Autowired
	JGSSessionServices sessionService;
	
	/**
	 * Permite realizar la autenticacion de un usuario. Si esta es correcta se regresa la sesion de usuario.
	 * @param credenciales JGSCredentials las credenciales de autenticacion.
	 * @return JGSOuput la respuesta que lleva en su interior la sesion de usaurio si la autenticacion fue correcta. 
	 * Los mensajes de retorno y estado de la operacion.
	 */
	@PostMapping("/v1/autenticate")
	public JGSOuput autenticate(@RequestBody JGSCredentials credenciales) {
		try {
			credenciales.setUsername(credenciales!=null && credenciales.getUsername()!=null?credenciales.getUsername().toUpperCase():null);
			return sessionService.autenticateUser(credenciales);
		} catch (NoSuchAlgorithmException e) {
			JGSOuput faliedOperation = new JGSOuput();
			faliedOperation.addMessage(ServiceReturns.UNEXPECTED_ERROR.getCode()+"-"+ServiceReturns.UNEXPECTED_ERROR.getMessage());
			faliedOperation.addMessage(e.getMessage());
			return faliedOperation;
		}
	}
	
	/**
	 * Valida si una sesion de usuario es valida o no.
	 * @param headers Map<String, String> los headers de la peticion.
	 * @return JGSOuput el objeto de retorno con true o false si la sesion es valida o no.
	 */
	@GetMapping("/v1/validateSession")
	public JGSOuput validateSession(@RequestHeader Map<String, String> headers) {
		try {
			return sessionService.validateSession(headers.get("username")!=null?headers.get("username").toUpperCase():null, headers.get("sessionid"));
		} catch (Exception e) {
			JGSOuput faliedOperation = new JGSOuput();
			faliedOperation.addMessage(ServiceReturns.UNEXPECTED_ERROR.getCode()+"-"+ServiceReturns.UNEXPECTED_ERROR.getMessage());
			faliedOperation.addMessage(e.getMessage());
			return faliedOperation;
		}
	}
}
