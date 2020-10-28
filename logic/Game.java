package logic;

import logic.lists.VampireList;
import logic.gameObjects.Player;
import java.util.Random;
/* métodos a usar de la clase Random:
 * nextDouble() para saber si hay que añadir o no un vampiro
 * nextInt() para saber en qué posición inicial sale el vampiro
 * nextFloat() para saber si en un ciclo el player recibe monedas o no
 */


/*
 * Encapsula la lógica del juego. Contiene un GameObjectBoard al que delega la funcionalidad.
Esta clase debe ser quien lleve la puntuación y también guardar una referencia a la instancia de Player.
 representa la partida en curso. Durante la ejecución de la aplicación solo se creará un objeto de la clase Game
 */

//Random Class should only be instantiated in Game

public class Game {

	//fields
	private static Level level;

	private Long seed; //TODO for random class?

	private static Random r;
	
	private static GameObjectBoard board;
	private static int cycles = 0;
	private Player player;
	
	//constructor
	public Game(Long seed, Level lvl) {

		level = lvl;
		this.seed = seed;

		//TODO what seed?


		player = new Player();
		board = new GameObjectBoard(lvl);
		r = new Random(seed);

	}
	
	
	//getters
	
	public static int getVampsOfLevel() {
		return level.getVampNumber();
	}
	
	public static int getLevel() {
		return Level.getValue(level);
	}
	
	public int getRandomClassNextInt(int upperBound) {
		return r.nextInt(upperBound);
	}
	
	//Actions in game loop:
	//TODO estas no van en Controller?
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

	public void update() {
		if(r.nextInt(2) == 0) //TODO okay or should we define .equals()?  //50% probability of receiving 10 coins
			player.receiveCoins(); 
		board.moveVamps();				
	}
	
	public void attack() {
		board.slayersHit();
		board.vampsBite();	
	}

	public void addVampire() {
		/*The game can be played at three levels, EASY, HARD and INSANE, where the level determines various configuration options (see Table 1.1),
		 *  in particular, the probability on each cycle that a new vampire is added to the game. If a vampire is to be added, the row in which it 
		 *  appears is chosen at random. If there is already a vampire in the chosen row then the new vampire is not placed on the board3.

Level	Number of vampires	Frecuency	board width	board height
EASY	3						0.1				8		4
HARD	5						0.2				7		3
INSANE	10						0.3				5		6
Table 1.1: Configuration for each level of difficulty

Table 1.1 shows the different values for the configuration options that depend on the level. These are:
The total number of vampires that appear in a game.
The frequency of appearance of vampires, which determines the probability that a vampire appears on a given cycle. Thus,if the frequency is 0.2, 
a vampire appears randomly on each cycle with a probability of one in five.
The dimensions of the board; at the easiest level the board dimensions are 8 × 4 and at the hardest level 5 × 6.
*/
	}
	
	public void userCommand() { //TODO
		
	}
	
	public boolean checkEnd() {
		boolean end = false;
			if (board.vampsLeft() == 0) {
				end = true;
				
			} else if (board.vampsWin()) {
				end = true;
			}
		
		
		return end;
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
			if (board.validCords(x, y)) { // The argument of enoughCoins should be a variable
				if (player.enoughCoins(50)) {
					board.addSlayer(x, y); //TODO
					player.payCoins(50);
				} else {
					System.out.println("Not enough coins");
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
