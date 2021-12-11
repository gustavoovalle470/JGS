package co.com.jgs.persistence.DAO.subscription;

import org.springframework.data.repository.CrudRepository;

import co.com.jgs.bo.subscription.Licenses;

public interface LicensesDAO extends CrudRepository<Licenses, String> {

}
