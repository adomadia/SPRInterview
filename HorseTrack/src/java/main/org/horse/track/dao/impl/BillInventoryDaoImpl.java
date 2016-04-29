package org.horse.track.dao.impl;

import java.util.Iterator;

import org.horse.track.dao.ICRUDOperations;
import org.horse.track.fakedb.CollectionDB;
import org.horse.track.model.BillInventory;

/**
 * BillInventory Dao implementation.
 * @author Ashvin Domadia
 *
 */
public class BillInventoryDaoImpl implements ICRUDOperations<Integer, BillInventory> {

	private CollectionDB db;
	
	public BillInventoryDaoImpl(CollectionDB db) {
		this.db = db;
	}
	
	/**
	 * find single record by id.
	 */
	@Override
	public BillInventory findOne(Integer id) {
		return db.getBillsInventoryMap().get(id);
	}

	/**
	 * return all records.
	 */
	@Override
	public Iterator<BillInventory> findAll() {
		return db.getBillsInventoryMap().values().iterator();
	}

	/**
	 * save or update record
	 */
	@Override
	public BillInventory save(BillInventory object) {
		db.getBillsInventoryMap().put(object.getDenomination().getValue(), object);
		return object;
	}

	/**
	 * remove all records
	 */
	@Override
	public void trunc() {
		db.getBillsInventoryMap().clear();
	}

	/**
	 * return first record 
	 * not supported
	 */
	@Override
	public BillInventory findOne() {
		throw new UnsupportedOperationException("Method not supported.");
	}

}
