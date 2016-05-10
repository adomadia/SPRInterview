package org.horse.track.support;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.horse.track.algorithm.IPayoutCalculator;
import org.horse.track.io.IWritable;
import org.horse.track.model.BillInventory;
import org.horse.track.model.Horse;

public class PayoutSupport {
	
	private IPayoutCalculator calculator;


	public PayoutSupport(IPayoutCalculator calculator){
		this.calculator = calculator;
	}
	
	/**
	 * Calculate payout distribution.
	 * @param horseOdds
	 * @param betAmount
	 * @return null if insufficient fund or payout distribution
	 */
	public Integer calculatePayout(Integer payoutAmount, List<BillInventory> inventoryList, Map<Integer, Integer> dispensMap) {

		return calculator.calculate(payoutAmount, inventoryList, dispensMap);
	}
	
	
	/**
	 * Display Payout message and dispensing inventory.
	 * 
	 * @param payoutMap
	 * @param horse
	 */
	public void printPayout(Map<Integer, Integer> payoutMap, Horse horse, IWritable out) {

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
}
