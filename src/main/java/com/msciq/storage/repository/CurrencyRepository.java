package com.msciq.storage.repository;

import com.msciq.storage.common.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * This is the repository interface helps maintain the DB connection to perform
 * operations on Currency entity.
 *
 * @author Rajkumar
 */
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

	public static final String IS_CURRENCY_EXISTS = "select case when count(currency) > 0 then true else false end from Currency as currency where currency.id<>:id and currency.currency=:currency and currency.isDeleted=false";

	/**
	 * Retrieves an Currency based on its Id and IsDeleted fields.
	 * 
	 * @param id        - Currency id
	 * @param isDeleted - deleted status
	 * @return Currency Entity
	 */
	public Currency findByIdAndIsDeleted(long id, boolean isDeleted);

	/**
	 * Finds the Currency based on currency name
	 *
	 * @param currency name
	 * @return Currency
	 */
	public Currency findByCurrency(String currency);

	/**
	 * Check the given currency exists based on currency
	 *
	 * @param id       - currency id
	 * @param currency - currency
	 * @return boolean - true or false
	 */
	@Query(value = IS_CURRENCY_EXISTS)
	public boolean isCurrencyExists(Long id, String currency);

	/**
	 * To find all active currencies.
	 * 
	 * @param isActive - active status
	 * @return List of currency Entities
	 */
	public List<Currency> findByIsActive(Boolean isActive);

}
