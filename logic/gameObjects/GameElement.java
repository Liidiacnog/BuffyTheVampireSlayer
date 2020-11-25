package logic.gameObjects;

import logic.Game;

public abstract class GameElement implements IAttack{ //TODO change to abstract

	public int col, row, life; //TODO change to protected
	public Game game;
	public static String representation;
	public static int frequency, damage, resistance;
	
	public GameElement(int x, int y, Game game) {
		col = x;
		row = y;
		this.game = game;
		life = resistance;
	}
	

	//TODO okay?
	public static String getRepresentation() {
		return representation;
	}
	
	public String toString() {
		return representation + "[" + life + "]";
	}
	
	
	//returns true if the gameElement has coordinates (x, y)
	public boolean isHere(int x, int y) {
		return col == x && row == y;
	}
		
	//to be overwritten by elements that do move
	public abstract void move();
	
	public abstract void attack();
	
	public boolean reachEnd() {
		return false;
	}
	
	public boolean isDead() {
		return life <= 0 ;
	}
}
