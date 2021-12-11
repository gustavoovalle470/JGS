package co.com.jgs.security.auditlog.model;

import co.com.jgs.bo.security.Operations;
import co.com.jgs.bo.security.Users;
import co.com.jgs.business.service.output.JGSOuput;

/**
 * Define la data minima para lograr auditar una operacion..
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.0
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |						 COMENTARIOS								 |
 * ***********************************************************************************************************
 * |   1.0   | 03/12/2021 |  OvalleGA   | Creacion la definicion de datos para auditoria.				     |
 * ***********************************************************************************************************
 */
public class JGSAuditData {
	
	private Operations operation;
	private JGSOuput output;
	private String input;
	private Users user;
	
	/**
	 * Retorna la operacion que se esta auditando.
	 * @return Operations la operacion a audtiar.
	 */
	public Operations getOperation() {
		return operation;
	}
	
	/**
	 * Cambia la operacion que se esta auditando.
	 * @param operation Operations la operacion a auditar.
	 */
	public void setOperation(Operations operation) {
		this.operation = operation;
	}
	
	/**
	 * Retorna el output de la operacion que contiene el estado de la misma.
	 * @return JGSOuput la salida de la operacion.
	 */
	public JGSOuput getOutput() {
		return output;
	}
	
	/**
	 * Cambia la salida de la operacion.
	 * @param output JGSOuput la salida de la operacion.
	 */
	public void setOutput(JGSOuput output) {
		this.output = output;
	}

	/**
	 * Regresa los datos de entrada de la operacion.
	 * @return String los datos de entrada.
	 */
	public String getInput() {
		return input;
	}

	/**
	 * Cambia los datos de entrada de la operacion.
	 * @param input JGSInput los datos de entrada.
	 */
	public void setInput(String input) {
		this.input = input;
	}

	/**
	 * Regresa el usuario que invoco la operacion.
	 * @return Users el usuario que invoco la operacion.
	 */
	public Users getUser() {
		return user;
	}

	/**
	 * Cambia el usuario que invoco la operacion
	 * @param user Users el nuevo usuario.
	 */
	public void setUser(Users user) {
		this.user = user;
	}
	
}
