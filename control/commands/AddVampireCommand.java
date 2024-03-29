package control.commands;

import logic.Game;

import java.util.ArrayList;
import java.util.Arrays;

import exceptions.*;

/*
 *If no type is indicated, the vampire placed on the board is a normal vampire, 
 *if the type is D, it is Dracula (as long as he is not already on the board), 
 *and if the type is E, it is an explosive vampire. 
 *This command does not cause the game to evolve (and the cycle number
does not change) and if there are no vampires remaining, it has no effect.
 */


public class AddVampireCommand extends Command{

		private int x, y;
		private String type;
		//ArrayList of String because commandWords is a String array and we need to compare them
		private ArrayList<String> availableTypes = new ArrayList<>(Arrays.asList("D", "E"));
				//Dracula, ExplosiveVampire, if no type is specified => normal Vampire
		
		private static final String AddVampireCommandName = "vampire";
		private static final String AddVampireCommandShortcut = "v";
		private static final String AddVampireCommandDetails = "[v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}";
		private static final String AddVampireCommandHelp = "add a vampire in position x, y";
				
		public AddVampireCommand() {
			super(AddVampireCommandName, AddVampireCommandShortcut, AddVampireCommandDetails, AddVampireCommandHelp);
		}
		
		public AddVampireCommand(int xCoord, int yCoord, String type) {
			this(); //calls AddCommand constructor defined in this subclass, on a previous line
			x = xCoord;
			y = yCoord;
			this.type = type.toUpperCase();
		}

		
		@Override
		public boolean execute(Game game) throws CommandExecuteException {
			boolean exec = false;
			try{
				if(availableTypes.indexOf(type) == 0) {
					if(game.addDraculaCommand(x, y))
						exec = true;
				}
				else if(availableTypes.indexOf(type) == 1) {
					if(game.addExplosiveVampireCommand(x, y))
						exec = true;
				}
				else {//always called when type matches none of the available ones, so last case is always normal vampire
					if(game.addVampireCommand(x, y))
						exec = true;
				}
			}catch (InvalidPositionException | NoMoreVampiresException | DraculaHasArisenException lowLevel){ //wrapping of low-level exceptions
				throw new CommandExecuteException("Failed to add vampire", lowLevel); 
			}
			
			return exec;
		}

		
		@Override
		public Command parse(String[] commandWords) throws CommandParseException {
			AddVampireCommand command = null;
			if (matchCommandName(commandWords[0])) {
				if (commandWords.length == 4) {
					if (availableTypes.indexOf(commandWords[1].toUpperCase()) != -1) //it has been found in the ArrayList
						try {
							command = new AddVampireCommand(Integer.parseInt(commandWords[2]), Integer.parseInt(commandWords[3]), commandWords[1]);
						} catch (NumberFormatException nfe) {
							throw new InvalidArgumentsException("Invalid arguments for add vampire, number expected: " + details); 
						}
					else
						throw new InvalidVampireTypeException("Invalid type: " + details);
				} else if (commandWords.length == 3) {
					try {
						command = new AddVampireCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]), "");
					} catch (NumberFormatException nfe) {
						throw new InvalidArgumentsException("Invalid arguments for add vampire, number expected: " + details);
					}
				} else 
					throw new InvalidArgumentsException("Invalid arguments for add vampire, number expected: " + details);
			}
			return command;
		}
}
