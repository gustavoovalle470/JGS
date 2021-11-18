package co.com.jgs.persistence.rest.utils;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.jgs.bo.utils.States;
import co.com.jgs.bo.utils.StatesPK;
import co.com.jgs.persistence.rest.JGSCRUDServices;
import co.com.jgs.persistence.rest.interfaces.IJGSCRUDServices;

@RestController
@RequestMapping(value="/state/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
public class StateServices extends JGSCRUDServices<States, StatesPK> implements IJGSCRUDServices<States, StatesPK>{
	
	public StateServices() {
		super.entity="State BO";
	}
	
	
}
