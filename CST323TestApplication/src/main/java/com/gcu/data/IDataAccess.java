package com.gcu.data;

import java.util.List;

/**
 * The user interface with all the methods implemented by the user services
 * using generic models
 * @param <T> generic list users
 */
public interface IDataAccess<T> 
{
	/**
	 * @return list of all users using generics
	 */
	public List<T> findAll();
	/**
	 * @param id (user id)
	 * @return user model of user being searched for
	 */
	public T findById(int id);
	/**
	 * @param t generic user
	 * @return true/false
	 */
	public boolean create(T t);
	/**
	 * @param oldt original model
	 * @param newt updated model
	 * @return true/false
	 */
	public boolean update(T oldt, T newt);
	/**
	 * @param t generic user
	 * @return true/false
	 */
	public boolean delete(T t);
	/**
	 * @param username (searched username)
	 * @return user model found/ exception
	 */
	public T findByUsername(String username);
}
