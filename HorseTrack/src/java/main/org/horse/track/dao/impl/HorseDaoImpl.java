package org.horse.track.dao.impl;

import java.util.Iterator;

import org.horse.track.dao.ICRUDOperations;
import org.horse.track.fakedb.CollectionDB;
import org.horse.track.model.Horse;

/**
 * HorseDao implementation.
 * @author Ashvin Domadia
 *
 */
public class HorseDaoImpl implements ICRUDOperations<Long, Horse>{
	
	private CollectionDB db;
	
	public HorseDaoImpl(CollectionDB db) {
		this.db = db;
	}
	
	/**
	 * find single record by id.
	 */
	@Override
	public Horse findOne(Long id) {
		return db.getHorseMap().get(id);
	}

	/**
	 * insert or update record
	 */
	@Override
	public Horse save(Horse object) {
		if(object.getId() == null){
			object.setId(db.getNextSequence());
		}
		db.getHorseMap().put(object.getId(), object);
		return object;
	}

	
	/**
	 * remove all records
	 */
	@Override
	public void trunc() {
		db.getHorseMap().clear();
	}

	/**
	 * find all records
	 */
	@Override
	public Iterator<Horse> findAll() {
		return db.getHorseMap().values().iterator();
	}

	/*
	 * (non-Javadoc)
	 * @see org.horse.track.dao.ICRUDOperations#findOne()
	 */
	@Override
	public Horse findOne() {
		throw new UnsupportedOperationException("Method not supported.");
	}
	
}
