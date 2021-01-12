package control.commands;

import logic.Game;

public class UpdateCommand extends NoParamsCommand{

	
	private static final String UpdateCommandName = "none";
	private static final String UpdateCommandShortcut = "n";
	private static final String UpdateCommandDetails = "[n]one  | []";
	private static final String UpdateCommandHelp = "update";

	
	public UpdateCommand() {
		super(UpdateCommandName, UpdateCommandShortcut, UpdateCommandDetails, UpdateCommandHelp);
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
