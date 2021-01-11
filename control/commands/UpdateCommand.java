package control.commands;

import logic.Game;

public class UpdateCommand extends NoParamsCommand{

	
	private static final String name = "none";
	private static final String shortcut = "n";
	private static final String details = "[n]one  | []";
	private static final String help = "update";

	
	public UpdateCommand() {
		super(name, shortcut, details, help);
	}

	
	public boolean execute(Game game) {
		game.updateCommand();
		return true;
	}

	@Override
	public Command parse(String[] commandWords){
		UpdateCommand command = null;
		if (matchCommandName(commandWords[0]) || commandWords[0].equals("")) {
			command = new UpdateCommand();
		}
		
		return command;
	}
	
}
