package org.horse.track.model;


public class BillInventory{
	
	public final static int MAX_INVENTORY_VALUE = 10;
	
	private Bill denomination;
	private Integer inventory;
	
	public BillInventory(Integer denomination, Integer inventory) {
		this.denomination = new Bill(denomination);
		this.setInventory(inventory);
		
	}

	public Bill getDenomination() {
		return denomination;
	}

	public Integer getInventory() {
		return inventory;
	}
	
	public void setInventory(Integer inventory) {
		
		if(inventory < 0){
			throw new IllegalArgumentException("Inventory should not be negative number.");
		}
		
		if(inventory > MAX_INVENTORY_VALUE){
			throw new IllegalArgumentException("Maximam bills allowed for inventory should not be more than " + MAX_INVENTORY_VALUE);
		}
		
		this.inventory = inventory;
	}
}
