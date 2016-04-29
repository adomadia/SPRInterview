package org.horse.track.singleton;

import java.util.Scanner;
import java.util.StringTokenizer;

import org.horse.track.command.ICommand;
import org.horse.track.command.IMessage;
import org.horse.track.command.impl.BlankCommand;
import org.horse.track.command.impl.QuitCommand;
import org.horse.track.command.impl.RestockCommand;
import org.horse.track.command.impl.WagerCommand;
import org.horse.track.command.impl.WinnerCommand;
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
import org.horse.track.util.StringUtils;

/**
 * CommandPrompt performs read and write.
 * @author Ashvin Domadia
 *
 */
public class CommandPrompt implements IMessage{

	private final Scanner scanner = new Scanner(System.in);
	
	private final static CommandPrompt INSTANCE = new CommandPrompt();
	private final static HorseWinnerService winnerService = 
			new HorseWinnerSeriveImpl(new HorseWinnerDaoImp(CollectionDB.getInstance()));

	private final static HorseService horseService =  
			new HorseServiceImpl(new HorseDaoImpl(CollectionDB.getInstance()), winnerService);

	private static BillInventoryService inventoryService = new BillInventoryServiceImpl(
			new BillInventoryDaoImpl(CollectionDB.getInstance()));


	private CommandPrompt(){
		
	}
	
	/**
	 * Singleton implementation
	 * @return CommandPrompt
	 */
	public static CommandPrompt getInstance(){
		return INSTANCE;
	}

	/**
	 * Method reads a command from console and return command object.
	 * Method thows IllegalArgumentException if a command is not supported.
	 * 
	 * @return ICommand
	 */
	public final ICommand readCommand(){

		String cmdSyntax = scanner.nextLine();
		
		StringTokenizer tokenizer = new StringTokenizer(cmdSyntax);
		
		//return BlankCommand object if its empty line.
		if(tokenizer.countTokens() == 0){
			return new BlankCommand(cmdSyntax);
		}
		
		String cmd = tokenizer.nextToken();
		
		// return RestockCommand boject if its Q command
		if(QuitCommand.COMMAND_KEYWORD.equalsIgnoreCase(cmd)){
			QuitCommand quitCommand = new QuitCommand(cmdSyntax);
			quitCommand.validate();
			return quitCommand;
		}

		// return RestockCommand object if its R command.
		if(RestockCommand.COMMAND_KEYWORD.equalsIgnoreCase(cmd)){
			RestockCommand restockCommand = new RestockCommand(cmdSyntax, inventoryService);
			restockCommand.validate();
			return restockCommand;
		}
		
		// return RestockCommand object if its W command.
		if(WinnerCommand.COMMAND_KEYWORD.equalsIgnoreCase(cmd)){
			WinnerCommand winnerCommand = new WinnerCommand(cmdSyntax, winnerService, horseService);
			winnerCommand.validate();
			return winnerCommand;
		}
		
		
		// return WagerCommand object if its Wager command.
		if(StringUtils.isNaturalNumber(cmd) && Integer.parseInt(cmd) > 0){
			WagerCommand wagerCommand = 
					new WagerCommand(
							cmdSyntax,  getInstance(), 
							winnerService, horseService, 
							inventoryService);
			
			wagerCommand.validate();
			return wagerCommand;
		}
		
		//Throw exception if system doesn't support command
		throw new IllegalArgumentException("Invalid Command: " + cmdSyntax.trim());
	}
	
	/**
	 * Method accepts message and write it to console.
	 * @param message
	 */
	public void write(String message) {
		System.out.printf(message);
	}


}
