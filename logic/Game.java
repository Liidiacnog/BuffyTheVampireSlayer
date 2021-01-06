package logic;
import logic.gameObjects.*;

import java.util.Random;

import exceptions.*;
import view.*;

public class Game implements IPrintable {
	
	//constants related to rules of the game
	private static final int COINS_TO_RECEIVE = 10; //number of coins received by player
	private static final int INITIAL_COINS = 50; //initial coins of player
	private static final double PROB_RECEIVING_COINS = 0.5; //player has 50% chances of receiving 10 coins
	private static final String invalidPositionMsg = "Invalid position";//shown when adding a gameElement on an invalid position 
	private static final String noVampsLeftMsg = "No more remaining vampires left"; //TODO eliminate this and the one before?
	private static final String draculaAlreadyMsg = "Dracula has already appeared";

	//fields
	private Level level;
	private Long seed; //provide one for predictable behaviour of tests
	private Random r;
	private GameObjectBoard board;
	private Player player;
	private GamePrinter gamePrinter;
	private int cycles = 0;
	private boolean isFinished = false;
	private String winnerMsg = "Nobody wins..."; //no winner by default
	private String DraculaArisenMsg = "Dracula has arisen!";
	private boolean DraculaOnBoard = false;	//True if dracula is on board
	private boolean incrementCycles = true;
	private boolean newGameCycle = false; // true if next command will cause game to call gameCycle() 
	
	//constructor
	public Game(Long seed, Level lvl) {
		level = lvl;
		this.seed = seed;
		r = new Random(this.seed);
		board = new GameObjectBoard(lvl.getColumns(), lvl.getRows(), lvl.getVampNumber());
		player = new Player(INITIAL_COINS);
		gamePrinter = new GamePrinter(this, lvl.getColumns(), lvl.getRows());
		Vampire.updateData(0, lvl.getVampNumber());
	}
	
	
	public String toString() {
		return gamePrinter.toString();
	}
	
	//to execute exit game
	public void exit() {
		isFinished = true;
	}
	
	//checks if game has come to an end
	public boolean isFinished() {
		return this.isFinished;
	}
	
	
	//actions on game loop:

	public void gameCycle() {
		update();
		receiveCoins();
		attack();
		addVampires();
		removeDeadObj();
		if(incrementCycles)
			incrementCycles();
		checkEnd();
	} 
	
	
	public void update() {
		board.update();
	}

	
	//checks odds of player of receiving coins, and if he should receive them, calls method in charge
	public void receiveCoins() {
		if(r.nextFloat() > PROB_RECEIVING_COINS)
			player.receiveCoins(COINS_TO_RECEIVE);
		player.receiveCoins(board.getBloodBankCoins());
	}

	
	public void attack() {
		board.attack();	
	}

	
	public void addVampires() {// throws CommandExecuteException TODO eliminate?
		addVampire();
		if(addDracula())
			DraculaOnBoard = true;
		addExplosiveVampire();
	}

	//"natural" (random) addition of vampires on game cycle:
	
	public boolean addVampire() {
		/*
		   Level	Number of vampires	Frequency	board width		board height
			EASY		3				0.1				8				4
			HARD		5				0.2				7				3
			INSANE		10				0.3				5				6
			Configuration for each level of difficulty
		*/
		boolean added = false;
		if(Vampire.getVampsLeft() > 0 && r.nextDouble() < level.getVampireFrequency()) { 
			//nextDouble(): returns the next pseudorandom, double value between 0 and 1.0 from this random number generator's sequence.
			int col = level.getColumns() - 1; //vampires appear on last column always
			int row = r.nextInt(level.getRows() - 1); // -1 to mimic behaviour of random in test cases
			added = board.addVampire(col, row + 1, this); //+ 1 because our programme works with columns and rows from 1 to ...
		}
		return added;
	}
	
	
	public boolean addDracula() {
		boolean added = false;/*same probability of appearing as normal vampires, but only called if Dracula hasn't appeared yet*/
		if(!Dracula.getAppearedBefore() && Vampire.getVampsLeft() > 0 && r.nextDouble() < level.getVampireFrequency()) { 
			int col = level.getColumns() - 1; //vampires appear on last column always
			int row = r.nextInt(level.getRows());
			added = board.addDracula(col, row, this);	
		}
		return added;
	}
	
	
	public boolean addExplosiveVampire() {
		boolean added = false;/*same probability of appearing as normal vampires*/
		if(Vampire.getVampsLeft() > 0 && r.nextDouble() < level.getVampireFrequency()) { 
			int col = level.getColumns() - 1; //vampires appear on last column always
			int row = r.nextInt(level.getRows());
			added = board.addExplosiveVampire(col, row, this);	
		}
		return added;
	}
	
	
	//"artificial" addition of vampires to debug:

	public boolean addVampire(int col, int row) throws CommandExecuteException{
		boolean added = false;
		if(Vampire.getVampsLeft() > 0) {
			added = board.addVampire(col, row, this);	
			if(!added) 
				throw new InvalidPositionException("vampire", col, row); //TODO Donde debe estar el mensaje de error?
		}else {
			throw new NoMoreVampiresException();
		}
		return added;
	}
	
	//"artificial" addition of vampires to debug
	public boolean addDracula(int col, int row) throws CommandExecuteException {
		boolean added = false;
		if(!Dracula.getAppearedBefore() && Vampire.getVampsLeft() > 0) {
			added = board.addDracula(col, row, this);	
			if(!added) 
				throw new InvalidPositionException("Dracula", col, row);
			else
				DraculaOnBoard = true;
		}else if (!(Vampire.getVampsLeft() > 0)){
			throw new NoMoreVampiresException();
		}else { //Dracula.getAppearedBefore() == true
			throw new DraculaAlreadyOnBoardException();
		}
		return added;
	}
	
	
	//"artificial" addition of vampires to debug
	public boolean addExplosiveVampire(int col, int row) throws CommandExecuteException{
		boolean added = false;
		if(Vampire.getVampsLeft() > 0) {
			added = board.addExplosiveVampire(col, row, this);	
			if(!added)
				throw new InvalidPositionException("explosive vampire", col, row);
		}else {
			throw new NoMoreVampiresException();
		}
		return added;
	}
	
	
	//removes references, in lists, to objects that are dead
	public void removeDeadObj() {
		board.removeDeadObj();
	}
	
	
	//resets game
	public void reset() {
		player.setCoins(INITIAL_COINS);
		cycles = 0;
		board.reset(level.getVampNumber());
		DraculaOnBoard = false;
	}
	
	
	//checks if slayers have killed all possible vampires, or vampires have reached end of board
	//updates winnerMsg string corresponding to who has won, or "" if no one has won yet
	public void checkEnd() {
		if (board.checkEnd()) {
			winnerMsg = "Player wins!";
			isFinished = true;
		} else if (board.vampsWin()) {
			winnerMsg = "Vampires win!";
			isFinished = true;
		}
	}
	
	
	public void incrementCycles() {
		cycles++;
	}
	
	
	//Methods to add an element (by user command):
	
	public boolean addSlayer(int x, int y) throws CommandExecuteException {
		boolean added = false;
		if (x != level.getColumns() - 1 && board.isFree(x, y)) { //cannot add slayer on last column 
			if (board.canAfford(player.getCoins()) != -1) {
				board.addSlayer(x, y, this); 
				player.payCoins(board.canAfford(player.getCoins()));
				added = true;
			} else { 
				throw new CommandExecuteException(player.toStringNotEnoughCoins());
				throw new NotEnaughCoinsException("Slayer", 50); //TODO el coste del slayer no es accesible desde aquí. Añadir cost en addCommand y como parametro?
			}
		}
		else {
			throw new InvalidPositionException("slayer", x, y);
		}
		return added;
	}


	public boolean addBloodBank(int x, int y, int cost) throws CommandExecuteException {
		boolean added = false;
		if (x != level.getColumns() - 1 && board.isFree(x, y)) { //cannot add blood bank on last column 
			if (player.canAfford(cost)) {
				board.addBloodBank(x, y, cost, this); 
				player.payCoins(cost);
				added = true;
			} else { 
				throw new CommandExecuteException(player.toStringNotEnoughCoins()+ '\n');
				throw new NotEnaughCoinsException("Bloodbank", cost);
			}
		}
		else {
			throw new InvalidPositionException("bloodbank", x, y);
		}
		return added;
	}
	
	
	//Other execution of commands
	
	//checks if player affords lightFlash, and if so, calls method in board in charge of it
	public boolean lightFlash(int cost) throws CommandExecuteException {
		boolean flash = false;
		if (player.canAfford(cost)) {
			board.lightFlash();
			removeDeadObj();
			player.payCoins(cost);
			flash = true;
		} else 
			throw new CommandExecuteException(player.toStringNotEnoughCoins() + '\n');
			throw new NotEnaughCoinsException("Light flash", cost);
		return flash;
	}
	
	
	//checks if player affords garlicPush, and if so, calls method in board in charge of it
	public boolean garlicPush(int cost)  throws CommandExecuteException {
		boolean push = false;
		if (player.canAfford(cost)) {
			board.garlicPush();
			player.payCoins(cost);
			push = true;
		} else 
			throw new CommandExecuteException(player.toStringNotEnoughCoins()+ '\n');
			throw new NotEnaughCoinsException("Garlic push", cost);
		return push;
	}
	
	
	//true if the element that is going to move to position newX, newY can move backwards due to garlicPush
	public boolean garlicPushEffect(int newX, int newY) {
		return board.isFree(newX, newY);
	}

	
	//implements superCoins
	public boolean superCoins(int coins) {
		player.receiveCoins(coins);
		return true;
	}
	
	
	//true if vampire on (x, y) can move
	public boolean vampCanMove(int x, int y) {
		return board.vampCanMove(x, y);
	}


	public String stringify() {
		String state;
		state = "Cycles: " + cycles + '\n';
		state += "Coins: " + player.getCoins() + '\n';
		state += "Level: " + level.getName().toUpperCase() + '\n';
		state += "Remaining Vampires: " + Vampire.getVampsLeft() + '\n';
		state += "Vampires on Board: " + Vampire.getVampsOnBoard() + '\n';
		state += '\n' + "Game Object List:" + '\n';
		state += board.stringify();
		return state;
	}
	

	@Override
	public String getInfo() {
		String str;
		char jumpLine = '\n';
		str = jumpLine + "Cycle number: " + cycles + jumpLine;
		str += "Coins: " + player.getCoins() + jumpLine;
		str += "Remainig vampires: " + Vampire.getVampsLeft() + jumpLine;
		str += "Vampires on the board: " + Vampire.getVampsOnBoard() + jumpLine;
		if (DraculaOnBoard)
			str += DraculaArisenMsg + jumpLine;
		
		return str;
	}
		

	
	//Getters
	
	public String getWinnerMessage() {
		return winnerMsg;
	}

	public IAttack getAttackableInPos(int i, int j) {
		return board.getAttackable(i, j);
	}
	
	
	public boolean getNewGameCycle() {
		return newGameCycle;
	}

	
	public int getBoardColumns() {
		return board.getColumns();
	}
	
	
	@Override
	public String getPositionToString(int x, int y) {
		return board.objToString(x, y);
	}
	
	
	
	//Setters

	public void draculaDie() {
		DraculaOnBoard = false;
	}

	
	public void setNewGameCycle(boolean b) {
		newGameCycle = b;
	}
	

	public void setIncrementCycles(boolean newValue) {
		incrementCycles = newValue;
	}
	
	
	
	
}
