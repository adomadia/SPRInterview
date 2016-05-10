package org.horse.track.algorithm.impl;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.horse.track.algorithm.IPayoutCalculator;
import org.horse.track.model.BillInventory;

public class IterativePayoutCalculator implements IPayoutCalculator{

	@Override
	public Integer calculate(Integer payoutAmount, List<BillInventory> inventoryList,
			Map<Integer, Integer> dispensMap) {

		Stack<BillInventory> billInventoryStack = new Stack<BillInventory>();

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

		return payoutAmount;
	}

}
