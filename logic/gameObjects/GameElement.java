package logic.gameObjects;

import logic.Game;

public abstract class GameElement implements IAttack, IMove{

	protected int col, row; //position coordinates on the board
	protected int life = 0; //changed on constructor of each subclass whenever an object is instantiated
	private final String representation;
	protected String stringifyRep;
	protected Game game;
	
	public GameElement(int x, int y, Game game, String representation, int resistance, String sRep) {
		col = x;
		row = y;
		this.game = game;
		this.representation = representation;
		life = resistance;
		stringifyRep = sRep;
	}
	
	public String toString() {
		return representation + "[" + life + "]";
	} 
	
	//returns true if the gameElement has coordinates (x, y)
	public boolean isHere(int x, int y) {
		return col == x && row == y;
	}
	
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
	
	
	public void update() {
		move();
	}

	//effect of garlicPush on GameElements
	public void receiveGarlicPush() {} 


	//default effect of lightFlash on GameElements, overwritten by all GameElements except Vampire
	public void receiveLightFlash() {
		life = 0;				
	}
	
	//reduces its life by "harm"	
	public void damage(int harm) {
		life -= harm;
	}

	
	public String stringify() {
		return stringifyRep + ";" + col + ";" + row + ";" + life;
	}


}
