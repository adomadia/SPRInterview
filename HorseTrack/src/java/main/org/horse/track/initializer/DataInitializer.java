package org.horse.track.initializer;

import org.horse.track.dao.impl.BillInventoryDaoImpl;
import org.horse.track.dao.impl.HorseDaoImpl;
import org.horse.track.dao.impl.HorseWinnerDaoImp;
import org.horse.track.fakedb.CollectionDB;
import org.horse.track.service.DataStagingService;
import org.horse.track.service.HorseWinnerService;
import org.horse.track.service.impl.BillInventoryServiceImpl;
import org.horse.track.service.impl.DataStagingServiceImpl;
import org.horse.track.service.impl.HorseServiceImpl;
import org.horse.track.service.impl.HorseWinnerSeriveImpl;

public class DataInitializer {
	
	/**
	 * Create initial setup and stage data for race horse, cash inventory and winner horse.
	 */
	public static void initialSetup(){
		
		final CollectionDB db = CollectionDB.getInstance();
		
		final HorseWinnerService winnerService = new HorseWinnerSeriveImpl(new HorseWinnerDaoImp(db));
		
		final DataStagingService stagingService = new DataStagingServiceImpl(
				new HorseServiceImpl(new HorseDaoImpl(db), winnerService), 
				new BillInventoryServiceImpl(new BillInventoryDaoImpl(db)),
				winnerService);
		
		stagingService.initalize();
	}

}
