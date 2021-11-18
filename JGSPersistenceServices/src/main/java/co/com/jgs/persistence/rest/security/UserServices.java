package co.com.jgs.persistence.rest.security;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.jgs.bo.security.Users;
import co.com.jgs.persistence.rest.JGSCRUDServices;
import co.com.jgs.persistence.rest.interfaces.IJGSCRUDServices;

@RestController
@RequestMapping(value="/user/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
public class UserServices extends JGSCRUDServices<Users, String> implements IJGSCRUDServices<Users, String>{
	
	public UserServices() {
		super.entity="User BO";
	}
	
	
}
