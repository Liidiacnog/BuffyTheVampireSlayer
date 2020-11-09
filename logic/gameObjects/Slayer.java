package logic.gameObjects;

import logic.Game;

public class Slayer {
	private static int cost = 50, resistance = 3, frequency = 1, damage = 1;
	private static String representation =  "<->";
	private int life;
	private int row, col; //position coordinates on the board
	private Game game; //instance of Class Game in order to be able to use its methods
	
	public Slayer(int x, int y, Game game) {
		col = x;
		row = y;
		life = resistance; //just initially
		this.game = game;
	}
	
	public boolean isHere(int i, int j) {
		boolean found = false;
		if (col == i && row == j)
			found = true;
		
		return found;
	}
	
	public String toString() {
		return representation + "[" + life + "]";
	}

	public static int getCost() {
		return cost;
	}

	public void damage(int damage2) {
		life -= damage2;
	}
	
	public void attack() {
		game.shootBullet(col, row, damage);
	}

	public int getLife() {
		return life;
	}
}