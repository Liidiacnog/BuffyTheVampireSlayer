package control;

import java.util.Scanner;

import control.commands.Command;
import exceptions.GameException;
import utils.CommandGenerator;
import logic.Game;

public class Controller {
	
	private final String prompt = "Command > ";
	private static final String unknownCommandMsg ="Unknown command";
	private static final String invalidParametersMsg = "Parameters are not valid";//shown when adding a gameElement on an invalid position
	private Command command = null;

    private Game game;
    private Scanner scanner;
    
    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    this.scanner = scanner;
    }
    
    public void printGame() {
   	 	System.out.println(game);
   }
    
    public void run(){
	    boolean refreshDisplay = true;

	    while (!game.isFinished()){
	    		
    		 if (refreshDisplay) printGame();
    		 refreshDisplay = false;
    		 
    		 
    		 System.out.print(prompt);
			 String s = scanner.nextLine();
			 String[] parameters = s.toLowerCase().trim().split(" ");
			 System.out.println("[DEBUG] Executing: " + s);
			 try {
				 command = CommandGenerator.parseCommand(parameters);
			     refreshDisplay = command.execute(game);
		    	 if(game.getNewGameCycle()) //newGameCycle is set to true by those commands which cause game to continue (attacking, moving, ...) 
		    	  	game.gameCycle();
		    	 game.setNewGameCycle(false); //default value, may or may not be modified by the execute() method of each command
			 } catch (GameException ex) {
				 System.out.format(ex.getMessage() + "%n");
				 if(ex.getCause() != null)
					 System.out.format(ex.getCause() + "%n"); //TODO shows extra info : e.g. exceptions.InvalidPositionException: [DEBUG] Position (0, 13): Invalid position
				 System.out.println();
			 }
		}
    	if (refreshDisplay) printGame();
		System.out.println ("[Game over] " + game.getWinnerMessage());

    } 

    
    
}