package logic.gameObjects;


public class Player {
	private int coins;
	private static String notEnoughCoinsErrorMsg = "Not enough coins left!";
	
	//constructor 
	public Player(int initial_coins) {
		coins = initial_coins;
	}
	
	
	public String toStringNotEnoughCoins(){
		return (notEnoughCoinsErrorMsg);
	}
	
	
	public int getCoins() {
    	return coins;
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


	public boolean canAfford(int cost) {
		return cost <= coins;
	}
	
}
