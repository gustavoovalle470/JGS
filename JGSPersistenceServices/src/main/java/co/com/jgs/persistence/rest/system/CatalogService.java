package co.com.jgs.persistence.rest.system;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.jgs.bo.system.Catalogs;
import co.com.jgs.bo.system.CatalogsPK;
import co.com.jgs.persistence.rest.JGSCRUDServices;
import co.com.jgs.persistence.rest.interfaces.IJGSCRUDServices;

@RestController
@RequestMapping(value="/catalogs/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
public class CatalogService extends JGSCRUDServices<Catalogs, CatalogsPK> implements IJGSCRUDServices<Catalogs, CatalogsPK>{
	
	public CatalogService() {
		super.entity="Catalogs BO";
	}
	
	
}
