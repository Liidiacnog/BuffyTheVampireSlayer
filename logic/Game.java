package logic;

import logic.lists.VampireList;
import logic.gameObjects.Player;
import java.util.Random;
import view.GamePrinter;

/*This class encapsulates the logic of the game and is responsible for updating the state of all the game elements. 
 * It maintains the current cycle number. It contains (a reference to) the board object, to which the game object 
 * delegates much of its functionality, and (a reference to) the player object.
 */

//Random Class should only be instantiated in Game

public class Game { //TODO pass itself using "this"

	//fields
	private Level level;

	private Long seed; //TODO provide one for predictable behaviour of tests

	private Random r;
	
	private GameObjectBoard board;
	private int cycles = 0;
	private Player player;
	private GamePrinter gamePrinter;
	
	//constructor
	public Game(Long seed, Level lvl) {
		level = lvl;
		this.seed = seed;
		r = new Random(seed);
		board = new GameObjectBoard(lvl);
		player = new Player();
		gamePrinter = new GamePrinter(this, level.getColums(), level.getRows());
	}
	
	public void incrementCycles() {
		cycles++;
	}
	
	public String toString() {
		return "" + gamePrinter;
	}
	
	
	//getters
	
	public int getVampsOfLevel() {
		return level.getVampNumber();
	}
	
	/*public int getLevel() {
		return Level.getValue(level);
	}*/ // not used
	
	public String boardObject(int x, int y) {
		String object = "";
		if(!board.isFree(x, y))
			object = board.objectOn(x, y); //returns toString() of vamp or slayer who is on (x, y)
				
		return object;
	}
	
	public int getRandomClassNextInt(int upperBound) {
		return r.nextInt(upperBound);
	}
	
	//Actions in game loop:

	public void receiveCoins() {
		if(r.nextInt(2) == 0) //50% probability of receiving 10 coins
			player.receiveCoins();
	}
	
	public void attack() {
		board.slayersHit();
		board.vampsBite();
	}
	
	
	public String stringInfo() {
		String info = "\n" + "Cycle number: " + cycles + "\n" + "Coins: " + player.getCoins() +
						"\n" + "Remainig vampires: " + board.vampsLeft() + "\n" +
						"Vampires on the board: " + board.vampsOnBoard() + "\n";
		return info;
	}

	//in charge of moving vamps
	public void update() { 
		board.moveVamps();
	}
	
	public void addVampire() {
		/*Level	Number of vampires	Frequency	board width	board height
			EASY		3				0.1				8		4
			HARD		5				0.2				7		3
			INSANE		10				0.3				5		6
		Configuration for each level of difficulty
		*/
		if(board.vampsLeft() > 0 && r.nextDouble() < level.getvampireFrequency()) { 
			//nextDouble(): returns the next pseudorandom, double value between 0 and 1.0 from this random number generator's sequence.
			board.addVampire();	
		}
	}
	
	
	public char userCommand(String str) {
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
			int x = Integer.parseInt(parts[1]), y = Integer.parseInt(parts[2]); //TODO prever errores al meter datos para que el prgrama no pete  
			if (board.validCords(x, y) & x != level.getColums()) { 
				if (player.enoughCoins(GameObjectBoard.getCostSlayers())) {//TODO change coordinates according to tests
					board.addSlayer(x, y); //TODO coords changes
					player.payCoins(GameObjectBoard.getCostSlayers());
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
	
	
	public boolean checkEnd() {
		boolean end = false;
			if (board.vampsLeft() == 0)
				end = true;
			else if (board.vampsWin())
				end = true;
			
		return end;
	}
	
	
}
