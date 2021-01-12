package logic;

import logic.gameObjects.*;
import utils.CommandGenerator;
import java.util.Random;
import exceptions.*;
import view.*;

public class Game implements IPrintable {
	
	//constants related to rules of the game
	private static final int COINS_TO_RECEIVE = 10; //number of coins received by player
	private static final int INITIAL_COINS = 50; //initial coins of player
	private static final double PROB_RECEIVING_COINS = 0.5; //player has 50% chances of receiving 10 coins
	private static final String invalidPositionMsg = "Invalid position";//shown when adding a gameElement on an invalid position 
	private static final String noVampsLeftMsg = "No more remaining vampires left";
	private static final String draculaAlreadyMsg = "Dracula has already appeared";
	private static final String DraculaArisenMsg = "Dracula has arisen!";
	
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
	private boolean DraculaOnBoard = false;	//True if dracula is on board
	//set to true or false by each execute() method of each command:
	private boolean evolve = false; //true if next command will cause game to call evolve() : update, attack, recivecoins, ...
	
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
	
	
	//actions on game loop:

	public void evolve(){
		if (evolve) {
			update();
			receiveCoins();
			attack();
			addVampires();
			removeDeadObj();
			incrementCycles();
			checkEnd();
		}
	} 
	
	
	public void update() {
		board.update();
	}

	
	//checks odds of player of receiving coins, and if he should receive them, calls method in charge
	public void receiveCoins() {
		if(r.nextFloat() > PROB_RECEIVING_COINS)
			addCoinsToPlayer(COINS_TO_RECEIVE);
	}
	
	//gives player an amount c of coins
	public void addCoinsToPlayer(int c) {
		player.receiveCoins(c);
	}

	
	public void attack() {
		board.attack();	
	}

	
	//"natural" (random) addition of vampires on game cycle:
	public void addVampires() {
		addVampire();
		try {
			DraculaOnBoard = addDracula();
		} catch (DraculaHasArisenException dhae) {
			//currently do nothing, just useful for programmer
		}
		addExplosiveVampire();
	}

	
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
	
	
	public boolean addDracula() throws DraculaHasArisenException { 
		boolean added = false;/*same probability of appearing as normal vampires, but only called if Dracula hasn't appeared yet*/
		if(!DraculaOnBoard && Vampire.getVampsLeft() > 0 && r.nextDouble() < level.getVampireFrequency()) { 
			int col = level.getColumns() - 1; //vampires appear on last column always
			int row = r.nextInt(level.getRows());
			added = board.addDracula(col, row, this);	
		}
		else if (DraculaOnBoard) {
			throw new DraculaHasArisenException("[DEBUG] " + draculaAlreadyMsg);   
		}
		return added;
	}
	
	
	public boolean addExplosiveVampire() {
		boolean added = false; /*same probability of appearing as normal vampires*/
		if(Vampire.getVampsLeft() > 0 && r.nextDouble() < level.getVampireFrequency()) { 
			int col = level.getColumns() - 1; //vampires appear on last column always
			int row = r.nextInt(level.getRows());
			added = board.addExplosiveVampire(col, row, this);	
		}
		return added;
	}
	
	
	//"artificial" addition of vampires to debug
	public boolean addVampireCommand(int col, int row) throws InvalidPositionException, NoMoreVampiresException{
		boolean added = false;
		if(Vampire.getVampsLeft() > 0) {
			added = board.addVampire(col, row, this);
			if(!added) 
				throw new InvalidPositionException("[DEBUG] Position (" + col + ", " + row + "): " + invalidPositionMsg);
		}else {
			throw new NoMoreVampiresException("[DEBUG] " + noVampsLeftMsg);
		}
		
		evolve = false;
		
		return added;
	}
	
	//"artificial" addition of dracula to debug
	public boolean addDraculaCommand(int col, int row) throws InvalidPositionException, NoMoreVampiresException, DraculaHasArisenException {
		boolean added = false;
		if(!DraculaOnBoard && Vampire.getVampsLeft() > 0) {
			added = board.addDracula(col, row, this);	
			if(!added) 
				throw new InvalidPositionException("[DEBUG] Position (" + col + ", " + row + "): " + invalidPositionMsg);
			else
				DraculaOnBoard = true;
		}else if (!(Vampire.getVampsLeft() > 0)){
			throw new NoMoreVampiresException("[DEBUG] " + noVampsLeftMsg);
		}
		else { //Dracula.getAppearedBefore() == true
			throw new DraculaHasArisenException( "[DEBUG] " + draculaAlreadyMsg);
		}
		
		evolve = false;
		
		return added;
	}
	
	//"artificial" addition of explosive vampires to debug
	public boolean addExplosiveVampireCommand(int col, int row) throws InvalidPositionException, NoMoreVampiresException{
		boolean added = false;
		if(Vampire.getVampsLeft() > 0) {
			added = board.addExplosiveVampire(col, row, this);	
			if(!added)
				throw new InvalidPositionException("[DEBUG] Position (" + col + ", " + row + "): " + invalidPositionMsg);
		}else {
			throw new NoMoreVampiresException("[DEBUG] " + noVampsLeftMsg);
		}
		
		evolve = false;
		
		return added;
	}
	
	
	//removes references, in lists, to objects that are dead
	public void removeDeadObj() {
		board.removeDeadObj();
	}
	
	
	
	//Comand execution methods:
	
	//in charge of setting "evolve" to the value corresponding to the save command
	public void saveCommand(){
		evolve = false;
	}
	
	//to execute exit game
	public void exitCommand() {
		isFinished = true;
		evolve = false;
	}

	
	//in charge of setting "evolve" to the value corresponding to the stringify command
	public void stringifyCommand(){
		evolve = false;
	}
	
	
	//resets game
	public void resetCommand() {
		player.setCoins(INITIAL_COINS);
		cycles = 0;
		board.reset(level.getVampNumber());
		DraculaOnBoard = false;
		evolve = false;
	}
	
	//checks if player affords lightFlash, and if so, calls method in board in charge of it
	public boolean lightFlashCommand(int cost) throws NotEnoughCoinsException {
		boolean flash = false;
		if (player.canAfford(cost)) {
			board.receiveLightFlash();
			removeDeadObj();
			player.payCoins(cost);
			flash = true;
		}else 
			throw new NotEnoughCoinsException("[DEBUG] Light Flash cost is " + cost + " " + player.toStringNotEnoughCoins());
		
		if(flash)
			evolve = true;
		else 
			evolve = false;
		
		return flash;
	}
	
	
	//checks if player affords garlicPush, and if so, calls method in board in charge of it
	public boolean garlicPushCommand(int cost)  throws NotEnoughCoinsException {
		boolean push = false;
		if (player.canAfford(cost)) {
			board.receiveGarlicPush();
			player.payCoins(cost);
			push = true;
		} else {
			throw new NotEnoughCoinsException("[DEBUG] Garlic Push cost is " + cost + " " + player.toStringNotEnoughCoins());
		}
		
		if(push)
			evolve = true;
		else 
			evolve = false;
		
		return push;
	}
	
	
	//true if the element that is going to move to position newX, newY can move backwards due to garlicPush
	public boolean garlicPushEffect(int newX, int newY) {
		return board.isFree(newX, newY);
	}

	
	//implements superCoins
	public boolean superCoinsCommand(int coins) {
		addCoinsToPlayer(coins);
		evolve = false;
		return true;
	}
	
	//implements help command
	public void helpCommand() {
		evolve = false;
		System.out.println(CommandGenerator.commandHelp());//TODO move
	}
	
	public void updateCommand() {
		evolve = true;
	}
	
	
	//Methods to add an element (by user command):
	
	public boolean addSlayerCommand(int x, int y) throws InvalidPositionException, NotEnoughCoinsException {
		boolean added = false;
		if (x != level.getColumns() - 1 && board.isFree(x, y)) { //because we cannot add slayer on last column 
			int cost = Slayer.getCost();
			if (cost <= player.getCoins()) { //can afford
				board.addSlayer(x, y, this); 
				player.payCoins(cost);
				added = true;
			} else //if cannot afford
				throw new NotEnoughCoinsException("[DEBUG] Slayer cost is " + cost + ". " + player.toStringNotEnoughCoins());
		}else
			throw new InvalidPositionException("[DEBUG] Position (" + x + ", " + y + "): " + invalidPositionMsg);
		
		if(added)
			evolve = true;
		else 
			evolve = false;
		
		return added;
	}


	public boolean addBloodBankCommand(int x, int y, int cost) throws InvalidPositionException, NotEnoughCoinsException {
		boolean added = false;
		if (x != level.getColumns() - 1 && board.isFree(x, y)) { //cannot add blood bank on last column 
			if (player.canAfford(cost)) {
				board.addBloodBank(x, y, cost, this); 
				player.payCoins(cost);
				added = true;
			} else //cannot afford
				throw new NotEnoughCoinsException("[DEBUG] Bloodbank cost is " + cost + ". " + player.toStringNotEnoughCoins());
		}
		else {
			throw new InvalidPositionException("[DEBUG] Position (" + x + ", " + y + "): " + invalidPositionMsg);
		}
		
		if(added)
			evolve = true;
		else 
			evolve = false;
		
		return added;
	}
	

	
	//returns IAttack object on (i, j) if there is one
	public IAttack getAttackableInPos(int i, int j) {
		return board.getAttackable(i, j);
	}

	
	public void incrementCycles() {
		cycles++;
	}
	
	//takes actions necessary if slayers have killed all possible vampires, or vampires have reached end of board
	//updates winnerMsg string corresponding to who has won, or "" if no one has won yet, and updates isFinished boolean
	public void checkEnd() {
		if (board.playerWins()) {
			winnerMsg = "Player wins!";
			isFinished = true;
		} else if (board.vampsWin()) {
			winnerMsg = "Vampires win!";
			isFinished = true;
		}
	}
	

	//true if game has come to an end
	public boolean isFinished() {
		return this.isFinished;
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
		state += '\n' + "GameElement List:" + '\n';
		state += board.stringify();
		return state;
	}
	

	@Override
	public String getInfo() {
		String str;
		char jumpLine = '\n';
		str = jumpLine + "Cycle number: " + cycles + jumpLine;
		str += "Coins: " + player.getCoins() + jumpLine;
		str += "Remaining vampires: " + Vampire.getVampsLeft() + jumpLine;
		str += "Vampires on the board: " + Vampire.getVampsOnBoard() + jumpLine;
		if (DraculaOnBoard)
			str += DraculaArisenMsg + jumpLine;
		
		return str;
	}
	
	
	//Getters
	
	public String getWinnerMessage() {
		return winnerMsg;
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

}
