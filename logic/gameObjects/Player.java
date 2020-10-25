package logic.gameObjects;

/*
 * This class encapsulates the state and behaviour of the player. For the moment, the state comprises only the number of coins in the player’s
 *  possession. Note that there will only ever be one instance of the Player class in the program, which we will refer to as the player object.
 */

public class Player {
	private int coins = 50; //The player starts the game with 50 coins and on each turn has a probability of 50% of receiving 10 coins.
	
	
	public int getCoins() {
    	return coins;
    }


	public boolean enaughCoins(int i) {
		return (coins >= i);
	}


	public void payCoins(int i) {
		coins = coins - i;
	}
	
}
