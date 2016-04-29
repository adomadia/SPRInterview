package org.horse.track.dao;

import java.util.Iterator;

/**
 * DAO interface
 * 
 * @author Ashvin
 *
 * @param <T1> unique key type
 * @param <T2> queried model type
 */
public interface ICRUDOperations<T1, T2> {
	
	public T2 findOne();
	
	public T2 findOne(T1 id);

	public Iterator<T2> findAll();
	
	public T2 save(T2 object);
	
	public void trunc();

}
