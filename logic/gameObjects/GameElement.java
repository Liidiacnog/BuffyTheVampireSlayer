package logic.gameObjects;

import logic.Game;

public abstract class GameElement implements IAttack{ //TODO abstract?

	protected int col, row; //position coordinates on the board
	protected int life = 0; //changed on constructor of each subclass whenever an object is instantiated
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
		
	public abstract void move();
	
	//check if the element has moved past the first column of the board
	public boolean reachEnd() {
		return false;
	}
	
	//check if the element whose next coordinates will be (x, y) is going to move past the first column of the board
	public boolean reachEnd(int x, int y) {
		return false;
	}
	
	public boolean isDead() {
		return life <= 0;
	}
	
	public int getBloodBankCoins() {return -1;}

	//effect of garlicPush on GameElements
	public abstract void garlicPush();


	//effect of lightFlash on GameElements
	public abstract void lightFlash();
	
	
	public void resetVampMovedBefore() {}


	
}
