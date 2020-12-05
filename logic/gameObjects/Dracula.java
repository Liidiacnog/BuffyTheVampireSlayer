package logic.gameObjects;


import logic.Game;

/*
 * A slayer, or any other type of defender, is destroyed (loses all lives) by a bite from Dracula.
 */

public class Dracula extends Vampire {
	
	private static final String representation = "V-V"; 
	private static boolean appeared = false; //keep count of instantiations of Dracula (there can only be 1)
	
	//so that it hides super.damage and we don't get confused and access to a wrong value of damage
	private static final String damage = "Kill with one blow"; 
	
	public Dracula(int x, int y, Game game) {
		super(x, y, game);
		life = resistance;
		appeared = true;
	}
	
	
	public void attack() {
		//TODO voy por aquí
	}
	
	
	
	
	
	public static boolean getAppearedBefore() {
		return appeared;
	}
	
}
