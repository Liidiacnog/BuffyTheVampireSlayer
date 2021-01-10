package control.commands;

import java.io.IOException;

import exceptions.CommandExecuteException;
import exceptions.InvalidArgumentsException;
import logic.Game;

public class SaveCommand extends Command {
	
	private String fileName;
	
	public SaveCommand(String str) {
		this();
		fileName = str;
	}
	
	public SaveCommand () {
		super("save", "s", "[s]ave <filename>", " . Save the state of the game to a file");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			game.saveGame(fileName);
		} catch (IOException ioe) {
			throw new CommandExecuteException("[ERROR] Not possible to open file", ioe);
		}
		System.out.println("Game succesfully saved in file " + fileName + ".dat");
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws InvalidArgumentsException{
		SaveCommand command = null;
		if (matchCommandName(commandWords[0])) {
			if (commandWords.length == 2) {
				command = new SaveCommand(commandWords[1]);
			} else {
				throw new InvalidArgumentsException("[ERROR] Invalid arguments for saving game, number expected: " + getDetails());
			}
		}
		return command;
	}

}
