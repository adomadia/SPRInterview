package org.horse.track.service.impl;

import org.horse.track.model.BillInventory;
import org.horse.track.model.Horse;
import org.horse.track.service.BillInventoryService;
import org.horse.track.service.DataStagingService;
import org.horse.track.service.HorseService;
import org.horse.track.service.HorseWinnerService;

/**
 * 
 * @author Ashvin Domadia
 *
 */
public class DataStagingServiceImpl implements DataStagingService{
	
	private HorseService horseService;
	private BillInventoryService inventoryService;
	private HorseWinnerService horseWinnerService;
	
	public DataStagingServiceImpl(HorseService horseService, BillInventoryService inventoryService, HorseWinnerService horseWinnerService) {
		this.horseService = horseService;
		this.inventoryService = inventoryService;
		this.horseWinnerService = horseWinnerService;
	}

	@Override
	public void initalize() {
		
		//Initalize Horses and stored in DB.
		
		Horse winnerHorse = new Horse("That Darn Gray Cat", 5);
		
		horseService.save(winnerHorse);
		horseService.save(new Horse("Fort Utopia", 10));
		horseService.save(new Horse("Count Sheep", 9));
		horseService.save(new Horse("Ms Traitour", 4));
		horseService.save(new Horse("Real Princess", 3));
		horseService.save(new Horse("Pa Kettle", 5));
		horseService.save(new Horse("Gin Stinger", 6));
		
		//initalize BillsInventories and store in DB.
		inventoryService.save(new BillInventory(1, 10));
		inventoryService.save(new BillInventory(5, 10));
		inventoryService.save(new BillInventory(10, 10));
		inventoryService.save(new BillInventory(20, 10));
		inventoryService.save(new BillInventory(100, 10));
		
		horseWinnerService.save(winnerHorse);
		
	}

}
