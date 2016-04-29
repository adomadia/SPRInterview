package org.horse.track.service;

import java.util.List;

import org.horse.track.model.Horse;

public interface HorseService {
	
	public Horse findById(Long id);
	
	public void save(Horse horse);
	
	public void removeAll();
	
	public List<Horse> findAll();
	
	public String printMenu();

}
