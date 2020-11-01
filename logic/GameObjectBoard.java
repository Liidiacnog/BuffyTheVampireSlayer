package logic;

import logic.lists.*;

/*This class encapsulates the state and behaviour of the board. It contains (a reference to) an instance of the VampireList
class and of the SlayerList class, together with the methods for managing the access to the objects of these lists. 
The Game class delegates much of its functionality to the Board class. 
Note that there will only ever be one instance of the Board class in the program, which we will refer to as the board object.
//Doesn't know where things are, it has to ask the lists
*/

public class GameObjectBoard {
	
	private int rows, columns;
	private String rowSeparation = " ";
	
	private VampireList vamps;
	private SlayerList slayers;
	private Game currentGame;
	
	
	public GameObjectBoard(Level lvl) {
		rows = lvl.getRows();
		columns = lvl.getColums();
		for (int i = 0; i < columns; i++) {
			rowSeparation = rowSeparation + "--------";
			if (i != 0)
				rowSeparation = rowSeparation + "-";
		}
		vamps = new VampireList(lvl.getVampNumber());
		slayers = new SlayerList(rows * columns);
	}
	
		//TODO no lo vamos a usar, por gamePrinter
	public String toString() {
		int pos;
		String boardString = "";
		for (int i = 0; i < rows; i++) {
			boardString = rowSeparation;
			for (int j = 0; j < columns; j++) {
				boardString += "|";
				pos = vamps.isHere(j + 1,  Math.abs(rows - i));
				if (pos != -1) {
					boardString += vamps.toString(pos);
				} else { 
					pos = slayers.isHere(j + 1,  Math.abs(rows - i));
					if (pos != -1){
						boardString += slayers.toString(pos);
					} else {
						boardString += "        ";
					}
				}
				// System.out.print("  " + (j + 1) + "  " + Math.abs(rows - i) + "  "); Coordenadas de cada casilla
			}
			boardString += "|";
		}
		boardString += rowSeparation;
		return boardString;
	}

	
	public String objectOn(int x, int y) {
		String object = "";
		if(slayers.isHere(x, y) != -1)
			object = slayers.representation(slayers.isHere(x, y));
		else 
			object = vamps.representation(vamps.isHere(x, y));
		return object;
	}
	
	
	public int vampsLeft() {
		return vamps.left();
	}

	public int vampsOnBoard() {
		return vamps.onBoard();
	}

	public boolean validCords(int x, int y) {
		boolean valid = false;
		if (x >= 0 && y >= 0 && y < rows && x < columns) {
			valid = true;
		}
		
		return valid;
	}
	
	public boolean isFree (int x, int y) {
		boolean valid = false;
		if (validCords(x, y) && slayers.isHere(x, y) == -1 && vamps.isHere(x, y) == -1)
				valid = true;

		return valid;
	}

	public void addVampire() { //we suppose it's only called when we haven't reached max number of vampires yet
		int x = columns - 1; //vamps appear on last column always
		int y = currentGame.getRandomClassNextInt(rows);
		if(vamps.isHere(x,  y) == -1) //no vampire in that position
			vamps.addVamp(x, y);
	}
	
	public void addSlayer(int i, int j) {//we suppose it's only called when Player can afford it
		int x = i;
		int y = j;
		if(isFree(x, y))
			slayers.addSlayer(x, y);
	}
	
	
	public boolean vampCanMove(int x, int y) {//true if new position of vamp is free
		return isFree(x--, y);
	}
	
	public int existsTargetVamp(int x, int y) { 
		//returns pos = -1 if doesn't exist, and pos = position of hit vamp if found
		boolean exists = false;
		int i = 1, pos = -1;
		while(!exists && i < (columns - (x+1))) { //columns - (x+1) (+1 bc coords start on 0) are positions on x axis that could be free at end of board
			if(vamps.isHere(x + i, y) != -1)
				exists = true;
			else 
				++i;
		}
		if(exists)
			pos = vamps.isHere(x + i, y);
		
		return pos;
	}
	
	public int existsTargetSlayer(int x, int y) { //returns pos = -1 if doesn't exist, and pos = position of hit vamp if found
		boolean exists = false;
		int i = 1, pos = -1;
		while(!exists && i < (columns - (x+1))) { //columns - (x+1) (+1 bc coords start on 0) are positions on x axis that could be free at end of board
			if(vamps.isHere(x + i, y) != -1)
				exists = true;
			else 
				++i;
		}
		if(exists)
			pos = vamps.isHere(x + i, y);
		
		return pos;
	}
	
	
	public void moveVamps() {
		for(int i = 0; i < vamps.getSize(); ++i) {
			if(vampCanMove(vamps.getX(i), vamps.getY(i)) && vamps.getMoved(i))
				vamps.moveVamps(i);
		}
	}
	
	public void slayersHit() {
		for(int pos = 0; pos < slayers.getSize(); ++pos) {
			if(existsTargetVamp(slayers.getX(pos), slayers.getY(pos)) != -1) {
				vamps.beenHit(existsTargetVamp(slayers.getX(pos), slayers.getY(pos)), slayers.getDamage(pos));
			}
		}
	}
	
	
	public void vampsBite() {
		for(int i = 0; i < vamps.getSize(); ++i) {
			int newX = vamps.getX(i) - 1;
			if(validCords(newX, vamps.getY(i)) && slayers.isHere(newX, vamps.getY(i)) != -1) {
				slayers.beenBitten(slayers.isHere(newX, vamps.getY(i)), vamps.getDamage(i));
			}
		}
	}

	public boolean vampsWin() {
		return vamps.wins();
	}
	
	public static int getCostSlayers() {
		return SlayerList.getCost();
	}
	
}
