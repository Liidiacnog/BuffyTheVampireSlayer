package control.commands;

import logic.Game;

public class AddCommand extends Command {
	
	private int x, y;
	
	public AddCommand() {
		super("add", "a", "[a]dd <x> <y>", "add a slayer in position x, y");
	}
	
	public AddCommand(int xCoord, int yCoord) {
		this(); //calls AddCommand contructor defined in this subclass, on a previous line
		x = xCoord;
		y = yCoord;
	}

	@Override
	public boolean execute(Game game) {
		game.setIncrementCycles(true);
		return game.addSlayer(x, y);
	}

	@Override
	public Command parse(String[] commandWords) {
		AddCommand command = null;
		if (matchCommandName(commandWords[0])) {//TODO falta lo de try
			command = new AddCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]));
		}
		
		return command;
	}

}
