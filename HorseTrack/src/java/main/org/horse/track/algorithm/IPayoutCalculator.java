package org.horse.track.algorithm;

import java.util.List;
import java.util.Map;

import org.horse.track.model.BillInventory;

public interface IPayoutCalculator {
	
	public Integer calculate(Integer payoutAmount, List<BillInventory> inventoryList, Map<Integer, Integer> dispensMap);

}
