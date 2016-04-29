package org.horse.track.service.impl;

import org.horse.track.dao.ICRUDOperations;
import org.horse.track.model.Horse;
import org.horse.track.service.HorseWinnerService;

/**
 * 
 * @author Ashvin Domadia
 *
 */
public class HorseWinnerSeriveImpl implements HorseWinnerService{
	
	private ICRUDOperations<Integer, Horse> horseWinnerDao;
	
	public HorseWinnerSeriveImpl(ICRUDOperations<Integer, Horse> horseWinnerDao) {
		this.horseWinnerDao = horseWinnerDao;
	}
	

	@Override
	public Horse getWinner() {
		return horseWinnerDao.findOne();
	}

	@Override
	public void save(Horse winnerHorse) {
		horseWinnerDao.save(winnerHorse);
		
	}

}
