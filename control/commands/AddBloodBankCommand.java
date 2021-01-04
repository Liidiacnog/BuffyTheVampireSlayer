package control.commands;

import exceptions.CommandExecuteException;
import exceptions.GameException;
import exceptions.InvalidArgumentsException;
import logic.Game;

public class AddBloodBankCommand extends Command {
	
	private int x, y, cost;
	
	
	public AddBloodBankCommand() {
		super("bank", "b", "[b]ank <x> <y> <z>", "add a bloodbank in x, y with cost z");
	}
	
	public AddBloodBankCommand(int x, int y, int z) {
		this();
		this.x = x;
		this.y = y;
		cost = z;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		boolean exec = false;
		if(game.addBloodBank(x, y, cost)) {
			game.setIncrementCycles(true);
			game.setNewGameCycle(true);
			exec = true;
		}else
			game.setIncrementCycles(false);
		
		return exec;
	}


	@Override
	public Command parse(String[] commandWords) throws InvalidArgumentsException {
		AddBloodBankCommand command = null;
		if (matchCommandName(commandWords[0]) && commandWords.length == 4) {
			try {
				command = new AddBloodBankCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]), Integer.parseInt(commandWords[3]));
			} catch (NumberFormatException nfe) {
				throw new InvalidArgumentsException("[ERROR] Invalid arguments for add bloodbank, number expected: " + getDetails());
			}
			
		} else if (matchCommandName(commandWords[0])) {
			throw new InvalidArgumentsException("[ERROR] Invalid arguments for add bloodbank, number expected: " + getDetails());
		}
		
		return command;
	}
	
}
