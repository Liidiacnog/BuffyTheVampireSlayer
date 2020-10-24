package logic;

import logic.lists.SlayerList;
import logic.lists.VampireList;

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
	
}
