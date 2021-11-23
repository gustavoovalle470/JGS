/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jgs.base.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 *
 * @author gusta
 */
public class JGSSecurityUtils {
    public static String encryptPwd(String pwd) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance(MessageDigestAlgorithms.SHA_512);
        md.update(pwd.getBytes());
        byte[] digest = md.digest();
        byte[] encoded = Base64.encodeBase64(digest);
        return new String(encoded);
    }
    
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
    
    public static String getJWTToken(String username, int expritation) {
		String secretKey = "JGSSecure";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("JGSSecure")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expritation))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

                return "Bearer " + token;
	}
}