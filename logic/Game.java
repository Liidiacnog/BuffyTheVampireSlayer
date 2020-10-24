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


	public char command(String str) {
		char output = '0';
		str = str.toLowerCase();
		if (str.equals("h") || str.equals("help")) {
			output = 'h';
		} else if (str.equals("r") || str.equals("reset")) {
			output = 'c'; // c de correct
		} else if (str.equals("e") || str.equals("exit")) {
			output = 'e';
		}  else if (str.equals("n") || str.equals("none") || str.equals("")) {
			output = 'c'; // c de correct
		} else if (str.startsWith("a ") || str.startsWith("add ")) {
			String[] parts = str.split(" ");
			int x = Integer.parseInt(parts[1]), y = Integer.parseInt(parts[2]);
			//System.out.println(x + " " + y);
			if (board.validCords(x, y)) { // The arguent of enaughtCoins should be a variable
				if (player.enaughCoins(50)) {
					board.addSlayer(x, y);
					player.payCoins(50);
				} else {
					System.out.println("Not enaught coins");
				}
				output = 'c';	
			} else {
				output = 'p'; // p of (invalid) position
			}
		} else {
			output = 'i'; // i of invalid
		}
		
		
		return output;
	}
	
	
	
	
}
