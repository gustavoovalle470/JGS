package co.com.jgs.persistence.rest.system;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.jgs.base.response.JGSResponse;
import co.com.jgs.bo.system.Catalogs;
import co.com.jgs.bo.system.CatalogsPK;
import co.com.jgs.persistence.rest.JGSCRUDServices;
import co.com.jgs.persistence.rest.interfaces.IJGSCRUDServices;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/catalogs/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
public class CatalogService extends JGSCRUDServices<Catalogs, CatalogsPK> implements IJGSCRUDServices<Catalogs, CatalogsPK>{
	
	public CatalogService() {
		super.entity="Catalogs BO";
	}
	
	@RequestMapping(value="getById", 
	method = RequestMethod.POST, 
	consumes = MediaType.APPLICATION_JSON_VALUE,
	produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Busca una entdidad en la base de datos",
	  notes="Recibe un JSON con el ID de una entidad que esta mapeada y la recuperara de la base de datos. \n"
		  + "Responde un objecto de tipo JGSResponse con success true si la operacion finalizo con exito, false en caso contrario \n"
		  + "Responde con la entidad mapeada en el JGSResponse si la encuentra, null en caso contrario. \n"
		  + "Este metodo reemplaza la version original con ID primitivo por uno con ID compuesto.")
	public @ResponseBody JGSResponse findByComplexId(@RequestBody CatalogsPK catalogPK) {
		return findById(catalogPK);
	}
	
}
