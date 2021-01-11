package logic.gameObjects;

import logic.Game;

public class Slayer extends GameElement{
	
	private static final int cost = 50, resistance = 3, frequency = 1, damage = 1;
	private static final String representation =  "<->";
	private static final String stringifyRep = "S";
	
	//constructor 
	public Slayer(int x, int y, Game game) {
		super(x, y, game, representation, resistance, stringifyRep);
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

	
	public static int getCost() {
		return cost;
	}
	
	public boolean receiveVampireAttack(int harm) {
		damage(harm);
		return true;
	}

	public boolean receiveDraculaAttack() {
		damage(life);
		return true;
	}

	//doesn't affect it
	@Override
	public void receiveLightFlash() {}
	
	
	//Getters
	
	public int getLife() {
		return life;
	}

}
