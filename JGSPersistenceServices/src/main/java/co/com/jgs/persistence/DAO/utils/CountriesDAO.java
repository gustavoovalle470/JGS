package co.com.jgs.persistence.DAO.utils;

import org.springframework.data.repository.CrudRepository;

import co.com.jgs.bo.utils.Countries;

public interface CountriesDAO extends CrudRepository<Countries, Integer> {

}
