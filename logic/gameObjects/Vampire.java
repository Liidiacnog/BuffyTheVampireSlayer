package logic.gameObjects;

import logic.Game;

public class Vampire {

	private int life = 5;
	private boolean placed;
	private static int frequency = 1, damage = 1; //how many vampires are on the board, total number of vampires that can appear in the game:
	private static int  vampsOnBoard, vampsThisLevel, vampsLeft;  
	private int col, row; //position coordinates on the board
	private boolean movedBefore; //check whether it is its turn to move or not(they move each 2 cycles)
	private static String representation = "VˆV";
	private Game game; //Game game needs to be passed via a parameter whenever needed
	
	public Vampire (int x, int y, Game game) {  
		col = x;
		row = y;
		placed = true;
		movedBefore = true;
		vampsOnBoard = 0;
		vampsThisLevel = game.getVampsNumber();
		this.game = game;
	}
	
	public static int getVampsNumber() {
		return vampsThisLevel;
	}

	public void move() {
		if (!movedBefore) {
			if (game.canVampMove(col, row)) {
				col -= 1;
			}
		}
		movedBefore = !movedBefore;
	}
	
	public boolean isHere(int i, int j) {
		boolean found = false;
		if (col == i && row == j) {
			found = true;
		}
		return found;
	}
	
	public int target(int xBullet, int yBullet) {
		int dist = -1;
		if(row == yBullet && xBullet < col)
			dist = col - xBullet;
		
		return dist;
	}
	
	public void beenHit(int harm) {
		life -= harm;
	}
	
	public String toString() {
		return representation + "[" + life + "]";
	}
	
	public boolean reachEnd() {
		return col == 0; //column == first
	}
	
	// Getters

	public static int getLeft() {
		return vampsLeft;
	}

	public int getLife() {
		return life;
	}

	public static int getVampThisLevel() {
		return vampsThisLevel;
	}
	
	// Setters
	
	public static void setVampsOnBoard(int nr) {
		vampsOnBoard = nr;
	}
	
	public static void setVampsLeft(int nr) {
		vampsLeft = nr;
	}

	public void attack() {
		game.bite(col, row, damage);
	}
}
