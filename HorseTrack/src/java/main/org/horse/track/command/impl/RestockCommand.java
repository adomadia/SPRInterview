package org.horse.track.command.impl;

import java.util.StringTokenizer;

import org.horse.track.command.ICommand;
import org.horse.track.command.IValidator;
import org.horse.track.dao.impl.BillInventoryDaoImpl;
import org.horse.track.dao.impl.HorseDaoImpl;
import org.horse.track.fakedb.CollectionDB;
import org.horse.track.service.BillInventoryService;
import org.horse.track.service.impl.BillInventoryServiceImpl;

/**
 * 
 * @author Ashvin Domadia
 * TODO: write descritpion
 *
 */
public class RestockCommand implements ICommand, IValidator{

	public final static String COMMAND_KEYWORD = "R";
	
	private final String syntax;
	
	private BillInventoryService inventoryService = new BillInventoryServiceImpl(
				new BillInventoryDaoImpl(CollectionDB.getInstance()));
	
	/**
	 * Constructor takes command-syntax as an argument.
	 * @param syntax
	 */
	public RestockCommand(String syntax) {
		this.syntax = syntax.trim();
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
	 */
	@Override
	public void execute() {
		inventoryService.restock();
	}

}
