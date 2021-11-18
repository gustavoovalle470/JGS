package co.com.jgs.persistence.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.jgs.ReleaseController;
import co.com.jgs.base.response.JGSResponse;
import co.com.jgs.persistence.DAO.system.MessagesDAO;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
public class PersistenceServices {

	@Autowired
	protected MessagesDAO msgDao;
	
	@RequestMapping(value="status", 
	method = RequestMethod.GET, 
	produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Retorna el estado del servicio.",
  	              notes="Retorna el estado del servicio de persistencia. \n"
		              + "Responde un objecto de tipo JGSResponse con success true si la operacion finalizo con exito, false en caso contrario")
	public JGSResponse getServiceStatus() {
		JGSResponse response = new JGSResponse();
		response.setSuccessOperation("This service is alive", msgDao.findById(10).get(), "JGSPersistenceServices");
		return response;
	}
	
	@RequestMapping(value="", 
	method = RequestMethod.GET, 
	produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Retorna la informacion del servicio como version, y ultimo release.",
  	              notes="Retorna la informacion del servicio. \n"
		              + "Responde un objecto de tipo JGSResponse con success true si la operacion finalizo con exito, false en caso contrario")
	public JGSResponse getInfo() {
		JGSResponse response = new JGSResponse();
		response.setSuccessOperation(new ReleaseController(), msgDao.findById(10).get(), "JGSPersistenceServices");
		return response;
	}
}
