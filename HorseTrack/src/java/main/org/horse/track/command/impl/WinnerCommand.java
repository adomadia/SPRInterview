package org.horse.track.command.impl;

import java.util.StringTokenizer;

import org.horse.track.command.ICommand;
import org.horse.track.command.IValidator;
import org.horse.track.dao.impl.HorseDaoImpl;
import org.horse.track.dao.impl.HorseWinnerDaoImp;
import org.horse.track.fakedb.CollectionDB;
import org.horse.track.model.Horse;
import org.horse.track.service.HorseService;
import org.horse.track.service.HorseWinnerService;
import org.horse.track.service.impl.HorseServiceImpl;
import org.horse.track.service.impl.HorseWinnerSeriveImpl;
import org.horse.track.singleton.DisplayInventory;
import org.horse.track.util.StringUtils;


/**
 * Represent as Winner command
 * A WinnerCommand is used to set the winner.
 * Restock command key is 'W horse#' or 'w horse#'
 
 * @author Ashvin Domadia
 */
public class WinnerCommand implements ICommand, IValidator {
	
	public final static String COMMAND_KEYWORD = "W";
	
	private boolean isValidated = false;
	private final String syntax;
	
	private HorseWinnerService winnerService ; 
	private HorseService horseService; 
	

	/**
	 * Constructor takes command-syntax as an argument.
	 * @param syntax
	 */
	public WinnerCommand(String syntax, HorseWinnerService winnerService, HorseService horseService ) {
			this.syntax = syntax;
			this.winnerService = winnerService;
			this.horseService = horseService;
	}

	
	/**
	 * Method validates the Q command syntax.
	 */
	@Override
	public void validate() {
		
		StringTokenizer tokenizer = new StringTokenizer(syntax);
		
		//throw exception if syntax arguments are not exactly two.
		if(tokenizer.countTokens() != 2){
			throw new IllegalArgumentException("Invalid Command: " + syntax);
		}

		tokenizer.nextToken();
		String secondToken = tokenizer.nextToken();
		
		//throw exception if syntax second argument is not greater than 0 natural number.
		if(!(StringUtils.isNaturalNumber(secondToken) 
				&& Integer.parseInt(secondToken) > 0)){
			throw new IllegalArgumentException("Invalid Horse Number: " + secondToken);
		}
		
		isValidated = true;
	}

	/**
	 * set the winner horse 
	 */
	@Override
	public void execute() {
		if(isValidated){
			StringTokenizer tokenizer = new StringTokenizer(syntax);
			tokenizer.nextToken();
			Long horseId = Long.parseLong(tokenizer.nextToken());
			Horse winnerHorse = horseService.findById(horseId);
			if(winnerHorse == null){
				throw new IllegalArgumentException("Invalid Horse Number: " + horseId);
			}
			winnerService.save(winnerHorse);
			DisplayInventory.getInstance().display();
		}
		
	}

	
	
}
