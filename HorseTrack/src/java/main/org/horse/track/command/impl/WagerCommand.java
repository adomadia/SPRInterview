package org.horse.track.command.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.horse.track.algorithm.impl.IterativePayoutCalculator;
import org.horse.track.command.ICommandExecutable;
import org.horse.track.command.ICommandValidator;
import org.horse.track.io.IWritable;
import org.horse.track.model.BillInventory;
import org.horse.track.model.Horse;
import org.horse.track.service.BillInventoryService;
import org.horse.track.service.HorseService;
import org.horse.track.service.HorseWinnerService;
import org.horse.track.support.PayoutSupport;
import org.horse.track.util.StringUtils;

/**
 * Represent as Wager command A WagerCommand is used to specifies the horse
 * wagered on and the amount of the bet. Wager command syntax is HorseNo amount.
 * amount value should be positive natural number amount value zero is not
 * allowed.
 * 
 * @author Ashvin Domadia
 */
public class WagerCommand implements ICommandExecutable, ICommandValidator {

	private final String syntax;
	private IWritable out;
	private boolean isValidated = false;

	private HorseWinnerService winnerService ; 
	
	private HorseService horseService;

	private BillInventoryService inventoryService;
	
	private PayoutSupport payoutSupport = new PayoutSupport(new IterativePayoutCalculator());
	
	/**
	 * Constructor takes command-syntax as an argument.
	 * 
	 * @param syntax
	 */
	public WagerCommand(String syntax, IWritable out, 
			HorseWinnerService winnerService, HorseService horseService, 
			BillInventoryService inventoryService) {
	
		this.syntax = syntax;
		this.out = out;
		this.winnerService = winnerService;
		this.horseService = horseService;
		this.inventoryService = inventoryService;
	}

	/**
	 * Method validates the Wager command syntax.
	 */
	@Override
	public void validate() {
		StringTokenizer tokenizer = new StringTokenizer(syntax);

		if (tokenizer.countTokens() != 2) {
			throw new IllegalArgumentException("Invalid Command: " + syntax);
		}

		tokenizer.nextToken();
		String secondToken = tokenizer.nextToken();

		// throw exception if syntax second argument is not greater than 0
		// natural number.
		if (!(StringUtils.isNaturalNumber(secondToken) && Integer.parseInt(secondToken) > 0)) {
			throw new IllegalArgumentException("Invalid Bet: " + secondToken);
		}

		isValidated = true;

	}

	/**
	 * Execute wager command to calculate payout 
	 * Dispense cash from the cash inventory.
	 * Display message Insufficient Funds message if
	 * the machine does not have enough cash on hand to make a complete and
	 * exact payout.
	 */
	@Override
	public boolean execute() {

		if (isValidated) {

			StringTokenizer tokenizer = new StringTokenizer(syntax);
			Long horseId = Long.parseLong(tokenizer.nextToken());
			Integer betAmount = Integer.parseInt(tokenizer.nextToken());

			Horse horse = horseService.findById(horseId);

			if (horse == null) {
				throw new IllegalArgumentException("Invalid Horse Number: " + horseId);
			}

			//not a winner horse
			if (horseId != winnerService.getWinner().getId()) {
				out.write("No Payout: " + horse.getName() + "\n");
			} 
			else { //Winner horse 

				List<BillInventory> inventoryList = inventoryService.findAll();
				Map<Integer, Integer> payoutMap = new TreeMap<Integer, Integer>();
						
				Integer remainingAmount = payoutSupport.calculatePayout(horse.getOdds()*betAmount, inventoryList, payoutMap);

				if (remainingAmount == 0) {
					Set<Integer> payOutKeys = payoutMap.keySet();

					for (Integer key : payOutKeys) {
						Integer value = payoutMap.get(key);
						BillInventory inventory = inventoryService.findById(key);
						inventory.setInventory(inventory.getInventory() - value);
						inventoryService.save(inventory);
					}
					payoutSupport.printPayout(payoutMap, horse, out);
				} else {
					out.write("Insufficient Funds: " + (horse.getOdds() * betAmount) + "\n");
				}
			}
		}
		return true;
	}	
}
