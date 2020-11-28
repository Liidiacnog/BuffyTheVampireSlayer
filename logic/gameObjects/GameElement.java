package logic.gameObjects;

import logic.Game;

public abstract class GameElement implements IAttack{

	protected int col, row; //position coordinates on the board
	protected int life;
	protected Game game;
	
	public GameElement(int x, int y, Game game) {
		col = x;
		row = y;
		this.game = game;
	}
	

	//returns true if the gameElement has coordinates (x, y)
	public boolean isHere(int x, int y) {
		return col == x && row == y;
	}
		
	//to be overwritten by elements that do move
	public abstract void move();
	
	public abstract void attack(int columns);
	
	public boolean reachEnd() {
		return false;
	}
	
	public boolean isDead() {
		return life <= 0 ;
	}
}
