package org.horse.track.command;

import org.horse.track.command.impl.WinnerCommand;
import org.horse.track.model.Horse;
import org.horse.track.service.HorseService;
import org.horse.track.service.HorseWinnerService;
import org.horse.track.service.impl.HorseServiceImpl;
import org.horse.track.service.impl.HorseWinnerSeriveImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class WinnerCommandTest {

	private HorseWinnerService winnerServiceMock;
	private HorseService horseServiceMock;
	
	@Before
	public void setUp() throws Exception {
	
		this.winnerServiceMock = Mockito.mock(HorseWinnerSeriveImpl.class);
		this.horseServiceMock = Mockito.mock(HorseServiceImpl.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidHorseNumber() {

		WinnerCommand command = new WinnerCommand("w 2", winnerServiceMock, horseServiceMock);

		Mockito.when(horseServiceMock.findById(Mockito.anyLong())).thenReturn(null);
		
		command.validate();
		command.execute();
	}
	
	
	@Test()
	public void testSaveWinner() {
	
		WinnerCommand command = new WinnerCommand("w 2", winnerServiceMock, horseServiceMock);
		Horse horse = new Horse("Fort Utopia", 10);

		Mockito.when(horseServiceMock.findById(Mockito.anyLong())).thenReturn(horse);

		command.validate();
		command.execute();
		
		Mockito.verify(winnerServiceMock).save(horse);
		
	
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidationFail() {
		WinnerCommand command = new WinnerCommand("w 2.1", winnerServiceMock, horseServiceMock);
		command.validate();
	}
}
