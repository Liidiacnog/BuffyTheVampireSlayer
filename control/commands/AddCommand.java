package control.commands;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import exceptions.GameException;
import exceptions.InvalidArgumentsException;
import exceptions.InvalidPositionException;
import exceptions.NotEnoughCoinsException;
import logic.Game;

public class AddCommand extends Command {
	
	private int x, y;
	
	public AddCommand() {
		super("add", "a", "[a]dd <x> <y>", "add a slayer in position x, y");
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
			if(game.addSlayer(x, y)) {
				game.setIncrementCycles(true);
				game.setNewGameCycle(true);
				exec = true;
			}else {
				game.setIncrementCycles(false);
			}
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
