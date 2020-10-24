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
		for (int i = 0; i < rows; i++) {
			System.out.println(rowSeparation);
			for (int j = 0; j < columns; j++) {
				System.out.print("|");
				if (vamps.isHere(i + 1,  Math.abs(rows - j)) != -1) {
					vamps.draw(i);
				} else if (slayers.isHere(i + 1,  Math.abs(rows - j)) != -1){
					slayers.draw(i);
				} else {
					System.out.print("        ");
				}
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
	
}
