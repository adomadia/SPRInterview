package org.horse.track.service;

import static org.junit.Assert.*;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import org.horse.track.dao.impl.BillInventoryDaoImpl;
import org.horse.track.dao.impl.HorseDaoImpl;
import org.horse.track.dao.impl.HorseWinnerDaoImp;
import org.horse.track.fakedb.CollectionDB;
import org.horse.track.service.impl.BillInventoryServiceImpl;
import org.horse.track.service.impl.DataStagingServiceImpl;
import org.horse.track.service.impl.HorseServiceImpl;
import org.horse.track.service.impl.HorseWinnerSeriveImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataStagingServiceTest {

	private CollectionDB db;
	private DataStagingService stagingService;
	private HorseWinnerService winnerService;
	private HorseService horseService;
	private BillInventoryService billInventoryService;
	
	
	@Before
	public void setUp() throws Exception {
		this.db = CollectionDB.getInstance();
		this.winnerService = new HorseWinnerSeriveImpl(new HorseWinnerDaoImp(db));
		this.billInventoryService = new BillInventoryServiceImpl(new BillInventoryDaoImpl(db));
		this.horseService = new HorseServiceImpl(new HorseDaoImpl(db), winnerService);
		this.stagingService = new DataStagingServiceImpl(horseService, billInventoryService, winnerService);
	}

	@Test
	public void testInitalize() {
		stagingService.initalize();
		assertThat(db.getBillsInventoryMap().entrySet(), hasSize(5));
		assertThat(db.getHorseMap().entrySet(), hasSize(7));
		assertThat(db.getWinnerHorse().getName(), is(equalTo("That Darn Gray Cat")));
	}

	@After
	public void tearDown(){
		horseService.removeAll();
		billInventoryService.removeAll();
	}
}
