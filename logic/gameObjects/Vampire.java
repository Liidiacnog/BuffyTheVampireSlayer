package logic.gameObjects;

import logic.Game;

public class Vampire extends GameElement{

	private static int resistance = 5, frequency = 1, damage = 1; 
	private static String representation = "VˆV";
	private int life;
	private int col, row; //position coordinates on the board
	private Game game; //Game game needs to be passed via a parameter whenever needed
	private boolean movedBefore; //to check whether it is its turn to move or not(they move each 2 cycles)
	
	/*how many vampires are on the board, 
	 * total number of vampires that can appear in this level, 
	 * vampires that can appear taking into consideration which ones have appeared already and which ones have died:
	 */
	private static int  vampsOnBoard = 0, vampsThisLevel, vampsLeft;  
	
	//constructor 
	public Vampire (int x, int y, Game game) {  
		col = x;
		row = y;
		life = resistance;
		movedBefore = true;
		vampsOnBoard++; //new vampire is added to the board on x, y
		vampsLeft--; //one less vampire can be added to the board
		this.game = game;
	}
	

	public String toString() {
		return representation + "[" + life + "]";
	}
	
	
	//checks if its coordinates are (i, j)
	public boolean isHere(int i, int j) {
		boolean found = false;
		if (col == i && row == j) {
			found = true;
		}
		return found;
	}
	
	
	//calls method in game which will check if any slayer can be bitten from (col, row)
		public void attack() {
			if(life > 0) {
				IAttack other = game.getAttackableInPos(x - 1, y);
				if (other != null) {
					other.receiveVampireAttack(damage); //TODO enterarse de que va esto
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
	
	
	//returns distance at which the bullet is from him (the minimum is 1 "tile" away), or returns -1 if he can't get hit by it
	public int target(int xBullet, int yBullet) {
		int dist = -1;
		if(row == yBullet && xBullet < col)
			dist = col - xBullet;
		
		return dist;
	}
	
		
	//updates vampsOnBoard, vampsThisLevel, vampsLeft ONLY when a list is created or reset  
	public static void updateData(int size, int vampsLevel) {
		vampsOnBoard = size;
		vampsThisLevel = vampsLevel;
		vampsLeft = vampsLevel - size;
	}
	
	
	//check if it has reached the first column of the board
	public boolean reachEnd() {
		return col == 0;
	}
	
	
	//Getters

	public static int getVampsLeft() {
		return vampsLeft;
	}

	public int getLife() {
		return life;
	}

	public static int getVampsThisLevel() {
		return vampsThisLevel;
	}
	
	// Setters
	
	public static void setVampsOnBoard(int nr) {
		vampsOnBoard = nr;
	}
	
	public static void setVampsLeft(int nr) {
		vampsLeft = nr;
	}
	
}
