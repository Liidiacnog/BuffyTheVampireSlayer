package control.commands;

import logic.Game;
import exceptions.*;

public class UpdateCommand extends NoParamsCommand{

	public UpdateCommand() {
		super("none", "n", "[n]one | []" ,"update");
	}
	
	public boolean execute(Game game) {
		game.setIncrementCycles(true);
		game.setNewGameCycle(true);
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		UpdateCommand command = null;
		if (matchCommandName(commandWords[0]) || commandWords[0].equals("")) {
			command = new UpdateCommand();
		}
		
		return command;
	}
	
}
