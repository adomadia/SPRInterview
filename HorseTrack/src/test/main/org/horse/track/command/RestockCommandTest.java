package org.horse.track.command;

import org.horse.track.command.impl.RestockCommand;
import org.horse.track.core.CommandParser;
import org.horse.track.io.IWritable;
import org.horse.track.service.BillInventoryService;
import org.horse.track.service.impl.BillInventoryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class RestockCommandTest {

	private BillInventoryService inventoryServiceMock;
	
	private CommandParser outMock;
	
	@Before
	public void setUp() throws Exception {
	
		outMock = Mockito.mock(CommandParser.class);
		
		this.inventoryServiceMock = Mockito.mock(BillInventoryServiceImpl.class);
	}

	@Test
	public void testRestock() {
		RestockCommand command = new RestockCommand("r", inventoryServiceMock);
		command.validate();
		command.execute();
		Mockito.verify(inventoryServiceMock).restock();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidationFail() {
		RestockCommand command = new RestockCommand("r 1", inventoryServiceMock);
		command.validate();
	}
}
