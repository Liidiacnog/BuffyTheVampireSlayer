package control.commands;

import logic.Game;

public class AddCommand extends Command {
	
	private int x, y;
	
	public AddCommand() {
		super("add", "a", " add a slayer in position x, y", "[a]dd <x> <y>:");
	}
	
	public AddCommand(int xCord, int yCord) {
		super("add", "a", " add a slayer in position x, y", "[a]dd <x> <y>:");
		x = xCord;
		y = yCord;
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
