package control.commands;


import exceptions.*;
import logic.Game;

public class AddBloodBankCommand extends Command {
	
	private int x, y, cost;
	
	private static final String AddBloodBankCommandName = "bank";
	private static final String AddBloodBankCommandShortcut = "b";
	private static final String AddBloodBankCommandDetails = "[b]ank <x> <y> <z>";
	private static final String AddBloodBankCommandHelp = "add a bloodbank in x, y with cost z";

	
	public AddBloodBankCommand() {
		super(AddBloodBankCommandName, AddBloodBankCommandShortcut, AddBloodBankCommandDetails, AddBloodBankCommandHelp);
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
		try{
			if(game.addBloodBankCommand(x, y, cost)) 
				exec = true;
		}catch (InvalidPositionException | NotEnoughCoinsException lowLevel){
			throw new CommandExecuteException("[ERROR] Failed to add BloodBank", lowLevel);
		}
		return exec;
	}


	@Override
	public Command parse(String[] commandWords) throws InvalidArgumentsException {
		AddBloodBankCommand command = null;
		if (matchCommandName(commandWords[0]) && commandWords.length == 4) {
			try {
				command = new AddBloodBankCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]), Integer.parseInt(commandWords[3]));
			} catch (NumberFormatException nfe) {
				throw new InvalidArgumentsException("[ERROR] Invalid arguments for add bloodbank, number expected: " + details);
			}
		} else if (matchCommandName(commandWords[0])) {
			throw new InvalidArgumentsException("[ERROR] Invalid arguments for add bloodbank, number expected: " + details);
		}
		
		return command;
	}
	
}
