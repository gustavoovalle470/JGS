package co.com.jgs.persistence.rest.security;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import co.com.jgs.base.response.JGSResponse;
import co.com.jgs.bo.security.Sessions;
import co.com.jgs.bo.security.SessionsPK;
import co.com.jgs.persistence.rest.JGSCRUDServices;
import co.com.jgs.persistence.rest.interfaces.IJGSCRUDServices;

@RestController
@RequestMapping(value="/session/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
@JsonInclude(Include.NON_NULL)
public class SessionServices extends JGSCRUDServices<Sessions, SessionsPK> implements IJGSCRUDServices<Sessions, SessionsPK>{
	
	public SessionServices() {
		super.entity="Sessions BO";
	}
	
	@RequestMapping(value="getById", 
	method = RequestMethod.POST, 
	consumes = MediaType.APPLICATION_JSON_VALUE,
	produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JGSResponse findByComplexId(@RequestBody SessionsPK sessionPK) {
		return findById(sessionPK);
	}
}
