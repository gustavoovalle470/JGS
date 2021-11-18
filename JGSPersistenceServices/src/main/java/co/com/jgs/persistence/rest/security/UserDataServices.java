package co.com.jgs.persistence.rest.security;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import co.com.jgs.bo.security.Usersdata;
import co.com.jgs.persistence.rest.JGSCRUDServices;
import co.com.jgs.persistence.rest.interfaces.IJGSCRUDServices;

@RestController
@RequestMapping(value="/userData/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
@JsonInclude(Include.NON_NULL)
public class UserDataServices extends JGSCRUDServices<Usersdata, String> implements IJGSCRUDServices<Usersdata, String>{
	
	public UserDataServices() {
		super.entity="User data BO";
	}
}
