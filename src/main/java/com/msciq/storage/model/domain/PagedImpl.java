package com.msciq.storage.model.domain;

import java.util.List;

/**
 * <p>
 * Paged Implementation
 * </p>
 * 
 * @author Rajkumar created on Jun 30, 2022
 */
public class PagedImpl<T> implements Paged<T> {

	public PagedImpl() {
	}

	public PagedImpl(List<T> list, long count) {
		this.list = list;
		this.count = count;
	}

	public PagedImpl(T object, long count) {
		this.object = object;
		this.count = count;
	}

	/**
	 * List of T
	 */
	private List<T> list;

	/**
	 * Object used Generically
	 */
	private T object;

	/**
	 * Total count
	 */
	private long count;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	@Override
	public T getObject() {
		return this.object;
	}

}
