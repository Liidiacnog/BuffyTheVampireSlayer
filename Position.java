public class Position {

	public int x, y; //TODO correct if it is public?, ask Simon.
	public Position(int i, int j) {
		x = i;
		y = j;
	}
	
	public boolean equals(Position pos) {
		return pos.x == x && pos.y == y;
	}
}
