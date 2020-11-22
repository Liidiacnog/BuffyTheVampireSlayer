package control.Commands;

import logic.Game;

public class HelpCommand extends Command {

	public HelpCommand() {
		super("help", "h", "", "[h]elp: show this help");
		// TODO iniciar details
	}

	@Override
	public boolean execute(Game game) {
		game.helpCommand();
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		HelpCommand command = null;
		if (matchCommandName(commandWords[0])) {
			command = new HelpCommand();
		}
		
		return command;
	}

}
