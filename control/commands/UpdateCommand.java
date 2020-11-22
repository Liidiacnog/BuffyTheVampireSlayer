package control.commands;

import logic.Game;

public class UpdateCommand extends NoParamsCommand{

	public UpdateCommand() {
		super("none", "", "", "[n]one | []: update"); //TODO no sÃ© que es details
	}
	
	public boolean execute(Game game) {
		return true;
	}

	public Command parse(String[] commandWords) {
		UpdateCommand command = null;
		if (matchCommandName(commandWords[0]) || commandWords[0].equals("")) {
			command = new UpdateCommand();
		}
		
		return command;
	}

}
