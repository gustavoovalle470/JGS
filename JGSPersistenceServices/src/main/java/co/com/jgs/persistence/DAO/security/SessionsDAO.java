package co.com.jgs.persistence.DAO.security;

import org.springframework.data.repository.CrudRepository;

import co.com.jgs.bo.security.Sessions;
import co.com.jgs.bo.security.SessionsPK;

public interface SessionsDAO extends CrudRepository<Sessions, SessionsPK> {

}
