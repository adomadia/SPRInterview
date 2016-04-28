package org.horse.track.dao;

import java.util.Iterator;


public interface ICRUDOperations<T1, T2> {
	
	public T2 findOne(T1 id);

	public Iterator<T2> findAll();
	
	public T2 save(T2 object);
	
	public void trunc();

}
