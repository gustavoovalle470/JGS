package co.com.jgs.persistence.DAO.security;

import org.springframework.data.repository.CrudRepository;

import co.com.jgs.bo.security.Users;

public interface UsersDAO extends CrudRepository<Users, String> {

}
