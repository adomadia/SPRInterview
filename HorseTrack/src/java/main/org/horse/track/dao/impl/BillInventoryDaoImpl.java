package org.horse.track.dao.impl;

import java.util.Iterator;

import org.horse.track.dao.ICRUDOperations;
import org.horse.track.fakedb.CollectionDB;
import org.horse.track.model.BillInventory;

public class BillInventoryDaoImpl implements ICRUDOperations<Integer, BillInventory> {

	private CollectionDB db;
	
	public BillInventoryDaoImpl(CollectionDB db) {
		this.db = db;
	}
	
	@Override
	public BillInventory findOne(Integer id) {
		return db.getBillsInventoryMap().get(id);
	}

	@Override
	public Iterator<BillInventory> findAll() {
		return db.getBillsInventoryMap().values().iterator();
	}

	@Override
	public BillInventory save(BillInventory object) {
		db.getBillsInventoryMap().put(object.getDenomination().getValue(), object);
		return object;
	}

	@Override
	public void trunc() {
		db.getBillsInventoryMap().clear();
		
	}

	@Override
	public BillInventory findOne() {
		throw new UnsupportedOperationException("Method not supported.");
	}

}
