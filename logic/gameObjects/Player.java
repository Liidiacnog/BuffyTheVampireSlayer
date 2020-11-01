package logic.gameObjects;

/*
 * This class encapsulates the state and behaviour of the player. For the moment, the state comprises only the number of 
 * coins in the player’s possession. Note that there will only ever be one instance of the Player class in the program, 
 * which we will refer to as the player object.
 */


public class Player {
	static final int COINS_TO_RECEIVE = 10; //number of coins received (whenever chances are favourable)
	
	private int coins = 50; //The player starts the game with 50 coins and on each turn has a probability of 50% of receiving 10 coins.
	private static String notEnoughCoinsErrorMsg = "Not enough coins left!";
	
	
	public String toStringNotEnoughCoins(){
		return (notEnoughCoinsErrorMsg);
	}
	
	public int getCoins() {
    	return coins;
    }

	public boolean enoughCoins(int i) {
		return (coins >= i);
	}

	public void payCoins(int i) {
		coins -= i;
	}
	
	public void receiveCoins() {
		coins += COINS_TO_RECEIVE;
	}
	
}
