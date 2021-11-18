package co.com.jgs.persistence.DAO.system;

import org.springframework.data.repository.CrudRepository;

import co.com.jgs.bo.system.Catalogs;
import co.com.jgs.bo.system.CatalogsPK;

public interface CatalogsDAO extends CrudRepository<Catalogs, CatalogsPK> {

}
