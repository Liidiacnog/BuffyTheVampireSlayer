package logic.gameObjects;

import logic.Game;

public class BloodBank extends GameElement {
	
	private int cost;
	private boolean alive;
	private static final String representation = "B-B";
	
	public BloodBank(int x, int y, int cost, Game game) {
		super(x, y, game);
		this.cost = cost;
		alive = true;
	}
	
	public boolean receiveVampireAttack(int harm) {
		alive = false;
		return true;
	}
	
	public String toString() {
		return representation + "[" + cost + "]";
	}
	
	//BloodBanks don't move
	public void move() {}
	
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
	public void lightFlash() {
		// TODO Auto-generated method stub
		
	}

}
