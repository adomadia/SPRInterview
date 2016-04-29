package org.horse.track.fakedb;

import java.util.Map;
import java.util.TreeMap;

import org.horse.track.model.BillInventory;
import org.horse.track.model.Horse;

/**
 * In memory collection base store to persist data 
 * @author Ashvin
 *
 */
public class CollectionDB {
	
	private final static CollectionDB INSTANCE = new CollectionDB();

	private Long sequence = 0L;
	
	private Map<Long, Horse> horseMap = new TreeMap<Long, Horse>();
	
	private Map<Integer, BillInventory> billsInventoryMap = new TreeMap<Integer, BillInventory>();
	
	private Horse winnerHorse;
	
	//Prevent direct instantiation
	private CollectionDB() {
		
	}
	
	public Map<Integer, BillInventory> getBillsInventoryMap() {
		return billsInventoryMap;
	}
	
	public Map<Long, Horse> getHorseMap() {
		return horseMap;
	}
	
	/**
	 * generate next sequencial number.
	 * @return
	 */
	public Long getNextSequence(){
		return ++sequence;
	}
	
	
	public Horse getWinnerHorse() {
		return winnerHorse;
	}
	
	public void setWinnerHorse(Horse winnerHorse) {
		this.winnerHorse = winnerHorse;
	}
	
	/**
	 * Single method
	 * @return
	 */
	public static CollectionDB getInstance(){
		return INSTANCE;
	}

}
