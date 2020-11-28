package logic.gameObjects;

import logic.Game;

public class Vampire extends GameElement{

	private static int resistance = 5, frequency = 1, damage = 1; 
	private boolean movedBefore; //to check whether it is its turn to move or not(they move each 2 cycles)
	private static String representation = "VˆV";
	/*how many vampires are on the board, 
	 * total number of vampires that can appear in this level, 
	 * vampires that can appear taking into consideration which ones have appeared already and which ones have died:
	 */
	private static int  vampsOnBoard = 0, vampsThisLevel, vampsLeft;  
	
	//constructor 
	public Vampire (int x, int y, Game game) {  
		super(x, y, game);
		movedBefore = true;
		vampsOnBoard++; //new vampire is added to the board on x, y
		vampsLeft--; //one less vampire can be added to the board
	}
	

	public String toString() {
		return representation + "[" + life + "]";
	}
	
	
	//calls method in game which will check if any slayer can be bitten from (col, row)
	public void attack(int columns) {
		if(life > 0) {
			IAttack other = game.getAttackableInPos(col - 1, row); 
			if (other != null) {
				other.receiveVampireAttack(damage);
			}
		}		
	}
		
	
	//reduces its life by "harm"	
	public void damage(int harm) {
		life -= harm;
	}
			
	
	//moves if it's its turn to do so, and there is no one on the tile where he should be going
	public void move() {
		if (game.vampCanMove(col, row)) {
			if (!movedBefore) {
				col -= 1;
			}
			movedBefore = !movedBefore;
		}
	}
	
	//returns new y coordinate of vampire if it were able to move
	public static int canMoveY(int y) {
		return y;
	}
	
	//returns new x coordinate of vampire if it were able to move
		public static int canMoveX(int x) {
			return --x;
	}
	
		
	//returns distance at which the bullet is from him (the minimum is 1 "tile" away), or returns -1 if he can't get hit by it
	public int target(int xBullet, int yBullet) {
		int dist = -1;
		if(row == yBullet && xBullet < col)
			dist = col - xBullet;
		
		return dist;
	}
	
		
	//updates vampsOnBoard, vampsThisLevel, vampsLeft ONLY when a list is created or reset  
	public static void updateData(int nr, int vampsLevel) {
		vampsOnBoard = nr;
		vampsThisLevel = vampsLevel;
		vampsLeft = vampsLevel - nr;
	}
	
	
	//check if it has reached the first column of the board
	public boolean reachEnd() {
		return col == 0;
	}
	
	public boolean receiveSlayerAttack(int damage) {
		life -= damage;
		return true;
	}
	
	
	//Getters

	public static int getVampsLeft() {
		return vampsLeft;
	}

	public static int getVampsOnBoard() {
		return vampsOnBoard;
	}
	
	public static int getVampsThisLevel() {
		return vampsThisLevel;
	}
	
	public int getLife() {
		return life;
	}

	// Setters
	
	public static void setVampsOnBoard(int nr) {
		vampsOnBoard = nr;
	}
	
	public static void setVampsLeft(int nr) {
		vampsLeft = nr;
	}
}

//NO SE USA

//checks if its coordinates are (i, j)
/*public boolean isHere(int i, int j) {
	boolean found = false;
	if (col == i && row == j) {
		found = true;
	}
	return found;
}*/
