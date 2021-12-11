package co.com.jgs.persistence.DAO.security;

import org.springframework.data.repository.CrudRepository;

import co.com.jgs.bo.security.AuditLogs;

public interface AuditLogDAO extends CrudRepository<AuditLogs, Integer> {

}
