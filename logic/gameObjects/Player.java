package logic.gameObjects;

/*
 * Esta clase sirve para llevar el control de los atributos propios del jugador, que de momento son sólo las monedas que tiene disponibles. 
 * Puedes también tener una referencia al generador de números aleatorios (instancia de Random) y así poder recurrir a él cuando toque 
 * calcular si se reciben o no monedas. PERO SIMON HA DICHO QUE PREFIERE QUE A LA CLASE RANDOM SOLO SE LA HAGA REFERENCIA EN GAME
 */

public class Player {
	private int coins = 50; //The player starts the game with 50 coins and on each turn has a probability of 50% of receiving 10 coins.
	
	
	public int getCoins() {
    	return coins;
    }
	
}
