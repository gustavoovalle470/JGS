package co.com.jgs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Define la aplicacion SpringBoot a desplegar.
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.0
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |						 COMENTARIOS								 |
 * ***********************************************************************************************************
 * |   1.0   | 03/12/2021 |  OvalleGA   | Creacion la aplicacion de seguridad.		.					     |
 * ***********************************************************************************************************
 */
@SpringBootApplication
public class MsaJgssecurityApplication {

	/**
	 * Inicia los servicios de seguridad.
	 * @param args los argumentos.
	 */
	public static void main(String[] args) {
		SpringApplication.run(MsaJgssecurityApplication.class, args);
	}

}
