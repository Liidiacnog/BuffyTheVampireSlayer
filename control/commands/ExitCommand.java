package control.Commands;

import logic.Game;

public class ExitCommand extends Command {

	public ExitCommand() {
		super("exit", "e", "", "[e]xit: exit game");
		// TODO iniciar details
	}

	@Override
	public boolean execute(Game game) {
		game.exitCommand();
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		ExitCommand command = null;
		
		if (matchCommandName(commandWords[0])) {
			command = new ExitCommand();
		}
		
		return command;
	}

}
