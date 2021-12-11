/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jgs.security.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/**
 * Define algunos utilitarios de seguridad.
 * @author Gustavo Adolfo Ovalle Quintero
 * @version 1.0
 * ***********************************************************************************************************
 * **************************************** CONTROL DE VERSIONES *********************************************
 * ***********************************************************************************************************
 * | VERSION |   FECHA    |    AUTOR    |						 COMENTARIOS								 |
 * ***********************************************************************************************************
 * |   1.0   | 03/12/2021 |  OvalleGA   | Creacion de los utilitarios de seguridad.						     |
 * ***********************************************************************************************************
 */
public class JGSSecurityUtils {
	
	/**
	 * Realiza el cifrado de un string.
	 * @param pwd la cadena de texto a cifrar.
	 * @return String la cadena de texto cifrada.
	 * @throws NoSuchAlgorithmException si:
	 * <ol><li>No se encuentra el algoritmo de cifrado de contraseña.</li></ol>
	 */
    public static String encryptPwd(String pwd) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance(MessageDigestAlgorithms.SHA_512);
        md.update(pwd.getBytes());
        byte[] digest = md.digest();
        byte[] encoded = Base64.encodeBase64(digest);
        return new String(encoded);
    }
    
    /**
     * Generador aleatorio de cadenas de texto de longitud deifnida.
     * @param n int la longitud de la cadena de texto a generar.
     * @return String la cadena de texto aleatoria generada.
     */
    public static String getAlphaNumericString(int n){
        String AlphaNumericString = "abcdefghijklmnopqrstuvxyzABCDEFGHIJKLMNOPQRSTUVXYZ";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());
            sb.append(AlphaNumericString
                          .charAt(index));
        }
        return sb.toString();
    }
}