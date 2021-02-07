package control;

import java.util.Scanner;

import control.commands.Command;
import exceptions.GameException;
import utils.CommandGenerator;
import logic.Game;

public class Controller {
	
	private final String prompt = "Command > ";
	private static final String DEBUG_MSSG = "[DEBUG] ";
	private static final String ERROR_MSSG = "[ERROR] ";
	private static final String GAME_OVER_MSSG = "[Game over] ";
	
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
			 String[] parameters = s.toLowerCase().trim().split("\\s+");
			 System.out.println(DEBUG_MSSG + "Executing: " + s);
			 
			 try {
				 command = CommandGenerator.parseCommand(parameters);
			     refreshDisplay = command.execute(game);
		    	 game.evolve();//evolve is set to true by those commands which cause game to continue (attacking, moving, ...) 
		    	 
			 } catch (GameException ex) {
				 System.out.format(ERROR_MSSG + ex.getMessage() + "%n");
				 if(ex.getCause() != null)
					 System.out.format(DEBUG_MSSG + ex.getCause().getMessage() + "%n");
				 System.out.println();
			 }
		}
    	
	    if (refreshDisplay) printGame();
		System.out.println (GAME_OVER_MSSG + game.getWinnerMessage());
    } 

    
}