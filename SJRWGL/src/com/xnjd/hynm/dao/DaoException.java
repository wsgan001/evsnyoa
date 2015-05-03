package com.xnjd.hynm.dao;

import org.springframework.dao.DataAccessException;

/**
 * The basic exception of the whole system. All the unchecked exception
 * in the system must extend from it.
 * 
 * @author Eric Feng, 03/17/2012
 */

public class DaoException extends DataAccessException {

	private static final long serialVersionUID = -5946863993639201273L;

	public DaoException(String message) {
		super(message);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}
}