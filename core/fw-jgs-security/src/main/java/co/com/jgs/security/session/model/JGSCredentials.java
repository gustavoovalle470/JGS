package co.com.jgs.security.session.model;

import co.com.jgs.business.service.input.JGSInput;

/**
 * Define las credenciales que se requiren para autenticarse ante el sistema.
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.0
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |						 COMENTARIOS								 |
 * ***********************************************************************************************************
 * |   1.0   | 03/12/2021 |  OvalleGA   | Creacion la definicion de credenciales.						     |
 * ***********************************************************************************************************
 */
public class JGSCredentials extends JGSInput{
	private String username;
	private String password;
	
	/**
	 * Regresa el username proporcionado.
	 * @return String el username.
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Modifica el username proporcionado.
	 * @param username String el nuevo username.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Regresa la contraseña de esta autenticacion.
	 * @return String la contraseña.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Cambia la contraseña de esta autenticacion.
	 * @param password String la nueva contraseña.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Username: "+getUsername()+", Password: [PASSWORD]";
	}
}
