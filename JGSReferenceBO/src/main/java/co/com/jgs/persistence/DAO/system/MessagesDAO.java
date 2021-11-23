package co.com.jgs.persistence.DAO.system;

import org.springframework.data.repository.CrudRepository;

import co.com.jgs.bo.system.Messages;

public interface MessagesDAO extends CrudRepository<Messages, Integer> {

}
