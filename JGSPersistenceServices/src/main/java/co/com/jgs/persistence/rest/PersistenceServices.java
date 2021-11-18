package co.com.jgs.persistence.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.jgs.base.response.JGSResponse;

@RestController
@RequestMapping(value="/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
public class PersistenceServices {

	@RequestMapping(value="status", 
	method = RequestMethod.GET, 
	produces = MediaType.APPLICATION_JSON_VALUE)
	public JGSResponse getServiceStatus() {
		JGSResponse response = new JGSResponse();
		response.setSuccessOperation("This service is alive", null);
		return response;
	}
}
