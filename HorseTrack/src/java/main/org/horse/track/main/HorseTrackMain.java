package org.horse.track.main;


import org.horse.track.command.ICommand;
import org.horse.track.command.impl.QuitCommand;
import org.horse.track.command.impl.WinnerCommand;
import org.horse.track.dao.impl.BillInventoryDaoImpl;
import org.horse.track.dao.impl.HorseDaoImpl;
import org.horse.track.dao.impl.HorseWinnerDaoImp;
import org.horse.track.fakedb.CollectionDB;
import org.horse.track.service.BillInventoryService;
import org.horse.track.service.DataStagingService;
import org.horse.track.service.HorseService;
import org.horse.track.service.HorseWinnerService;
import org.horse.track.service.impl.BillInventoryServiceImpl;
import org.horse.track.service.impl.DataStagingServiceImpl;
import org.horse.track.service.impl.HorseServiceImpl;
import org.horse.track.service.impl.HorseWinnerSeriveImpl;
import org.horse.track.singleton.CommandProcessor;
import org.horse.track.singleton.CommandPrompt;
import org.horse.track.singleton.DisplayInventory;

public class HorseTrackMain {
	
	public static void main(String[] args) {

		ICommand cmd = null;

		initialSetup();
		
		final CommandPrompt shell = CommandPrompt.getInstance();
		final CommandProcessor processor = CommandProcessor.getInstance();
	
		DisplayInventory.getInstance().display();
		
		do{
			try {
				cmd = shell.readCommand();
				processor.process(cmd);
			} catch (IllegalArgumentException ex) {
				shell.write(ex.getMessage() + "\n");
				
			}
		}while(!(cmd instanceof QuitCommand));
	}

	/**
	 * run initial setup to create horse, bill inventory and set first horse a winner.
	 */
	public static void initialSetup(){
		
		final CollectionDB db = CollectionDB.getInstance();
		
		final HorseWinnerService winnerService = new HorseWinnerSeriveImpl(new HorseWinnerDaoImp(db));
		
		final DataStagingService stagingService = new DataStagingServiceImpl(
				new HorseServiceImpl(new HorseDaoImpl(db), winnerService), 
				new BillInventoryServiceImpl(new BillInventoryDaoImpl(db)),
				winnerService);
		
		stagingService.initalize();
	}

	/*
	public static void initialDisplay(CommandPrompt shell){
		
		final CollectionDB db = CollectionDB.getInstance();
		
		final BillInventoryService inventoryService = new BillInventoryServiceImpl(new BillInventoryDaoImpl(db));
		final HorseWinnerService winnerService = new HorseWinnerSeriveImpl(new HorseWinnerDaoImp(db));

		final HorseService horseService = new HorseServiceImpl(new HorseDaoImpl(db), winnerService);
		
		shell.write(inventoryService.printInventory());
		shell.write(horseService.printMenu());
	}*/
}
