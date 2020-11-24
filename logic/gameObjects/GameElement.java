package logic.gameObjects;

import logic.Game;

public class GameElement implements IAttack{

	public int col, row, life;
	public Game game;
	public static String representation;
	public static int frequency, damage, resistance;
	
	public GameElement(int x, int y, Game game) {
		col = x;
		row = y;
		this.game = game;
		life = resistance;
	}
	
	
	public String toString() {
		return representation;
	}
	
	//returns true if the gameElement has coordinates (x, y)
	public boolean isHere(int x, int y) {
		return col == x && row == y;
	}
		
	//to be overwritten by elements that do move
	public void move() {} //TODO legal? 
	
	public void attack() {}//TODO legal? 
	
	public boolean isDead() {
		return life <= 0 ;
	}
}
