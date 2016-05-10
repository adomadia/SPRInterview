package org.horse.track.command.impl;

import java.util.StringTokenizer;

import org.horse.track.command.ICommandExecutable;
import org.horse.track.command.ICommandValidator;
import org.horse.track.service.BillInventoryService;

/**
 * Represent as Restock command
 * A RestockCommand is used to restock the cash inventory.
 * Restock command key is 'Q' or 'q'
 
 * @author Ashvin Domadia
 */
public class RestockCommand implements ICommandExecutable, ICommandValidator{

	public final static String COMMAND_KEYWORD = "R";
	
	private final String syntax;
	
	private BillInventoryService inventoryService;
	
	/**
	 * Constructor takes command-syntax as an argument.
	 * @param syntax
	 */
	public RestockCommand(String syntax, BillInventoryService inventoryService) {
		this.syntax = syntax.trim();
		this.inventoryService = inventoryService;
	}

	
	/**
	 * Method validates the R command syntax.
	 */
	@Override
	public void validate() {

		StringTokenizer tokenizer = new StringTokenizer(syntax);
		
		if(tokenizer.countTokens() != 1){
			throw new IllegalArgumentException("Invalid Command: " + syntax);
		}
	}

	/**
	 *  Restocks the cash inventory.
	 *  Display horse list and cash inventory.
	 */
	@Override
	public boolean execute() {
		inventoryService.restock();
		return true;
	}

}
