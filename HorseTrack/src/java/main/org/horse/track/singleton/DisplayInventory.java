package org.horse.track.singleton;

import org.horse.track.dao.impl.BillInventoryDaoImpl;
import org.horse.track.dao.impl.HorseDaoImpl;
import org.horse.track.dao.impl.HorseWinnerDaoImp;
import org.horse.track.fakedb.CollectionDB;
import org.horse.track.service.BillInventoryService;
import org.horse.track.service.HorseService;
import org.horse.track.service.HorseWinnerService;
import org.horse.track.service.impl.BillInventoryServiceImpl;
import org.horse.track.service.impl.HorseServiceImpl;
import org.horse.track.service.impl.HorseWinnerSeriveImpl;

/**
 * Singleton implementation
 * Display Inventory info to screen.
 * @author Ashvin Domadia
 *
 */
public class DisplayInventory {

	private final static DisplayInventory INSTANCE = new DisplayInventory();

	private final CollectionDB db = CollectionDB.getInstance();
	private final BillInventoryService inventoryService = new BillInventoryServiceImpl(new BillInventoryDaoImpl(db));
	private final HorseWinnerService winnerService = new HorseWinnerSeriveImpl(new HorseWinnerDaoImp(db));
	private final HorseService horseService = new HorseServiceImpl(new HorseDaoImpl(db), winnerService);
	private final CommandPrompt shell = CommandPrompt.getInstance();
	
	
	private DisplayInventory(){
		
	}
	
	/**
	 * Singleton implementation
	 * @return CommandPrompt
	 */
	public static DisplayInventory getInstance(){
		return INSTANCE;
	}
	
	/**
	 * display horse and cash inventory.
	 */
	public void display(){
		shell.write(inventoryService.printInventory());
		shell.write(horseService.printMenu());
	}
	
	
	

	
}
