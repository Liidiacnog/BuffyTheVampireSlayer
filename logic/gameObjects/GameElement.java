package logic.gameObjects;

import logic.Game;

public class GameElement {

	private int x, y;
	private Game game;
	private String representation;
	
	public GameElement() {
		
	}
	
	
	public String toString() {
		return representation;
	}
	
	//returns true if the gameElement has coordinates (x, y)
	public boolean isHere(int x, int y) {
		return this.x == x && this.y == y;
	}
		
	//to be overwritten by elements that do move
	public void move() {} //TODO legal? 
	
	public void attack() {}//TODO legal? 
	
	
}
