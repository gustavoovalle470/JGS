package co.com.jgs.business.service;

import co.com.jgs.bo.security.Operations;
import co.com.jgs.bo.security.Services;
import co.com.jgs.bo.security.Users;

public class JGSContext {
	
	private Services service;
	private Operations operation;
	private Users user;
	
	public Services getService() {
		return service;
	}
	
	public void setService(Services service) {
		this.service = service;
	}
	
	public Operations getOperation() {
		return operation;
	}
	
	public void setOperation(Operations operation) {
		this.operation = operation;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
}
