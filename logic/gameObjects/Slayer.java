package logic.gameObjects;

import logic.Game;

public class Slayer extends GameElement{
	
	private static int cost = 50, resistance = 3, frequency = 1, damage = 1;
	private static String representation =  "<->";
	private int life;
	private int row, col; //position coordinates on the board
	private Game game; //instance of Class Game in order to be able to use its methods
	
	//constructor 
	public Slayer(int x, int y, Game game) {
		col = x;
		row = y;
		life = resistance; //just initially
		this.game = game;
	}
	
	
	public String toString() {
		return representation + "[" + life + "]";
	}

	
	//checks if its coordinates are (i, j)
	public boolean isHere(int i, int j) {
		boolean found = false;
		if (col == i && row == j)
			found = true;
		
		return found;
	}
	
	
	//calls method in game which will check if any vampire can be shot by its bullet shot from (col, row)
	public void attack() {
		game.shootBullet(col, row, damage);
	}
	
	
	//reduces its life by "harm"
	public void damage(int harm) {
		life -= harm;
	}
	
	//returns -1 if coins aren't enough to pay for cost, or returns cost otherwise
	//TODO legal? or breaking encapsulation?
	public static int canAfford(int coins) {
		int sol = -1;
		if(coins >= cost)
			sol = cost;
		return sol;
	}
	
	//Getters
	
	public int getLife() {
		return life;
	}
}