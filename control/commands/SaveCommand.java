package control.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import exceptions.CommandExecuteException;
import exceptions.InvalidArgumentsException;
import logic.Game;

public class SaveCommand extends Command {
	
	private String fileName;
	private static final String ResetCommandName = "save";
	private static final String ResetCommandShortcut = "s";
	private static final String ResetCommandDetails = "[s]ave <filename> .";
	private static final String ResetCommandHelp = " Save the state of the game to a file";

	
	public SaveCommand() {
		super(ResetCommandName, ResetCommandShortcut, ResetCommandDetails, ResetCommandHelp);
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
			throw new CommandExecuteException("[ERROR] Not possible to open file", ioe);
		}
		game.saveCommand();
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
				throw new InvalidArgumentsException("[ERROR] Invalid arguments for saving game, number expected: " + details);
			}
		}
		return command;
	}

}
