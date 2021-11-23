package co.com.jgs.persistence.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.jgs.ReleaseController;
import co.com.jgs.base.response.JGSResponse;
import co.com.jgs.base.utils.JGSSecurityUtils;
import co.com.jgs.bo.security.JGSAuthentication;
import co.com.jgs.bo.security.Users;
import co.com.jgs.persistence.DAO.security.UsersDAO;
import co.com.jgs.persistence.DAO.system.MessagesDAO;
import co.com.jgs.persistence.DAO.system.ModuleConfigurationsDAO;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class CoreServices {

	@Autowired
	protected MessagesDAO msgDao;
	@Autowired
	protected UsersDAO userDao;
	@Autowired
	protected ModuleConfigurationsDAO mcDao;
	
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
	
	@RequestMapping(value="getToken", 
	method = RequestMethod.POST,
	consumes = MediaType.APPLICATION_JSON_VALUE,
	produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Genera una sesion de usuario requerida para utilizar los demas servicios del API.",
  	              notes="Un objeto con el ID de la sesion. \n"
		              + "Responde un objecto de tipo JGSResponse con success true si la operacion finalizo con exito, false en caso contrario")
	public JGSResponse generateToken(@RequestBody JGSAuthentication auth) {
		auth.setUsername(auth.getUsername().toUpperCase());
		JGSResponse response = new JGSResponse();
		try{
			if(userDao.existsById(auth.getUsername())) {
				Users user = userDao.findById(auth.getUsername()).get();
				if((user.getUserStatus().getCatalogsPK().getItemId()==1 || user.getUserStatus().getCatalogsPK().getItemId()==2) && 
					user.getPassword().equals(JGSSecurityUtils.encryptPwd(auth.getPassword()))) {
					response.setSuccessOperation(getSession(user), null);
				}else {
					
				}
			}else {
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	private String getSession(Users user) {
		return JGSSecurityUtils.getJWTToken(user.getUsername(), 
				Integer.parseInt(mcDao.findById(1).get().getValue()));
	}
}
