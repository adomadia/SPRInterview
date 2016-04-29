package org.horse.track.command.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.horse.track.command.ICommand;
import org.horse.track.command.IMessage;
import org.horse.track.command.IValidator;
import org.horse.track.dao.impl.BillInventoryDaoImpl;
import org.horse.track.dao.impl.HorseDaoImpl;
import org.horse.track.dao.impl.HorseWinnerDaoImp;
import org.horse.track.fakedb.CollectionDB;
import org.horse.track.model.BillInventory;
import org.horse.track.model.Horse;
import org.horse.track.service.BillInventoryService;
import org.horse.track.service.HorseService;
import org.horse.track.service.HorseWinnerService;
import org.horse.track.service.impl.BillInventoryServiceImpl;
import org.horse.track.service.impl.HorseServiceImpl;
import org.horse.track.service.impl.HorseWinnerSeriveImpl;
import org.horse.track.util.StringUtils;


/**
 * 
 * @author Ashvin Domadia
 * TODO: write descritpion
 *
 */
public class WagerCommand implements ICommand, IValidator{
	
	private final String syntax;
	private IMessage out;
	private boolean isValidated = false;

	private final static HorseWinnerService winnerService = 
			new HorseWinnerSeriveImpl(new HorseWinnerDaoImp(CollectionDB.getInstance()));

	private final static HorseService horseService = 
			new HorseServiceImpl(new HorseDaoImpl(CollectionDB.getInstance()), winnerService);
	
	private final static BillInventoryService inventoryService = 
			new BillInventoryServiceImpl(new BillInventoryDaoImpl(CollectionDB.getInstance()));

	
	/**
	 * Constructor takes command-syntax as an argument.
	 * @param syntax
	 */
	public WagerCommand(String syntax, IMessage out) {
		this.syntax = syntax;
		this.out = out;
	}

	
	/**
	 * Method validates the Wager command syntax.
	 */
	@Override
	public void validate() {
	StringTokenizer tokenizer = new StringTokenizer(syntax);
		
		if(tokenizer.countTokens() != 2){
			throw new IllegalArgumentException("Invalid Command: " + syntax);
		}
		
		tokenizer.nextToken();
		String secondToken = tokenizer.nextToken();
		
		//throw exception if syntax second argument is not greater than 0 natural number.
		if(!(StringUtils.isNaturalNumber(secondToken) 
				&& Integer.parseInt(secondToken) > 0)){
			throw new IllegalArgumentException("Invalid Bet: " + secondToken);
		}
		
		isValidated = true;
		
	}

	@Override
	public void execute() {
		
		if(isValidated){
			
			StringTokenizer tokenizer = new StringTokenizer(syntax);
			Long horseId = Long.parseLong(tokenizer.nextToken());
			Integer betAmount = Integer.parseInt(tokenizer.nextToken());
			
			Horse horse = horseService.findById(horseId);
			
			if(horse == null){
				throw new IllegalArgumentException("Invalid Horse Number: " + horseId);
			}
			
			if(horseId != winnerService.getWinner().getId()){
				out.write("No Payout: " + horseId + "\n");
			}
			
			 Map<Integer, Integer> payout = calculatePayout( horse.getOdds(), betAmount);
			 if(payout == null){
				 System.out.println("Cant dispense");
			 }
			 else {
				 System.out.println("Despensing");
			 }
		}
		
	}

	public Map<Integer, Integer> calculatePayout(Integer horseOdds, Integer betAmount){
		
		Integer payoutAmount = horseOdds * betAmount ;
		List<BillInventory> inventoryList = inventoryService.findAll();
		
		Stack<BillInventory> billInventoryStack = new Stack<BillInventory>();
		
		Map<Integer, Integer> dispensMap = new TreeMap<Integer, Integer>();
		
		for(BillInventory inventory : inventoryList){
			billInventoryStack.push(inventory);
		}
		
		while(billInventoryStack.size() != 0){
			BillInventory inventory = billInventoryStack.pop();
			Integer totalDenomBillsNeeded = payoutAmount / inventory.getDenomination().getValue();
			Integer dispensBillsCount = 0;
			if(inventory.getInventory() >= totalDenomBillsNeeded){
				dispensBillsCount = totalDenomBillsNeeded;
				
			}
			else {
				Integer billShort = inventory.getInventory() - totalDenomBillsNeeded;
				dispensBillsCount = totalDenomBillsNeeded + billShort;
			}
			payoutAmount = payoutAmount - (dispensBillsCount * inventory.getDenomination().getValue());
			dispensMap.put(inventory.getDenomination().getValue(), dispensBillsCount);
		}
		
		
		if(payoutAmount == 0){
			return dispensMap;
		}
		
		return null;
		
	}

}
