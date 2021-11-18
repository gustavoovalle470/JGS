package co.com.jgs.persistence.rest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import co.com.jgs.base.response.JGSResponse;
import co.com.jgs.bo.security.AuditLogs;
import co.com.jgs.persistence.DAO.security.AuditLogDAO;
import co.com.jgs.persistence.DAO.system.MessagesDAO;
import co.com.jgs.persistence.rest.interfaces.IJGSCRUDServices;

public abstract class JGSCRUDServices <T, ID> implements IJGSCRUDServices<T, ID>{

	protected final Logger logger = LoggerFactory.getLogger(JGSCRUDServices.class);
	@Autowired
	private AuditLogDAO auditDao;
	@Autowired
	protected CrudRepository<T, ID> dao;
	@Autowired
	protected MessagesDAO msgDao;
	protected JGSResponse response;
	protected String entity = "Entidad generica";
	protected boolean requiredAuditLog = true;
	
	protected void writeAuditLog(String entity, String method, String params, JGSResponse response) {
		AuditLogs audit=new AuditLogs();
		audit.setWsinvoked("CRUD for "+entity);
		audit.setMethodInvoked(method);
		audit.setParametersInvoked(params);
		audit.setSuccesOperation(""+response.isSuccess());
		audit.setOperationDate(Calendar.getInstance().getTime());
		if(response.getMessageResponse()!= null) {
			audit.setResponseMessage(response.getMessageResponse().getMessage());
		}else {
			audit.setResponseMessage("No se ha adicionado un mensaje de respuesta a este servicio.");
		}
		
		auditDao.save(audit);
	}
	
	@Override
	public JGSResponse save(T entity) {
		response = new JGSResponse();
		try{
			dao.save(entity);
			response.setSuccessOperation(entity, msgDao.findById(1).get());
		}catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			response.setMessageResponse(msgDao.findById(0).get());
		}finally {
			writeAuditLog(this.entity, "Save entity", entity.toString(), response);
		}
		return response;
	}


	@Override
	public JGSResponse findById(ID id) {
		response = new JGSResponse();
		try{
			if(dao.existsById(id)) {
				response.setSuccessOperation(dao.findById(id).get(), msgDao.findById(3).get());
			}else {
				response.setMessageResponse(msgDao.findById(2).get());
			}
		}catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			response.setMessageResponse(msgDao.findById(0).get());
		}finally {
			writeAuditLog(this.entity, "Find entity by ID", id.toString(), response);
		}
		return response;
	}

	@Override
	public JGSResponse findAll() {
		response = new JGSResponse();
		try {
			List<T> entities = new ArrayList<>();
			dao.findAll().forEach(entities::add);
			if(entities.size()>0) {
				response.setSuccessOperation(entities, msgDao.findById(5).get(), entities.size()+","+this.entity);
			}else {
				response.setMessageResponse(msgDao.findById(4).get(), this.entity);
			}
		}catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			response.setMessageResponse(msgDao.findById(0).get());
		}finally {
			writeAuditLog(this.entity, "Find all entities", null, response);
		}
		return response;
	}
	
	@Override
	public JGSResponse update(T entity) {
		response = new JGSResponse();
		try{
			dao.save(entity);
			response.setSuccessOperation(entity, msgDao.findById(6).get(), this.entity);
		}catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			response.setMessageResponse(msgDao.findById(0).get());
		}finally {
			writeAuditLog(this.entity, "Update entity", entity.toString(), response);
		}
		return response;
	}

	@Override
	public JGSResponse delete(T entity) {
		response = new JGSResponse();
		try{
			dao.delete(entity);
			response.setSuccessOperation(entity, msgDao.findById(7).get(), this.entity);
		}catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			response.setMessageResponse(msgDao.findById(0).get());
		}finally {
			writeAuditLog(this.entity, "Delete entity", entity.toString(), response);
		}
		return response;
	}

}
