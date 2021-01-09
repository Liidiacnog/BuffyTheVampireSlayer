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
		
		public AddVampireCommand() {
			super("add a vampire", "v", "[v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}", "add a vampire in position x, y");
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
					if(game.addDracula(x, y))
						exec = true;
				}
				else if(availableTypes.indexOf(type) == 1) {
					if(game.addExplosiveVampire(x, y))
						exec = true;
				}
				else {//always called when type matches none of the available ones, so last case is always normal vampire
					if(game.addVampire(x, y))
						exec = true;
				}
				game.setIncrementCycles(false);
			}catch (InvalidPositionException | NoMoreVampiresException | DraculaHasArisenException lowLevel){ //wrapping of low-level exceptions
				throw new CommandExecuteException("[ERROR] Failed to add vampire", lowLevel); 
			}
			
			return exec;
		}

		
		@Override
		public Command parse(String[] commandWords) throws InvalidArgumentsException, InvalidVampireTypeException  {
			AddVampireCommand command = null;
			if (matchCommandName(commandWords[0])) {
				if (commandWords.length == 4) {
					if (availableTypes.indexOf(commandWords[1].toUpperCase()) != -1) //it has been found in the ArrayList
						try {
							command = new AddVampireCommand(Integer.parseInt(commandWords[2]), Integer.parseInt(commandWords[3]), commandWords[1]);
						} catch (NumberFormatException nfe) {
							throw new InvalidArgumentsException("[ERROR] Invalid arguments for add vampire, number expected: " + getDetails()); 
						}
					else
						throw new InvalidVampireTypeException("[ERROR] Invalid type: " + getDetails());
				} else if (commandWords.length == 3) {
					try {
						command = new AddVampireCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]), "");
					} catch (NumberFormatException nfe) {
						throw new InvalidArgumentsException("[ERROR] Invalid arguments for add vampire, number expected: " + getDetails());
					}
				} else 
					throw new InvalidArgumentsException("[ERROR] Invalid arguments for add vampire, number expected: " + getDetails());
			}
			return command;
		}
}
