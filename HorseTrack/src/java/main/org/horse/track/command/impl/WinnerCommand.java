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
import org.horse.track.util.StringUtils;


/**
 * 
 * @author Ashvin Domadia
 * TODO: write descritpion
 *
 */
public class WinnerCommand implements ICommand, IValidator {
	
	public final static String COMMAND_KEYWORD = "W";
	
	private boolean isValidated = false;
	private final String syntax;
	
	private final static HorseWinnerService winnerService = 
			new HorseWinnerSeriveImpl(new HorseWinnerDaoImp(CollectionDB.getInstance()));

	private final static HorseService horseService = 
			new HorseServiceImpl(new HorseDaoImpl(CollectionDB.getInstance()), winnerService);
	
	

	/**
	 * Constructor takes command-syntax as an argument.
	 * @param syntax
	 */
	public WinnerCommand(String syntax) {
			this.syntax = syntax;
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
		}
		
	}

	
	
}
