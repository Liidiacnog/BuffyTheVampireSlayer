package logic.gameObjects;

import logic.Game;

public class BloodBank extends GameElement {
	
	private int cost;
	private boolean alive; //TODO int life?
	private static final String representation = "B-B";
	
	public BloodBank(int x, int y, int cost, Game game) { //TODO initialize lives on constructor, and constant for intial nr of lives
		super(x, y, game);
		this.cost = cost;
		alive = true;
	}
	
	//implements the method defined in IAttack, but since BloodBanks are killed instantly, it doesn't make use of the vampire's damage (harm)   
	public boolean receiveVampireAttack(int harm) {//TODO change so that they call a method which is BloodBankreceivesAnyAttack()?
		alive = false; //TODO comment on why it isnt used
		return true;
	}
	
	//implements the method defined in IAttack, but since BloodBanks are killed instantly, it doesn't make use of the vampire's damage (harm)   
	public boolean receiveDraculaAttack() {
		alive = false;
		return true;
	}
	
	public String toString() {
		return representation + "[" + cost + "]";
	}
	
	//BloodBanks don't move
	public void move() {} //TODO add move Interface
	
	@Override
	public boolean isDead() {
		return !alive;
	}
	
	@Override
	public int getBloodBankCoins() {
		return Math.round(cost * 0.1f);
	}

	//not affected by garlicPush
	@Override
	public void garlicPush() {}

	
	//not affected by lightFlash
	@Override
	public void lightFlash() {}

}
