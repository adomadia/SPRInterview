package org.horse.track.service;

import java.util.List;

import org.horse.track.model.BillInventory;

public interface BillInventoryService {

	public BillInventory findById(Integer id);
	public void save(BillInventory inventory);
	public void removeAll();
	public List<BillInventory> findAll();
	public void restock();
	public String printInventory();
}
