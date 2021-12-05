package co.com.jgs.persistence.DAO.system;

import co.com.jgs.bo.security.Operations;
import co.com.jgs.bo.security.OperationsPK;
import org.springframework.data.repository.CrudRepository;

public interface OperationsDAO extends CrudRepository<Operations, OperationsPK> {

}
