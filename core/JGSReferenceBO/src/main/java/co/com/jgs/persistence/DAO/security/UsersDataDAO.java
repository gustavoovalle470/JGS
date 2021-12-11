package co.com.jgs.persistence.DAO.security;

import org.springframework.data.repository.CrudRepository;

import co.com.jgs.bo.security.Usersdata;

public interface UsersDataDAO extends CrudRepository<Usersdata, String> {

}
