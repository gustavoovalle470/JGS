package co.com.jgs.persistence.rest.utils;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.jgs.bo.utils.Countries;
import co.com.jgs.persistence.rest.JGSCRUDServices;
import co.com.jgs.persistence.rest.interfaces.IJGSCRUDServices;

@RestController
@RequestMapping(value="/country/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
public class CountryServices extends JGSCRUDServices<Countries, Integer> implements IJGSCRUDServices<Countries, Integer>{
	
	public CountryServices() {
		super.entity="Country BO";
	}
	
	
}
