package org.horse.track.command.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
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
import org.horse.track.singleton.DisplayInventory;
import org.horse.track.util.StringUtils;

/**
 * Represent as Wager command A WagerCommand is used to specifies the horse
 * wagered on and the amount of the bet. Wager command syntax is HorseNo amount.
 * amount value should be positive natural number amount value zero is not
 * allowed.
 * 
 * @author Ashvin Domadia
 */
public class WagerCommand implements ICommand, IValidator {

	private final String syntax;
	private IMessage out;
	private boolean isValidated = false;

	private final static HorseWinnerService winnerService = new HorseWinnerSeriveImpl(
			new HorseWinnerDaoImp(CollectionDB.getInstance()));

	private final static HorseService horseService = new HorseServiceImpl(new HorseDaoImpl(CollectionDB.getInstance()),
			winnerService);

	private final static BillInventoryService inventoryService = new BillInventoryServiceImpl(
			new BillInventoryDaoImpl(CollectionDB.getInstance()));

	/**
	 * Constructor takes command-syntax as an argument.
	 * 
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
	public void execute() {

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
				DisplayInventory.getInstance().display();
			} 
			else { //Winner horse 

				Map<Integer, Integer> payoutMap = calculatePayout(horse.getOdds(), betAmount);

				if (payoutMap != null) {
					Set<Integer> payOutKeys = payoutMap.keySet();

					for (Integer key : payOutKeys) {
						Integer value = payoutMap.get(key);
						BillInventory inventory = inventoryService.findById(key);
						inventory.setInventory(inventory.getInventory() - value);
						inventoryService.save(inventory);
					}
					printPayout(payoutMap, horse);
				} else {
					out.write("Insufficient Funds: " + (horse.getOdds() * betAmount) + "\n");
				}

				DisplayInventory.getInstance().display();
			}
		}
	}

	/**
	 * Display Payout message and dispensing inventory.
	 * 
	 * @param payoutMap
	 * @param horse
	 */
	private void printPayout(Map<Integer, Integer> payoutMap, Horse horse) {

		StringBuilder payoutMessage = new StringBuilder();

		Set<Integer> payOutKeys = payoutMap.keySet();

		Integer payoutAmount = 0;

		for (Integer key : payOutKeys) {
			Integer value = payoutMap.get(key);
			payoutMessage.append("$" + key + "," + value + "\n");
			payoutAmount += (key * value);
		}

		out.write("Payout: " + horse.getName() + ",$" + payoutAmount + "\n");
		out.write("Dispensing: \n");
		out.write(payoutMessage.toString());

	}

	/**
	 * Calculate payout distribution.
	 * @param horseOdds
	 * @param betAmount
	 * @return null if insufficient fund or payout distribution
	 */
	public Map<Integer, Integer> calculatePayout(Integer horseOdds, Integer betAmount) {

		Integer payoutAmount = horseOdds * betAmount;
		List<BillInventory> inventoryList = inventoryService.findAll();

		Stack<BillInventory> billInventoryStack = new Stack<BillInventory>();

		Map<Integer, Integer> dispensMap = new TreeMap<Integer, Integer>();

		for (BillInventory inventory : inventoryList) {
			billInventoryStack.push(inventory);
		}

		while (billInventoryStack.size() != 0) {
			BillInventory inventory = billInventoryStack.pop();
			Integer totalDenomBillsNeeded = payoutAmount / inventory.getDenomination().getValue();
			Integer dispensBillsCount = 0;
			if (inventory.getInventory() >= totalDenomBillsNeeded) {
				dispensBillsCount = totalDenomBillsNeeded;

			} else {
				Integer billShort = inventory.getInventory() - totalDenomBillsNeeded;
				dispensBillsCount = totalDenomBillsNeeded + billShort;
			}
			payoutAmount = payoutAmount - (dispensBillsCount * inventory.getDenomination().getValue());
			dispensMap.put(inventory.getDenomination().getValue(), dispensBillsCount);
		}

		if (payoutAmount == 0) {
			return dispensMap;
		}

		return null;
	}
}
