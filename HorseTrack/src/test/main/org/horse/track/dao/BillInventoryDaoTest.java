package org.horse.track.dao;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.horse.track.dao.impl.BillInventoryDaoImpl;
import org.horse.track.fakedb.CollectionDB;
import org.horse.track.model.BillInventory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BillInventoryDaoTest {

	private CollectionDB db;
	private ICRUDOperations<Integer, BillInventory> billInventoryDao;
	
	@Before
	public void setUp(){
		db = CollectionDB.getInstance();
		billInventoryDao = new BillInventoryDaoImpl(db);
		
	}
	
	@Test
	public void testFindOne() {
		
		BillInventory fixture = new BillInventory(1, 10);
		db.getBillsInventoryMap().put(fixture.getDenomination().getValue(), fixture);

		BillInventory target = billInventoryDao.findOne(fixture.getDenomination().getValue());
		
		assertThat(fixture, is(equalTo(target)));

	}
	
	
	@Test
	public void testFindAll() {
		
		BillInventory fixture1 = new BillInventory(1, 10);
		BillInventory fixture2 = new BillInventory(5, 10);

		db.getBillsInventoryMap().put(fixture1.getDenomination().getValue(), fixture1);
		db.getBillsInventoryMap().put(fixture2.getDenomination().getValue(), fixture2);


		Iterator<BillInventory> target = billInventoryDao.findAll();
		List<BillInventory> billInventoryList = new ArrayList<BillInventory>();
		while(target.hasNext()){
			billInventoryList.add(target.next());
		}
		assertThat(billInventoryList, hasSize(2));

	}
	
	@Test
	public void testSave(){
		
		BillInventory target = new BillInventory(1, 10);
		billInventoryDao.save(target);
		assertThat(db.getBillsInventoryMap().get(target.getDenomination().getValue()), is(equalTo(target)));
		
	}
	
	
	@Test(expected = UnsupportedOperationException.class)
	public void testfindOneNoParams(){
		billInventoryDao.findOne();
	}

	@After
	public void tearDown(){
		billInventoryDao.trunc();
	}
}
