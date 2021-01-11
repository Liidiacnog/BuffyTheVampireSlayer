package logic.gameObjects;

import logic.Game;

public class BloodBank extends GameElement {
	
	private static final int resistance = 1;
	private static final String stringifyRep = "B";
	private int cost;
	private static final String representation = "B-B";
	
	public BloodBank(int x, int y, int cost, Game game) {
		super(x, y, game, representation, resistance, stringifyRep);
		this.cost = cost;
	}
	
	//implements the method defined in IAttack  
	public boolean receiveVampireAttack(int harm) {
		damage(harm);
		return true;
	}
	
	//implements the method defined in IAttack  
	public boolean receiveDraculaAttack() {
		damage(life);
		return true;
	}
	
	//doesn't affect it
	@Override
	public void receiveLightFlash() {}
	
	
	//bloodbank overwrites update() because it doesn't move, and instead gives coins to player
	@Override
	public void update() {
		game.addCoinsToPlayer(getBloodBankCoins());
	}
	
	@Override
	public String toString() {
		return representation + "[" + cost + "]";
	}
	
	public String stringify() {
		return super.stringify() + ";" + cost;
	}
	
	@Override
	public boolean isDead() {
		return life <= 0;
	}
	
	private int getBloodBankCoins() {
		return Math.round(cost * 0.1f);
	}

}
