package org.horse.track.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.horse.track.dao.ICRUDOperations;
import org.horse.track.model.BillInventory;
import org.horse.track.service.BillInventoryService;

/**
 * 
 * @author Ashvin Domadia
 *
 */
public class BillInventoryServiceImpl implements BillInventoryService {
	
	ICRUDOperations<Integer, BillInventory> dao;
	
	public BillInventoryServiceImpl(ICRUDOperations<Integer, BillInventory> dao) {
		this.dao = dao;
	}

	@Override
	public BillInventory findById(Integer id) {
		return dao.findOne(id);
	}

	@Override
	public void save(BillInventory inventory) {
		dao.save(inventory);
		
	}

	@Override
	public void removeAll() {
		dao.trunc();
		
	}

	@Override
	public List<BillInventory> findAll() {
		List<BillInventory> list = new ArrayList<BillInventory>();
		Iterator<BillInventory> iterator = dao.findAll();
		
		while(iterator.hasNext()){
			list.add(iterator.next());
		}
		
		return list;
	}

	@Override
	public void restock() {
		Iterator<BillInventory> iterator = dao.findAll();
		while(iterator.hasNext()){
			BillInventory billInventory = iterator.next();
			billInventory.setInventory(BillInventory.MAX_INVENTORY_VALUE);
			dao.save(billInventory);
		}
		
	}

	@Override
	public String printInventory() {
	
		StringBuilder builder = new StringBuilder();
		builder.append("Inventory:\n");
		List<BillInventory> inventories = findAll();

		for(BillInventory inventory : inventories){
			builder.append("$" + inventory.getDenomination().getValue() + "," + inventory.getInventory());
			builder.append("\n");
		}
		return builder.toString();
	}
	
	

}
