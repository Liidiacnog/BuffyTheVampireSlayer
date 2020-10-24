package logic;

import logic.gameObjects.Player;
import logic.lists.VampireList;

/*
 * Encapsula la lógica del juego. Contiene un GameObjectBoard al que delega la funcionalidad.
Esta clase debe ser quien lleve la puntuación y también guardar una referencia a la instancia de Player.
 representa la partida en curso. Durante la ejecución de la aplicación solo se creará un objeto de la clase Game
 */



public class Game {

	//fields
	private static Level lvl;
	private Long seed;
	private GameObjectBoard board;
	private int cycles = 0;
	private Player player;
	
	//constructor
	public Game(Long seed, Level lvl) {
		this.lvl = lvl;
		this.seed = seed;
		player = new Player();
		board = new GameObjectBoard(lvl);
	}
	
	
	public void update() {//actualiza el estado de todos los elementos del juego
		
	}
	
	public void draw() {
		System.out.println("");
		System.out.println("Cycle number: " + cycles);
		System.out.println("Coins: " + player.getCoins());
		System.out.println("Remainig vampires: " + board.vampsLeft());
		System.out.println("Vampires on the board: " + board.vampsOn());
		System.out.println("");
		board.draw();
		System.out.println("");
		System.out.print("Command > ");
		
	}
	
	
	
	
}
