package logic;
import logic.gameObjects.Player;
import java.util.Random;
import view.GamePrinter;
import view.IPrintable;

/*This class encapsulates the logic of the game and is responsible for updating the state of all the game elements. 
  It maintains the current cycle number. It contains (a reference to) the board object, to which the game object 
  delegates much of its functionality, and (a reference to) the player object.
 */

//Random Class should only be instantiated in Game

public class Game {
	
	//constants related to rules of the game
	static final int COINS_TO_RECEIVE = 10; //number of coins received by player
	static final int INITIAL_COINS = 50; //initial coins of player
	static final double PROB_RECEIVING_COINS = 0.5; //player has 50% chances of receiving 10 coins
	
	//fields
	private Level level;
	private Long seed; //provide one for predictable behaviour of tests
	private Random r;
	private GameObjectBoard board;
	private Player player;
	private GamePrinter gamePrinter;
	private int cycles = 0;
	
	//constructor
	public Game(Long seed, Level lvl) {
		level = lvl;
		this.seed = seed;
		r = new Random(this.seed);
		board = new GameObjectBoard(lvl.getColumns(), lvl.getRows(), lvl.getVampNumber());
		player = new Player(INITIAL_COINS);
		gamePrinter = new GamePrinter(this, lvl.getColumns(), lvl.getRows());
	}
	

	
	public String toString() {
		return drawInfo() + gamePrinter;
	}

	
	//draws information needed every cycle of the game before displaying the board:
	//cycle number, coins, remaining vampires and vampires currently on the board
	public String drawInfo() {
		StringBuilder str = new StringBuilder();
		char jumpLine = '\n';
		str.append(jumpLine);
		str.append("Cycle number: ").append(cycles).append(jumpLine);
		str.append("Coins: ").append(player.getCoins()).append(jumpLine);
		str.append("Remainig vampires: ").append(board.getVampsLeft()).append(jumpLine);
		str.append("Vampires on the board: ").append(board.getVampsOnBoard()).append(jumpLine);
		
		return str.toString();
	}
	
	
	//generates a String "matrix" (String[][]) which is the representation of the board object
	public String[][] encodeGame() {
		return board.toStringMatrixBoard();
	}
	
	//actions on game loop:

	public void update() {
		receiveCoins();
		board.update();		
	}

	
	public void attack() {
		board.attack();	
	}


	//through a random double (number), decides whether to add a vampire on a randomly chosen row, according to the vampire frequency of the level
	public void addVampire() {
		/*
		   Level	Number of vampires	Frequency	board width		board height
			EASY		3				0.1				8				4
			HARD		5				0.2				7				3
			INSANE		10				0.3				5				6
			Configuration for each level of difficulty
		*/
		if(board.getVampsLeft() > 0 && r.nextDouble() < level.getVampireFrequency()) { 
			//nextDouble(): returns the next pseudorandom, double value between 0 and 1.0 from this random number generator's sequence.
			int col = level.getColumns() - 1; //vampires appear on last column always
			int row = r.nextInt(level.getRows());
			board.addVampire(col, row, this);	
		}
	}


	//true if vampire on (x, y) can move
	public boolean vampCanMove(int x, int y) {
		return board.vampCanMove(x, y);
	}

	
	//tells board to tell slayerList to check if any of the slayers can be bitten by a vampire on (x, y)
	public void bite (int x, int y, int damage) {
		board.bite(x, y, damage);
	}
	
	
	//tells board to tell vampireList to check if any of the vampires can be shot by a slayer on (x, y)
	public void shootBullet(int x, int y, int damage) {
		board.shootBullet(x, y, damage);
	}
	
	
	//removes references, in lists, to objects that are dead
	public void removeDeadObj() {
		board.removeDeadObj();
	}
	
	
	//resets game
	public void resetValues() {
		player.setCoins(INITIAL_COINS);
		cycles = 0;
		board.reset(level.getVampNumber());
	}
	
	
	//checks if slayers have killed all possible vampires, or vampires have reached end of board
	//returns string corresponding to who has won, or "" if no one has won yet
	public String getEnd() {
		String str = "";
		if (board.getVampsLeft() == 0 && board.getVampsOnBoard() == 0)
			str = "[Game over] Player wins!";
		else if (board.vampsWin())
			str = "[Game over] Vampires win!";
		return str;
	}

	
	//checks odds of player of receiving coins, and if he should receive them, calls method in charge
	public void receiveCoins() {
		if(r.nextFloat() > PROB_RECEIVING_COINS)
			player.receiveCoins(COINS_TO_RECEIVE);
	}
	
	
	public void incrementCycles() {
		cycles++;
	}



	public void addSlayer(int x, int y) {
		board.addSlayer(x, y, this);
	}



	public void exitCommand() {
		// TODO Auto-generated method stub
		
	}



	public void helpCommand() {
		// TODO Auto-generated method stub
		
	}
	
}

//manages input of the user, returns char which tells run() which action to carry out
	/*public char userCommand(String str) {
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
			output = 'c'; // c of correct
		} else if (str.startsWith("a ") || str.startsWith("add ")) {
			String[] parts = str.split(" ");
			try {
				int x = Integer.parseInt(parts[1]), y = Integer.parseInt(parts[2]);
				if (x != level.getColumns() - 1 && board.isFree(x, y)) { //cannot add slayer on last column 
					if (board.canAfford(player.getCoins()) != -1) {
						board.addSlayer(x, y, this); 
						player.payCoins(board.canAfford(player.getCoins()));
					} else {
						System.out.println(player.toStringNotEnoughCoins());
					}
					output = 'c';
				} else {
					output = 'p'; // p of (invalid) position
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				output = 'p';
			} catch (NumberFormatException nfe) {
				output = 'p';
			}
			
		}else
			output = 'i'; // i of invalid
		
		
		return output;
	}*/