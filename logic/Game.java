package logic;
import logic.gameObjects.Player;
import java.util.Random;
import view.GamePrinter;

/*This class encapsulates the logic of the game and is responsible for updating the state of all the game elements. 
  It maintains the current cycle number. It contains (a reference to) the board object, to which the game object 
  delegates much of its functionality, and (a reference to) the player object.
 */

//Random Class should only be instantiated in Game

public class Game {
	static final int COINS_TO_RECEIVE = 10; //number of coins received by player
	
	//fields
	private Level level;
	private Long seed; //provide one for predictable behaviour of tests
	private Random r;
	private GameObjectBoard board;
	private int cycles = 0;
	private Player player;
	private GamePrinter gamePrinter;
	
	//constructor
	public Game(Long seed, Level lvl) {
		level = lvl;
		this.seed = seed;
		player = new Player();
		board = new GameObjectBoard(this);
		r = new Random(seed);
		gamePrinter = new GamePrinter(this, lvl.getRows(), lvl.getColumns());
	}

	public char userCommand(String str) {
		char output = '0';
		str = str.toLowerCase();
		if (str.equals("h") || str.equals("help")) {
			output = 'h';
		} else if (str.equals("r") || str.equals("reset")) {
			resetValues();
			output = 'r'; // r of reset
		} else if (str.equals("e") || str.equals("exit")) {
			output = 'e';
		}  else if (str.equals("n") || str.equals("none") || str.equals("")) {
			output = 'c'; // c de correct
		} else if (str.startsWith("a ") || str.startsWith("add ")) {
			String[] parts = str.split(" ");
			int x = Integer.parseInt(parts[1]), y = Integer.parseInt(parts[2]);
			if (board.validCords(x, y) && y != level.getColumns() - 1) { //cannot add slayer on last column 
				if (player.enoughCoins(board.getCostSlayers())) {
					board.addSlayer(x, y); 
					player.payCoins(board.getCostSlayers());
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
	
	public String boardObject(int row, int col) {
		String object = "";
		if(!board.isFree(col, row))
			object = board.objectOn(col, row); //returns toString() of vamp or slayer who is on (col, row)
				
		return object;
	}
	
	public String toString() {
		return drawInfo() + gamePrinter;
	}

	public void update() {
		board.moveVamps();		
	}

	public void attack() {
		board.attack();	
	}

	public int getVampsNumber() {
		return level.getVampNumber();
	}

	public boolean canVampMove(int x, int y) {
		return board.vampCanMove(x, y);
	}

	public int getCols() {
		return level.getColumns();
	}

	public int getRows() {
		return level.getRows();
	}
	
	public String drawInfo() {
		StringBuilder str = new StringBuilder();
		char jumpLine = '\n';
		str.append(jumpLine);
		str.append("Cycle number: ").append(cycles).append(jumpLine);
		str.append("Coins: ").append(player.getCoins()).append(jumpLine);
		str.append("Remainig vampires: ").append( board.vampsLeft()).append(jumpLine);
		str.append("Vampires on the board: ").append(board.vampsOnBoard()).append(jumpLine);
		
		return str.toString();
	}
	
	public void addVampire() {
		/*
		 * Level	Number of vampires	Frequency	board width	board height
			EASY		3				0.1				8		4
			HARD		5				0.2				7		3
			INSANE		10				0.3				5		6
			Configuration for each level of difficulty
		*/
		if(board.vampsLeft() > 0 && r.nextDouble() < level.getvampireFrequency()) { 
			//nextDouble(): returns the next pseudorandom, double value between 0 and 1.0 from this random number generator's sequence.
			int col = level.getColumns() - 1; //vamps appear on last column always
			int row = r.nextInt(level.getRows());
			board.addVampire(col, row);	
		}
	}

	public void bite (int x, int y, int damage) {
		board.bite(x, y, damage);
	}
	
	public void shootBullet(int x, int y, int damage) {
		board.shootBullet(x, y, damage);
	}
	
	public void removeDeadObj() {
		board.removeDeadObj();
	}
	
	public void resetValues() {
		player.setCoins(50);
		cycles = 0;
		board.resetValues();
	}
	
	public String checkEnd() {
		String str = "";
		if (board.vampsLeft() == 0 && board.vampsOnBoard() == 0)
			str = "[Game over] Player wins!";
		else if (board.vampsWin())
			str = "[Game over] Vampires win!";
		return str;
	}

	public void receiveCoins() {
		if(r.nextInt(2) == 0) //50% probability of receiving 10 coins
			player.receiveCoins(COINS_TO_RECEIVE);
	}
	
	public void incrementCycles() {
		cycles++;
	}
}