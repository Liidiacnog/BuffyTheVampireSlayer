package control.commands;

import logic.Game;

public class AddCommand extends Command {
	
	private int x, y;
	String name
	
	public AddCommand() {
		super("add", "a", "", "[a]dd <x> <y>: add a slayer in position x, y");
		// TODO iniciar details
	}
	
	public AddCommand(int xCord, int yCord) {
		super("add", "a", "", "[a]dd <x> <y>: add a slayer in position x, y");
		x = xCord;
		y = yCord;
		// TODO iniciar details
	}

	@Override
	public boolean execute(Game game) {
		game.addSlayer(x, y);
		return true;
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
