package logic;

import logic.lists.*;

//tablero
//DOESNT KNOW WHERE THINGS ARE, IT HAS TO ASK THE LISTS

public class GameObjectBoard {
	
	private int rows, columns;
	private String rowSeparation = " ";
	private VampireList vamps;
	private SlayerList slayers;
	
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
	
	public void draw() {
		int pos;
		for (int i = 0; i < rows; i++) {
			System.out.println(rowSeparation);
			for (int j = 0; j < columns; j++) {
				System.out.print("|");
				pos = vamps.isHere(j + 1,  Math.abs(rows - i));
				if (pos != -1) {
					vamps.draw(pos);
				} else { 
					pos = slayers.isHere(j + 1,  Math.abs(rows - i));
					if (pos != -1){
						slayers.draw(pos);
					} else {
						System.out.print("        ");
					}
				}
				// System.out.print("  " + (j + 1) + "  " + Math.abs(rows - i) + "  ");
			}
			System.out.println("|");
		}
		System.out.println(rowSeparation);
	}

	public int vampsLeft() {
		return vamps.left();
	}

	public int vampsOn() {
		return vamps.onBoard();
	}

	public boolean validCords(int x, int y) {
		boolean valid = false;
		if (x > 0 && y > 0 && y <= rows && x <= columns) {
			if (slayers.isHere(x, y) == -1 && vamps.isHere(x, y) == -1) {
				valid = true;
			}
		}
		
		return valid;
	}

	public void addSlayer(int x, int y) {
		slayers.addSlayer(x, y);
	}
	
	public boolean isOccupied(int x, int y) {
		boolean occupied = false;
		
		return occupied;
	}
	
	
	public boolean canMove(int x, int y) {
		boolean can = false; 
		if(validCords(--x, y) && (vamps.isHere(--x, y) == -1) && (slayers.isHere(--x,  y) == -1))
				can = true;
		return can;
	}
	
	public int existsTargetVamp(int x, int y) { //returns pos = -1 if doesn't exist, and pos = position of hit vamp if found
		boolean exists = false;
		int i = 1, pos = -1;
		while(!exists && i < (columns - x + 1)) { //columns - x + 1 are positions on x axis that could be free at end of board
			if(vamps.isHere(x + i, y) != -1)
				exists = true;
			else 
				++i;
		}
		if(exists)
			pos = vamps.isHere(x + i, y));
		
		return pos;
	}
	
	public int existsTargetSlayer(int x, int y) { //returns pos = -1 if doesn't exist, and pos = position of hit vamp if found
		boolean exists = false;
		int i = 1, pos = -1;
		while(!exists && i < (columns - x + 1)) { //columns - x + 1 are positions on x axis that could be free at end of board
			if(vamps.isHere(x + i, y) != -1)
				exists = true;
			else 
				++i;
		}
		if(exists)
			pos = vamps.isHere(x + i, y));
		
		return pos;
	}
	
	
	public void moveVamps() {
		for(int i = 0; i < vamps.getSize(); ++i) {
			if(canMove(vamps.getX(i), vamps.getY(i)))
				vamps.moveVamps(i);
		}
	}
	
	public void slayersHit() {
		for(int position = 0; position < slayers.getSize(); ++position) {
			if(existsTargetVamp(slayers.getX(position), slayers.getY(position)) != -1) {
				vamps.getHit(existsTargetVamp(slayers.getX(position), slayers.getY(position)), slayers.getDamage(position));
			}
		}
	}
	
	
	public void vampsBite() {
		for(int i = 0; i < vamps.getSize(); ++i) {
			int newX = vamps.getX(i) - 1;
			if(validCords(newX, vamps.getY(i)) && slayers.isHere(newX, vamps.getY(i)) != -1) {
				slayers.getBitten(slayers.isHere(newX, vamps.getY(i)), vamps.getDamage(i));
			}
		}
	}
	
	
	
}
