package control.commands;

import logic.Game;
import control.Controller;

/*These concrete command classes execute the functionality associated to each command by calling methods of the Game class.*/
//only Command with parameters?
public class AddCommand extends Command{
	help = "[a]dd <x> <y>: add a slayer in position x, y%n";
	
	private int x, y; //coordinates to add vampire
	

	public AddCommand(){ //public Command(String name,  String shortcut, String details, String help){    
		super("Add", "a", , ); //TODO change?
	}
	
	public boolean execute(Game game) {
		game.addVampire();
		return true;
	}
	
	public Command parse(String[] input) {
		Command obj = null;
		try {
			x = Integer.parseInt(input[1]);
			y = Integer.parseInt(input[2]);
			if (x != controller.getLvlDimX() - 1 && controller.isFree(x, y)) { //cannot add slayer on last column 
				if (board.canAfford(player.getCoins()) != -1) {
					board.addSlayer(x, y, this); 
					player.payCoins(board.canAfford(player.getCoins()));
				} else {
					System.out.println(player.toStringNotEnoughCoins());
				}
				output = 'c';
			} else {
				output = 'p'; // p of (invalid) position
			}
		
		
		
	}
}
