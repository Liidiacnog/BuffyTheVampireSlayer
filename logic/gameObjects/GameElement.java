package logic.gameObjects;

import logic.Game;

public abstract class GameElement implements IAttack, IMove{

	protected int col, row; //position coordinates on the board
	protected int life;
	protected final String representation;
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
	
	public boolean isDead() {
		return life <= 0;
	}
	
	//overwritten by BloodBank
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
	
	//overwritten by some GameElements like vampires
	public String stringify() {
		return stringifyRep + ";" + col + ";" + row + ";" + life;
	}


}
