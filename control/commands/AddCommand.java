package control.commands;

import exceptions.*;
import logic.Game;

public class AddCommand extends Command {
	
	private int x, y;
	
	private static final String name = "add";
	private static final String shortcut = "a";
	private static final String details = "[a]dd <x> <y>";
	private static final String help = "add a slayer in position x, y";

	
	public AddCommand() {
		super(name, shortcut, details, help);
	}
	
	public AddCommand(int xCoord, int yCoord) {
		this(); //calls AddCommand constructor defined in this subclass, on a previous line
		x = xCoord;
		y = yCoord;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		boolean exec = false;
		try{
			if(game.addSlayerCommand(x, y)) 
				exec = true;
		}catch (InvalidPositionException | NotEnoughCoinsException lowLevel){
			throw new CommandExecuteException("[ERROR] Failed to add slayer", lowLevel);
		}
		return exec;
	}

	@Override
	public Command parse(String[] commandWords)  throws InvalidArgumentsException {
		AddCommand command = null;
		if (matchCommandName(commandWords[0]) && commandWords.length == 3) {
			try {
				command = new AddCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]));
			} catch (NumberFormatException nfe) {
				throw new InvalidArgumentsException("[ERROR] Invalid arguments for add slayer, number expected: " + getDetails());
			}
		} else if (matchCommandName(commandWords[0])) {
			throw new InvalidArgumentsException("[ERROR] Invalid arguments for add slayer, number expected: " + getDetails());
		}
		
		return command;
	}

}
