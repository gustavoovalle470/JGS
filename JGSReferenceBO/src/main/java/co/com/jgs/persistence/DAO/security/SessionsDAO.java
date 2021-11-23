package co.com.jgs.persistence.DAO.security;

import org.springframework.data.repository.CrudRepository;

import co.com.jgs.bo.security.Sessions;
import co.com.jgs.bo.security.SessionsPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SessionsDAO extends CrudRepository<Sessions, SessionsPK> {
    @Query("SELECT s FROM Sessions s WHERE s.sesionsPK.username=:username")
    public Sessions findByUsername(@Param("username") String username);
    
    @Query("SELECT s FROM Sessions s WHERE s.sesionsPK.sessionId=:sessionid")
    public Sessions findBySessionId(@Param("sessionid") String sessionid);
}
