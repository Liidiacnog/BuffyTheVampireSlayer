package control.commands;

import logic.Game;

public class UpdateCommand extends NoParamsCommand{

	public UpdateCommand() {
		super("none", "", "", "[n]one | []: update"); //TODO details?
		shortcut = "";
	}
	
	public boolean execute(Game game) {
		shortcut = "";
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		UpdateCommand command = null;
		if (matchCommandName(commandWords[0]) || commandWords[0].equals("")) {
			command = new UpdateCommand();
		}
		
		return command;
	}
	
}
