package org.horse.track.support;

import org.horse.track.io.IWritable;
import org.horse.track.service.BillInventoryService;
import org.horse.track.service.HorseService;

/**
 * Display Inventory info to screen.
 * @author Ashvin Domadia
 *
 */
public class InventorySupport {

	private BillInventoryService inventoryService; 
	private HorseService horseService; 
	private IWritable out;
	
	

	public InventorySupport(BillInventoryService inventoryService,
			HorseService horseService, IWritable out) {
		this.inventoryService = inventoryService;
		this.horseService = horseService;
		this.out = out;
	}



	/**
	 * display horse and cash inventory.
	 */
	public void display(){
		out.write(inventoryService.printInventory());
		out.write(horseService.printMenu());
	}
	
}
