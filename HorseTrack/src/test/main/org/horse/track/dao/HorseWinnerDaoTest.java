package org.horse.track.dao;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.horse.track.dao.impl.HorseWinnerDaoImp;
import org.horse.track.fakedb.CollectionDB;
import org.horse.track.model.Horse;
import org.junit.Before;
import org.junit.Test;

public class HorseWinnerDaoTest {

	private CollectionDB db;
	private ICRUDOperations<Integer, Horse> winnerDao;
	
	@Before
	public void setUp(){
		db = CollectionDB.getInstance();
		winnerDao = new HorseWinnerDaoImp(db);
		
	}
	
	@Test()
	public void testfindOneNoParams(){
		db.setWinnerHorse(new Horse("That Darn Gray Cat", 5));
		Horse target = winnerDao.findOne();
		assertThat(db.getWinnerHorse(), is(equalTo(target)));
		
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testFindOne() {
		winnerDao.findOne(1);
	}
	
	
	@Test(expected = UnsupportedOperationException.class)
	public void testFindAll() {
		winnerDao.findAll();
	}
	
	@Test
	public void testSave(){
		
		Horse target = new Horse("That Darn Gray Cat", 5);
		target = winnerDao.save(target);
		assertThat(db.getWinnerHorse(), is(equalTo(target)));
		
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testTrunc(){
		winnerDao.trunc();
	}
	
	

}
