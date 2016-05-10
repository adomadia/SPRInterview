package org.horse.track.factory;

import java.util.StringTokenizer;

import org.horse.track.command.ICommandExecutable;
import org.horse.track.command.impl.BlankCommand;
import org.horse.track.command.impl.QuitCommand;
import org.horse.track.command.impl.RestockCommand;
import org.horse.track.command.impl.WagerCommand;
import org.horse.track.command.impl.WinnerCommand;
import org.horse.track.io.IWritable;
import org.horse.track.service.BillInventoryService;
import org.horse.track.service.HorseService;
import org.horse.track.service.HorseWinnerService;
import org.horse.track.util.StringUtils;

public class CommandFactory {

	private HorseWinnerService winnerService;
	private HorseService horseService;
	private BillInventoryService inventoryService;
	private IWritable out;

	public CommandFactory(HorseWinnerService winnerService, HorseService horseService,
			BillInventoryService inventoryService, IWritable out) {

		this.winnerService = winnerService;
		this.horseService = horseService;
		this.inventoryService = inventoryService;
		this.out = out;
	}
	
	public ICommandExecutable getCommand(String cmdSyntax) {

		StringTokenizer tokenizer = new StringTokenizer(cmdSyntax);

		// return BlankCommand object if its empty line.
		if (tokenizer.countTokens() == 0) {
			return new BlankCommand(cmdSyntax);
		}

		String cmd = tokenizer.nextToken();

		// return RestockCommand boject if its Q command
		if (QuitCommand.COMMAND_KEYWORD.equalsIgnoreCase(cmd)) {
			QuitCommand quitCommand = new QuitCommand(cmdSyntax);
			quitCommand.validate();
			return quitCommand;
		}

		// return RestockCommand object if its R command.
		if (RestockCommand.COMMAND_KEYWORD.equalsIgnoreCase(cmd)) {
			RestockCommand restockCommand = new RestockCommand(cmdSyntax, inventoryService);
			restockCommand.validate();
			return restockCommand;
		}

		// return RestockCommand object if its W command.
		if (WinnerCommand.COMMAND_KEYWORD.equalsIgnoreCase(cmd)) {
			WinnerCommand winnerCommand = new WinnerCommand(cmdSyntax, winnerService, horseService);
			winnerCommand.validate();
			return winnerCommand;
		}

		// return WagerCommand object if its Wager command.
		if (StringUtils.isNaturalNumber(cmd) && Integer.parseInt(cmd) > 0) {
			WagerCommand wagerCommand = new WagerCommand(cmdSyntax, out, winnerService, horseService,
					inventoryService);

			wagerCommand.validate();
			return wagerCommand;
		}

		return null;
	}

}
