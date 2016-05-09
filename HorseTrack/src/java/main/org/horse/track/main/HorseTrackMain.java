package org.horse.track.main;


import org.horse.track.command.ICommand;
import org.horse.track.command.impl.QuitCommand;
import org.horse.track.core.CommandProcessor;
import org.horse.track.core.CommandPrompt;
import org.horse.track.core.DisplayInventory;
import org.horse.track.dao.impl.BillInventoryDaoImpl;
import org.horse.track.dao.impl.HorseDaoImpl;
import org.horse.track.dao.impl.HorseWinnerDaoImp;
import org.horse.track.fakedb.CollectionDB;
import org.horse.track.service.DataStagingService;
import org.horse.track.service.HorseWinnerService;
import org.horse.track.service.impl.BillInventoryServiceImpl;
import org.horse.track.service.impl.DataStagingServiceImpl;
import org.horse.track.service.impl.HorseServiceImpl;
import org.horse.track.service.impl.HorseWinnerSeriveImpl;

/***
 * Application to run the simulator.
 * @author Ashvin Domadia
 *
 */
public class HorseTrackMain {
	/**
	 * main method to start simulator.
	 * @param args
	 */
	public static void main(String[] args) {

		ICommand cmd = null;

		//Initial data setup.
		initialSetup();
		
		final CommandPrompt shell = CommandPrompt.getInstance();
		final CommandProcessor processor = CommandProcessor.getInstance();
	
		//Display horse and cash inventory.
		DisplayInventory.getInstance().display();
		
		do{
			try {
				//Read a command.
				cmd = shell.readCommand();
				//Process the command.
				processor.process(cmd);
			} catch (IllegalArgumentException ex) {
				shell.write(ex.getMessage() + "\n");
				
			}
		}while(!(cmd instanceof QuitCommand));
	}

	/**
	 * Create initial setup and stage data for race horse, cash inventory and winner horse.
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
}
