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
	
	@Override
	public boolean isDead() {
		return !alive;
	}
	
	@Override
	public int getBloodBankCoins() {
		return Math.round(cost * 0.1f);
	}
	
	@Override
	public void move() {}

	@Override
	public void attack() {}

}
