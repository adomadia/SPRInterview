package org.horse.track.fakedb;

import java.util.Map;
import java.util.TreeMap;

import org.horse.track.model.BillInventory;
import org.horse.track.model.Horse;

public final class CollectionDB {
	
	private final static CollectionDB INSTANCE = new CollectionDB();
	
	private Long sequence = 0L;
	
	private Map<Long, Horse> horseMap = new TreeMap<Long, Horse>();
	
	private Map<Integer, BillInventory> billsInventoryMap = new TreeMap<Integer, BillInventory>();
	
	//Prevent direct instanciation
	private CollectionDB() {
		
	}
	
	public Map<Integer, BillInventory> getBillsInventoryMap() {
		return billsInventoryMap;
	}
	
	public Map<Long, Horse> getHorseMap() {
		return horseMap;
	}
	
	public Long getNextSequence(){
		return ++sequence;
	}
	
	public static CollectionDB getInstance(){
		return INSTANCE;
	}
}
