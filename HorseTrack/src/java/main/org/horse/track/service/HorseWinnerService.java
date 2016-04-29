package org.horse.track.service;

import org.horse.track.model.Horse;

public interface HorseWinnerService {
	
	public Horse getWinner();
	
	public void save(Horse horse);

}
