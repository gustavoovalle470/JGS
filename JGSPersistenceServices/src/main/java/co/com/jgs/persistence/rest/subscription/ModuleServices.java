package co.com.jgs.persistence.rest.subscription;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import co.com.jgs.bo.subscription.Modules;
import co.com.jgs.persistence.rest.JGSCRUDServices;
import co.com.jgs.persistence.rest.interfaces.IJGSCRUDServices;

@RestController
@RequestMapping(value="/module/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
@JsonInclude(Include.NON_NULL)
public class ModuleServices extends JGSCRUDServices<Modules, String> implements IJGSCRUDServices<Modules, String>{
	
	public ModuleServices() {
		super.entity="Modules BO";
	}
}
