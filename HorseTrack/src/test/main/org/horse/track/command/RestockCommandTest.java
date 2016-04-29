package org.horse.track.command;

import org.horse.track.command.impl.RestockCommand;
import org.horse.track.service.BillInventoryService;
import org.horse.track.service.impl.BillInventoryServiceImpl;
import org.horse.track.singleton.CommandPrompt;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class RestockCommandTest {

	private BillInventoryService inventoryServiceMock;
	
	private IMessage outMock;
	
	@Before
	public void setUp() throws Exception {
	
		outMock = Mockito.mock(CommandPrompt.class);
		
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
