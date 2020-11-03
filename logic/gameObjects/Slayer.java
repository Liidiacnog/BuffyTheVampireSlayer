package logic.gameObjects;

import logic.Game;

/*Slayer. Added by the player on a specified tile of the board. Costs 50 coins. Does not move.  On each cycle, fires a 
 * silver bullet which travels from left to right and strikes the leftmost vampire in the same row as the slayer, if 
 * there is a vampire in that row. The effect of the impact of a silver bullet on a vampire is to decrease its lives by one.
 * 
 * 
Slayer
Behaviour: Fires silver bullets at vampires in the same row.
Cost: 50 coins.
Resistance: 3 lives.
Frequency: One shot per cycle.
Damage: Each shot effects 1 point of damage on its target (removes one of its lives).
Reach: Can only fire straight (along the row) and forwards (right to left).
Graphics: Represented on the board by the ASCII text “<->”.
*/




public class Slayer {

	private static int cost = 50, resistance = 3, frequency = 1, damage = 1;
	private static String representation =  "<->";
	private int life;
	private int row, col; //position coordinates on the board
	
	private Game currentGame; //instance of Class Game in order to be able to use its methods
	
	public Slayer(int x, int y) {
		row = x;
		col = y;
		life = resistance; //just initially
	}
	
	//methods
	
			//affect others
	
	public String toString() {
		return representation + "[" + life + "]";
	}

	public boolean isHere(int i, int j) {
		boolean found = false;
		if (row == i && col == j) {
			found = true;
		}
		return found;
	}	
		
	
			//own state
		
	public void beenBitten(int harm) {
		life -= harm;
	}
	

				//getters
	
	public static int getDamage() {
		return damage;
	}
	
	public int getX() {
		return row;
	}
	
	public int getY() {
		return col;
	}
	
	public static int getCost() {
		return cost;
	}

	public int getLife() {
		return life;
	}
}
	
	
