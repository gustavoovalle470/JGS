package co.com.jgs.persistence.rest.security;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import co.com.jgs.base.response.JGSResponse;
import co.com.jgs.bo.security.AuditLogs;
import co.com.jgs.persistence.rest.JGSCRUDServices;
import co.com.jgs.persistence.rest.interfaces.IJGSCRUDServices;

@RestController
@RequestMapping(value="/auditlog/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
@JsonInclude(Include.NON_NULL)
public class AuditLogServices extends JGSCRUDServices<AuditLogs, Integer> implements IJGSCRUDServices<AuditLogs, Integer>{
	
	public AuditLogServices() {
		super.entity="AuditLogs BO";
	}
	
	@Override
	public JGSResponse update(AuditLogs entity) {
		response = new JGSResponse();
		response.setMessageResponse(msgDao.findById(9).get(), this.entity);
		writeAuditLog(this.entity, "Update entity", entity.toString(), response);
		return response;
	}

	@Override
	public JGSResponse delete(AuditLogs entity) {
		response = new JGSResponse();
		response.setMessageResponse(msgDao.findById(9).get(), this.entity);
		writeAuditLog(this.entity, "Delete entity", entity.toString(), response);
		return response;
	}

}
