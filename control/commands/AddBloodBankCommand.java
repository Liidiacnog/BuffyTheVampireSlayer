package control.commands;

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
	public boolean execute(Game game) {
		game.setIncrementCycles(true);
		return game.addBloodBank(x, y, cost);
	}

	@Override
	public Command parse(String[] commandWords) {
		AddBloodBankCommand command = null;
		if (matchCommandName(commandWords[0])) {//TODO falta lo de try
			command = new AddBloodBankCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]), Integer.parseInt(commandWords[3]));
		}
		
		return command;
	}

}
