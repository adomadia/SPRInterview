package org.horse.track.main;


import org.horse.track.command.ICommandExecutable;
import org.horse.track.command.impl.QuitCommand;
import org.horse.track.core.CommandParser;
import org.horse.track.core.CommandProcessor;
import org.horse.track.dao.impl.BillInventoryDaoImpl;
import org.horse.track.dao.impl.HorseDaoImpl;
import org.horse.track.dao.impl.HorseWinnerDaoImp;
import org.horse.track.factory.CommandFactory;
import org.horse.track.fakedb.CollectionDB;
import org.horse.track.initializer.DataInitializer;
import org.horse.track.io.IWritable;
import org.horse.track.io.impl.ConsoleReader;
import org.horse.track.io.impl.ConsoleWriter;
import org.horse.track.service.BillInventoryService;
import org.horse.track.service.HorseService;
import org.horse.track.service.HorseWinnerService;
import org.horse.track.service.impl.BillInventoryServiceImpl;
import org.horse.track.service.impl.HorseServiceImpl;
import org.horse.track.service.impl.HorseWinnerSeriveImpl;
import org.horse.track.support.InventorySupport;

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
		
		final HorseWinnerService winnerService = 
				new HorseWinnerSeriveImpl(new HorseWinnerDaoImp(CollectionDB.getInstance()));
		final HorseService horseService =  
				new HorseServiceImpl(new HorseDaoImpl(CollectionDB.getInstance()), winnerService);
		final BillInventoryService inventoryService = new BillInventoryServiceImpl(
				new BillInventoryDaoImpl(CollectionDB.getInstance()));

		final IWritable consoleWriter = new ConsoleWriter();
		final CommandFactory commandFactory = new CommandFactory(winnerService, horseService, inventoryService, consoleWriter);
		final CommandParser commandGenerator = new CommandParser(new ConsoleReader(), commandFactory);
		final CommandProcessor processor = new CommandProcessor();
		final InventorySupport inventorySupport = new InventorySupport(inventoryService, horseService, consoleWriter);
		
		ICommandExecutable cmd = null;

		//Initial data setup.
		DataInitializer.initialSetup();
	
		//Display horse and cash inventory.
		inventorySupport.display();
		
		do{
			try {

				//Read a command.
				cmd = commandGenerator.readCommand();
				
				//Process the command.
				if(processor.process(cmd)){
					inventorySupport.display();
				}
			} catch (IllegalArgumentException ex) {
				consoleWriter.write(ex.getMessage() + "\n");
				
			}
		}while(!(cmd instanceof QuitCommand));
	}


}
