package co.com.jgs.persistence.DAO.utils;

import org.springframework.data.repository.CrudRepository;

import co.com.jgs.bo.utils.States;
import co.com.jgs.bo.utils.StatesPK;

public interface StatesDAO extends CrudRepository<States, StatesPK> {

}
