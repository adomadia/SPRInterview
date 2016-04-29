package org.horse.track.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.horse.track.command.impl.WinnerCommand;
import org.horse.track.dao.ICRUDOperations;
import org.horse.track.model.BillInventory;
import org.horse.track.model.Horse;
import org.horse.track.service.HorseService;
import org.horse.track.service.HorseWinnerService;

public class HorseServiceImpl implements HorseService{
	
	private ICRUDOperations<Long, Horse> dao;
	private HorseWinnerService winnerService;
	
	public HorseServiceImpl(ICRUDOperations<Long, Horse> dao, HorseWinnerService winnerService) {
		this.dao = dao;
		this.winnerService = winnerService;
	}
	
	@Override
	public Horse findById(Long id) {
		return dao.findOne(id);
	}

	@Override
	public void save(Horse horse) {
		dao.save(horse); 
		
	}

	@Override
	public void removeAll() {
		dao.trunc();
	}

	@Override
	public List<Horse> findAll() {

		List<Horse> list = new ArrayList<Horse>();
		Iterator<Horse> iterator = dao.findAll();
		
		while(iterator.hasNext()){
			list.add(iterator.next());
		}
		
		return list;
	}

	@Override
	public String printMenu() {

		List<Horse> horses = findAll();
		StringBuilder builder = new StringBuilder();
		builder.append("Horses:\n");
		
		for(Horse horse : horses){
			builder.append(horse.getId() + ", " + horse.getName() + ", " );
			builder.append((winnerService.getWinner().getId() == horse.getId())?"won":"lost");
			builder.append("\n");
		}
		return builder.toString();
	}
	
}
