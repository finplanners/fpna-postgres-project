package com.msciq.storage.model.domain;

import java.util.List;

/**
 * <p>
 * Paged Interface
 * </p>
 * 
 * @author Rajkumar created on Oct 16, 2020
 */
public interface Paged<T> {

	/**
	 * Get list of generic object T.
	 * 
	 * @return List - list of entity
	 */
	List<T> getList();

	/**
	 * Total count
	 * 
	 * @return - count as a long type
	 */
	long getCount();

	/**
	 * gets Generic object T
	 * 
	 * @return Object T - entity object
	 */
	T getObject();

}
