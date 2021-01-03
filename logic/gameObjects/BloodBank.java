package logic.gameObjects;

import logic.Game;

public class BloodBank extends GameElement {
	
	private static final int resistance = 1;
	private int cost;
	private static final String representation = "B-B";
	
	public BloodBank(int x, int y, int cost, Game game) {
		super(x, y, game);
		this.cost = cost;
		life = resistance;
	}
	
	// He hecho esta función porque estaba en un ToDo, pero no estoy seguro de que así sea mejor (si no te gusta lo quitas quiero decir).
	public boolean BloodBankreceivesAttack(int harm) {
		life -= harm;
		return true;
	}
	
	//implements the method defined in IAttack  
	public boolean receiveVampireAttack(int harm) {
		return BloodBankreceivesAttack(harm);
	}
	
	//implements the method defined in IAttack  
	public boolean receiveDraculaAttack() {
		return BloodBankreceivesAttack(life);
	}
	
	public String toString() {
		return representation + "[" + cost + "]";
	}
	
	@Override
	public boolean isDead() {
		return life <= 0;
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
