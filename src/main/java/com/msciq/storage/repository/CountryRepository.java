package com.msciq.storage.repository;

import com.msciq.storage.common.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This Repository interface maintains connection between Country entity and
 * database
 *
 * @author Rajkumar
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

	public static final String IS_COUNTRY_EXISTS = "select case when count(country) > 0 then true else false end from Country as country where country.id<>:id and (country.name=:name or country.countryCode=:countryCode) and country.isDeleted=false";
	public static final String  GET_ALL_COUNTRIES = "select country from Country as country where country.isDeleted=false";
	public static final String GET_COUNTRIES_BY_NAME = "select country from Country as country where lower(country.name)"
			+ " LIKE CONCAT('%',lower(:searchTerm),'%') AND country.isDeleted=false";

	
	/**
	 * Check the given country exists based on id, name and countrycode
	 *
	 * @param id - country id
	 * @param name - country name
	 * @param countryCode - country code
	 * @return boolean - true or false
	 */
	@Query(value = IS_COUNTRY_EXISTS)
	boolean isCountryExists(long id, String name, String countryCode);
	
	
	/**
	 * To find all active countries.
	 * 
	 * @param isActive - active status
	 * @return List of Country Entities
	 */
	List<Country> findByIsActive(Boolean isActive);
	
	/**
	 * Finds a country based on countryCode and name
	 *
	 * @param countryCode
	 * @param name
	 * @return List<Country>
	 */
	List<Country> findByCountryCodeOrNameOrIdIsNot(String countryCode, String name, long id);

	/**
	 * Finds a country based on countryCode and name
	 *
	 * @param countryCode
	 * @param name
	 * @return List<Country>
	 */
	List<Country> findByCountryCodeOrName(String countryCode, String name);

	/**
	 * Finds the country based on countrycode and name
	 *
	 * @param countryCode
	 * @param name
	 * @return Country
	 * @author Rajkumar
	 */
	Country findByCountryCodeAndName(String countryCode, String name);

	/**
	 * Finds the country based on countrycode
	 *
	 * @param countryCode
	 * @return Country
	 * @author Rajkumar
	 */
	Country findByCountryCode(String countryCode);

	/**
	 * Finds the country based on name
	 *
	 * @param name
	 * @returN Country
	 */
	Country findByName(String name);

	/**
	 * Finds the country based on countryId
	 *
	 * @param countryId
	 * @return Country
	 * @author Rajkumar
	 */
	Country findById(long countryId);

	/**
	 * Retrives the all countries
	 *
	 * @param pageable
	 * @return Pageable
	 * @author Rajkumar
	 */
	@Query(value = GET_ALL_COUNTRIES)
	Page<Country> getAllCountries(Pageable pageable);

	/**
	 * Retrives the all countries based on the search term
	 *
	 * @param searchTerm
	 * @param pageable
	 * @return Page<Country>
	 * @author Rajkumar
	 */
	@Query(value = GET_COUNTRIES_BY_NAME)
	Page<Country> searchCountries(@Param("searchTerm") String searchTerm, Pageable pageable);

	/**
	 * Finds the country based on countryId and isActive
	 *
	 * @param countryId
	 * @param isActive
	 * @return Country
	 * @author Rajkumar
	 */
	Country findByIdAndIsActive(long countryId, boolean isActive);

	/**
	 * Finds the country based on countryId and isDeleted
	 *
	 * @param countryId
	 * @param isDeleted
	 * @return
	 * @author Rajkumar
	 */
	Country findByIdAndIsDeleted(long countryId, boolean isDeleted);

	/**
	 * To find a country by its Id, isDeleted and isActive fields.
	 * 
	 * @param id country Id
	 * @return List of country entity
	 */
	List<Country> findByIdAndIsDeletedFalseAndIsActiveTrue(long id);

	/**
	 * To find the number of countries present. 
	 * 
	 * @return Number of countries.
	 */
	int countByIsDeletedFalse();

}
