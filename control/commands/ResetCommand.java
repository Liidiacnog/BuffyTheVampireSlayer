package control.Commands;

import logic.Game;

public class ResetCommand extends Command {

	public ResetCommand() {
		super("reset", "r", "", "[r]eset: reset game");
		// TODO iniciar details
	}

	@Override
	public boolean execute(Game game) {
		game.resetValues();
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		ResetCommand command = null;
		if (matchCommandName(commandWords[0])) {
			command = new ResetCommand();
		}
		
		return command;
	}

}

