package co.com.jgs.persistence.rest.interfaces;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.com.jgs.base.response.JGSResponse;

public interface IJGSCRUDServices <T, ID> {
	
	@RequestMapping(value="save", 
	method = RequestMethod.POST, 
	produces = MediaType.APPLICATION_JSON_VALUE, 
	consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JGSResponse save(@RequestBody T entity);
	
	@RequestMapping(value="update", 
	method = RequestMethod.PUT, 
	produces = MediaType.APPLICATION_JSON_VALUE, 
	consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JGSResponse update(@RequestBody T entity);
	
	@RequestMapping(value="getById/{id}", 
	method = RequestMethod.GET, 
	produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JGSResponse findById(@PathVariable("id") ID entity);
	
	@RequestMapping(value="getAll", 
	method = RequestMethod.GET, 
	produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JGSResponse findAll();
	
	@RequestMapping(value="delete", 
	method = RequestMethod.PUT, 
	produces = MediaType.APPLICATION_JSON_VALUE, 
	consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JGSResponse delete(@RequestBody T entity);
}
