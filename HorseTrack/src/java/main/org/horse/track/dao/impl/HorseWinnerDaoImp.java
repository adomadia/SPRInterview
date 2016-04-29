package org.horse.track.dao.impl;

import java.util.Iterator;

import org.horse.track.dao.ICRUDOperations;
import org.horse.track.fakedb.CollectionDB;
import org.horse.track.model.Horse;

/***
 * HorseWinner Dao implementaiton.
 * @author Ashvin Domadia
 *
 */
public class HorseWinnerDaoImp implements ICRUDOperations<Integer, Horse>{
	
	private CollectionDB db;
	
	public HorseWinnerDaoImp(CollectionDB db) {
		this.db = db;
	}

	/**
	 * not supported
	 */
	@Override
	public Horse findOne(Integer id) {
		throw new UnsupportedOperationException("Method not supported.");
	}

	/**
	 * not supported
	 */
	@Override
	public Iterator<Horse> findAll() {
		throw new UnsupportedOperationException("Method not supported.");
	}
	
	/**
	 * save winner horse
	 */
	@Override
	public Horse save(Horse object) {
		db.setWinnerHorse(object);
		return object;
	}

	/**
	 * not supported
	 */
	@Override
	public void trunc() {
		throw new UnsupportedOperationException("Method not supported.");
		
	}

	/**
	 * return winner horse
	 */
	@Override
	public Horse findOne() {
		return db.getWinnerHorse();
	}

}
