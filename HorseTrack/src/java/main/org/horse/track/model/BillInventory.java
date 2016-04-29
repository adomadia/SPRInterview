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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((denomination == null) ? 0 : denomination.hashCode());
		result = prime * result + ((inventory == null) ? 0 : inventory.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BillInventory other = (BillInventory) obj;
		if (denomination == null) {
			if (other.denomination != null)
				return false;
		} else if (!denomination.equals(other.denomination))
			return false;
		if (inventory == null) {
			if (other.inventory != null)
				return false;
		} else if (!inventory.equals(other.inventory))
			return false;
		return true;
	}
}
