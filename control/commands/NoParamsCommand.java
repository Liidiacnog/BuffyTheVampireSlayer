package control.commands;

import logic.Game;

/*the NoParamsCommand class implements the parse method using the matchCommandName method inherited from Command. 
 * In this way, the classes deriving from NoParamsCommand only need to implement the execute method.
 * Command subclasses that represent commands with parameters derive directly from the class Command and will
 *  need attributes to store the value of their parameters.*/

public abstract class NoParamsCommand extends Command {
	
	public NoParamsCommand(String name,  String shortcut, String details, String help){ 
		super(name, shortcut, details, help);
	}

	public Command parse(String[] input) {
		Command obj = null;
		if(matchCommandName(input[0]))
			obj = this;
		return obj;
	}
	
	public abstract boolean execute(Game game);
	
}
