package logic.gameObjects;

import logic.Game;

public class GameElement implements IAttack{

	protected int x, y;
	protected Game game;
	protected String representation;
	protected int life;
	
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
	
	public boolean isDead() {
		return life <= 0 ;
	}
}
