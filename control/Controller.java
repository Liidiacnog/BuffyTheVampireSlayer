package control;

import java.util.Scanner;

import control.commands.Command;
import exceptions.MyException;
import utils.CommandGenerator;
import logic.Game;

public class Controller {
	
	public final String prompt = "Command > ";
	public static final String unknownCommandMsg ="Unknown command";
	static final String invalidParametersMsg = "Parameters are not valid";	//shown when adding a gameElement on an invalid position
	static Command command = null;

    private Game game;
    private Scanner scanner;
    
    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    this.scanner = scanner;
    }
    
    public void printGame() {
   	 	System.out.println(game);
   }
    
    public void run() {
	    boolean refreshDisplay = true;

	    while (!game.isFinished()){
	    		
    		 if (refreshDisplay) printGame();
    		 refreshDisplay = false;
    		 
    		 command = null;
    		 try {
    			  System.out.print(prompt);	
    			  String s = scanner.nextLine();
    			  String[] parameters = s.toLowerCase().trim().split(" ");
    			  System.out.println("[DEBUG] Executing: " + s);
    		      command = CommandGenerator.parseCommand(parameters, this);
	   		      if (command != null) {
	   		    	  try {
	   		    		refreshDisplay = command.execute(game);
		   	    	  	  if(refreshDisplay)
		   	    	  		game.refreshDisplay();
	   		    	  }
	   		    	  catch (MyException me) {
	   		    		System.out.println(me);
	   		    	  }
	   	    	  		
	   		      }
	   		      else {
	   		    	 System.out.println("[ERROR]: "+ unknownCommandMsg + '\n');
	   		      }
	    	  }
    		  catch (NumberFormatException | ArrayIndexOutOfBoundsException exp){
    			  /*In case someone input a command as "add q w" or "b 0 0"*/
	    		  System.out.println("[ERROR]: "+ invalidParametersMsg);
	    		  System.out.println();
	    	  }
		}	    
    	if (refreshDisplay) printGame();
		System.out.println ("[Game over] " + game.getWinnerMessage());

    }
    
}