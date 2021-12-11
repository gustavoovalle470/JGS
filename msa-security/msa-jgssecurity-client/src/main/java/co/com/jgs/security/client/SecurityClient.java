package co.com.jgs.security.client;


import java.io.IOException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.jgs.bo.security.Sessions;
import co.com.jgs.business.service.exception.JGSServiceException;
import co.com.jgs.business.service.output.JGSOuput;
import co.com.jgs.security.auditlog.model.JGSAuditData;
import co.com.jgs.security.session.model.JGSCredentials;

public class SecurityClient {
	
	private ObjectMapper mapper = new ObjectMapper();
	private String host;
	private RestTemplate client = new RestTemplate();
	
	public SecurityClient(String host) {
		this.host = host;
	}
	
	public JGSOuput autenticate(String username, String password) throws JGSServiceException, IOException {
		if(host != null && !host.equals("")) {
			JGSCredentials credencial = new JGSCredentials();
			credencial.setUsername(username);
			credencial.setPassword(password);
			HttpHeaders headers = new HttpHeaders();
			HttpEntity<JGSCredentials> entity = new HttpEntity<>(credencial, headers);
			JGSOuput response = client.exchange(host+"/JGS/API/security/v1/autenticate", HttpMethod.POST, entity, JGSOuput.class).getBody();
			byte[] json = mapper.writeValueAsBytes(response.getObjectToReturn());
			Sessions session = mapper.readValue(json, Sessions.class);
			response.setObjectToReturn(session);
			return response; 
		}
		throw new JGSServiceException("No se ha configurado un host para este servicio. Service: "+this.getClass().getCanonicalName());
	}
	
	public JGSOuput validateSession(String username, String sessionId) throws JGSServiceException, IOException {
		if(host != null && !host.equals("")) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("username", username);
			headers.add("sessionid", sessionId);
			HttpEntity<Void> entity = new HttpEntity<>(headers);
			return client.exchange(host+"/JGS/API/security/v1/validateSession", HttpMethod.GET, entity, JGSOuput.class).getBody();
		}
		throw new JGSServiceException("No se ha configurado un host para este servicio. Service: "+this.getClass().getCanonicalName());
	}
	
	public JGSOuput registryAudit(JGSAuditData data) throws JGSServiceException {
		if(host != null && !host.equals("")) {
			HttpHeaders headers = new HttpHeaders();
			HttpEntity<JGSAuditData> entity = new HttpEntity<>(data, headers);
			return client.exchange(host+"/JGS/API/security/v1/register-audit", HttpMethod.POST, entity, JGSOuput.class).getBody();
		}
		throw new JGSServiceException("No se ha configurado un host para este servicio. Service: "+this.getClass().getCanonicalName());
	}
}
