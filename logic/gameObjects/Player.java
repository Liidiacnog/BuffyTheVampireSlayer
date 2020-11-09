package logic.gameObjects;

/*
 * This class encapsulates the state and behaviour of the player. For the moment, the state comprises only the number of 
 * coins in the player’s possession. Note that there will only ever be one instance of the Player class in the program, 
 * which we will refer to as the player object.
 */


public class Player {
	private int coins;
	private static String notEnoughCoinsErrorMsg = "Not enough coins left!";
	
	public Player(int initial_coins) {
		coins = initial_coins;
	}
	
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
	
	public void receiveCoins(int amount) {
		coins += amount;
	}
	
	public void setCoins(int coins) {
		this.coins = coins;
	}
	
}
