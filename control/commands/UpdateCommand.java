package control.Commands;

import logic.Game;

public class NoCommand extends Command{

	public NoCommand() {
		super("none", "", "", "[n]one | []: update"); //TODO no sÃ© que es details
	}
	
	public boolean execute(Game game) {
		return false;
	}

	public Command parse(String[] commandWords) {
		NoCommand command = null;
		if (matchCommandName(commandWords[0]) || commandWords[0].equals("")) {
			command = new NoCommand();
		}
		
		return command;
	}

}
