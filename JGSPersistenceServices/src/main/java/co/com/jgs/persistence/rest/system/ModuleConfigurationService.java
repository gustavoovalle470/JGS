package co.com.jgs.persistence.rest.system;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.jgs.bo.system.Moduleconfigurations;
import co.com.jgs.persistence.rest.JGSCRUDServices;
import co.com.jgs.persistence.rest.interfaces.IJGSCRUDServices;

@RestController
@RequestMapping(value="/moduleConfigurations/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
public class ModuleConfigurationService extends JGSCRUDServices<Moduleconfigurations, Integer> implements IJGSCRUDServices<Moduleconfigurations, Integer>{
	
	public ModuleConfigurationService() {
		super.entity="Module Configurations BO";
	}
	
	
}
