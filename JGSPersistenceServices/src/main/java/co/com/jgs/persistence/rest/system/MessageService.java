package co.com.jgs.persistence.rest.system;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.jgs.bo.system.Messages;
import co.com.jgs.persistence.rest.JGSCRUDServices;
import co.com.jgs.persistence.rest.interfaces.IJGSCRUDServices;

@RestController
@RequestMapping(value="/messages/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
public class MessageService extends JGSCRUDServices<Messages, Integer> implements IJGSCRUDServices<Messages, Integer>{
	
	public MessageService() {
		super.entity="User BO";
	}
	
	
}
