package logic.gameObjects;

import logic.Game;

public class Slayer extends GameElement{
	
	private static final int cost = 50, resistance = 3, frequency = 1, damage = 1;
	private static final String representation =  "<->";
	
	//constructor 
	public Slayer(int x, int y, Game game) {
		super(x, y, game);
		life = resistance; //just initially
	}
	
	
	public String toString() {
		return representation + "[" + life + "]";
	}
	
	
	//calls method in game which will check if any vampire can be shot by its bullet shot from (col, row)
	public void attack() {
		boolean found = false;
		int i = col + 1;
		IAttack other = null;
		if(life > 0) {
			while (!found && i < game.getBoardColumns()) {
				other = game.getAttackableInPos(i, row);
				if (other != null) {
					if (other.receiveSlayerAttack(damage))
						found = true;
				}
				i++;
			}
		}		
	}
	
	//returns -1 if coins aren't enough to pay for cost, or returns cost otherwise
	public static int canAfford(int coins) {
		int sol = -1;
		if(coins >= cost)
			sol = cost;
		return sol;
	}
	
	public boolean receiveVampireAttack(int harm) {
		damage(harm);
		return true;
	}

	public boolean receiveDraculaAttack() {
		damage(life);
		return true;
	}
	
	//Getters
	
	public int getLife() {
		return life;
	}


	//not affected by garlicPush
	@Override
	public void garlicPush() {}


	//not affected by lightFlash
	@Override
	public void lightFlash() {}
	
}
