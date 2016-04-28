package org.horse.track.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.horse.track.dao.ICRUDOperations;
import org.horse.track.model.Horse;
import org.horse.track.service.HorseService;

public class HorseServiceImpl implements HorseService{

	private ICRUDOperations<Long, Horse> dao;
	
	public HorseServiceImpl(ICRUDOperations<Long, Horse> dao) {
		this.dao = dao;
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
	
}
