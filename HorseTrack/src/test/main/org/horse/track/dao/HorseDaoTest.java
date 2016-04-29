package org.horse.track.dao;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.horse.track.dao.impl.HorseDaoImpl;
import org.horse.track.fakedb.CollectionDB;
import org.horse.track.model.Horse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HorseDaoTest {

	private CollectionDB db;
	private ICRUDOperations<Long, Horse> horseDao;
	
	@Before
	public void setUp(){
		db = CollectionDB.getInstance();
		horseDao = new HorseDaoImpl(db);
		
	}
	
	@Test
	public void testFindOne() {
		
		Horse fixture = new Horse("That Darn Gray Cat", 5);
		Long id = db.getNextSequence();
		db.getHorseMap().put(id, fixture);

		Horse target = horseDao.findOne(id);
		
		assertThat(fixture, is(equalTo(target)));

	}
	
	
	@Test
	public void testFindAll() {
		
		Horse fixture1 = new Horse("That Darn Gray Cat", 5);
		Long id1 = db.getNextSequence();
		Horse fixture2 = new Horse("Fort Utopia", 10);
		Long id2 = db.getNextSequence();

		db.getHorseMap().put(id1, fixture1);
		db.getHorseMap().put(id2, fixture2);


		Iterator<Horse> target = horseDao.findAll();
		List<Horse> horseList = new ArrayList<Horse>();
		while(target.hasNext()){
			horseList.add(target.next());
		}
		assertThat(horseList, hasSize(2));

	}
	
	@Test
	public void testSave(){
		
		Horse target = new Horse("That Darn Gray Cat", 5);
		target = horseDao.save(target);
		assertThat(db.getHorseMap().get(target.getId()), is(equalTo(target)));
		
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testfindOne(){
		horseDao.findOne();
	}

	@After
	public void tearDown(){
		horseDao.trunc();
	}
}
