package co.com.jgs.persistence.rest.interfaces;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.com.jgs.base.response.JGSResponse;
import io.swagger.annotations.ApiOperation;

public interface IJGSCRUDServices <T, ID> {
	
	@RequestMapping(value="save", 
	method = RequestMethod.POST, 
	produces = MediaType.APPLICATION_JSON_VALUE, 
	consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Guardar la entdidad en la base de datos",
				  notes="Recibe por parametro un JSON con la entidad que esta mapeada y se guardara en la base de datos. \n"
				  	  + "Responde un objecto de tipo JGSResponse con success true si la operacion finalizo con exito, false en caso contrario")
	public @ResponseBody JGSResponse save(@RequestBody T entity);
	
	
	@RequestMapping(value="update", 
	method = RequestMethod.PUT, 
	produces = MediaType.APPLICATION_JSON_VALUE, 
	consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Actualiza la entdidad en la base de datos",
	  			  notes="Recibe por parametro un JSON con la entidad que esta mapeada y se actualizara en la base de datos. \n"
   					  + "Responde un objecto de tipo JGSResponse con success true si la operacion finalizo con exito, false en caso contrario")
	public @ResponseBody JGSResponse update(@RequestBody T entity);
	
	@RequestMapping(value="getById/{id}", 
	method = RequestMethod.GET, 
	produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Busca una entdidad en la base de datos",
	  			  notes="Recibe por URL el ID de una entidad que esta mapeada y la recuperara de la base de datos. \n"
	  				  + "Responde un objecto de tipo JGSResponse con success true si la operacion finalizo con exito, false en caso contrario \n"
	  				  + "Responde con la entidad mapeada en el JGSResponse si la encuentra, null en caso contrario.")
	public @ResponseBody JGSResponse findById(@PathVariable("id") ID entity);
	
	@RequestMapping(value="getAll", 
	method = RequestMethod.GET, 
	produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Busca una todas las entradas de esta entidad en la base de datos",
	  notes="Responde un objecto de tipo JGSResponse con success true si la operacion finalizo con exito, false en caso contrario \n"
		  + "Responde con un arreglo con todas las entidades mapeadas en el JGSResponse si las encuentra, null en caso contrario.")
	public @ResponseBody JGSResponse findAll();
	
	@RequestMapping(value="delete", 
	method = RequestMethod.PUT, 
	produces = MediaType.APPLICATION_JSON_VALUE, 
	consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Borra una entdidad en la base de datos",
	  notes="Recibe por parametro un JSON con la entidad que esta mapeada y se borrara en la base de datos. \n"
		  + "Responde un objecto de tipo JGSResponse con success true si la operacion finalizo con exito, false en caso contrario")
	public @ResponseBody JGSResponse delete(@RequestBody T entity);
}
