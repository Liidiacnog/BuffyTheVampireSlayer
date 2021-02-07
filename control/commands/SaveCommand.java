package control.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import exceptions.InvalidArgumentsException;
import logic.Game;

public class SaveCommand extends Command {
	
	private String fileName;
	private static final String SaveCommandName = "save";
	private static final String SaveCommandShortcut = "s";
	private static final String SaveCommandDetails = "[s]ave <filename>";
	private static final String SaveCommandHelp = " Save the state of the game to a file";

	
	public SaveCommand() {
		super(SaveCommandName, SaveCommandShortcut, SaveCommandDetails, SaveCommandHelp);
	}

	public SaveCommand(String str) {
		this();
		fileName = str;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try (BufferedWriter outChar = new BufferedWriter(new FileWriter(fileName + ".dat"))) {
			outChar.write("Buffy the Vampire Slayer v3.0");
			outChar.newLine();
			outChar.newLine();			
			outChar.write(game.stringify());
		}catch (IOException ioe) {
			throw new CommandExecuteException("IOException caught ", ioe); //TODO correct?
		}
		game.saveCommand();
		System.out.println("Game succesfully saved in file " + fileName + ".dat");
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		SaveCommand command = null;
		if (matchCommandName(commandWords[0])) {
			if (commandWords.length == 2) {
				command = new SaveCommand(commandWords[1]);
			} else {
				throw new InvalidArgumentsException("Invalid arguments for saving game, number expected: " + details);
			}
		}
		return command;
	}

}
