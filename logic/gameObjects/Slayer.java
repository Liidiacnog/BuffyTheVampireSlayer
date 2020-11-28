package logic.gameObjects;

import logic.Game;

public class Slayer extends GameElement{
	
	private static int cost = 50, resistance = 3, frequency = 1, damage = 1;
	private static String representation =  "<->";
	private int life;
	
	//constructor 
	public Slayer(int x, int y, Game game) {
		super(x, y, game);
		life = resistance; //just initially
	}
	
	
	public String toString() {
		return representation + "[" + life + "]";
	}
	
	
	//calls method in game which will check if any vampire can be shot by its bullet shot from (col, row)
	public void attack(int columns) {
		boolean found = false;
		int i = col + 1;
		IAttack other = null;
		if(life > 0) {
			while (!found && i < columns) {
				other = game.getAttackableInPos(i, row);
				if (other != null) {
					if (other.receiveSlayerAttack(damage))
						found = true;
				}
				i++;
			}
		}		
	}
	
	
	//reduces its life by "harm"
	public void damage(int harm) {
		life -= harm;
	}
	
	//returns -1 if coins aren't enough to pay for cost, or returns cost otherwise
	public static int canAfford(int coins) {
		int sol = -1;
		if(coins >= cost)
			sol = cost;
		return sol;
	}
	
	public boolean receiveVampireAttack(int damage) {
		life -= damage;
		return false;
	}


	@Override
	public void move() {
	}
	
	//Getters
	
	public int getLife() {
		return life;
	}
}

//NO SE USA
//checks if its coordinates are (i, j)
	/*public boolean isHere(int i, int j) {
		boolean found = false;
		if (col == i && row == j)
			found = true;
		
		return found;
	}*/