package logic;

/*
 * This is an Enum class that contains the values which correspond to each level of the game. Any level-dependent code of
 * the program should be in this class (so that if, at a later time, we decided to add a new level or remove an existing
 * level, we would only need to change the code of the Level class itself in order to do so).
 */

/*The game can be played at three levels, EASY, HARD and INSANE, where the level determines various configuration options 
 * (see Table 1.1), in particular, the probability on each cycle that a new vampire is added to the game. If a vampire is
 *  to be added, the row in which it appears is chosen at random. If there is already a vampire in the chosen row then 
 *  the new vampire is not placed on the board.

Level	Number of vampires	Frecuency	board width	board height
EASY	3						0.1				8		4
HARD	5						0.2				7		3
INSANE	10						0.3				5		6
Table 1.1: Configuration for each level of difficulty

Table 1.1 shows the different values for the configuration options that depend on the level. These are:
The total number of vampires that appear in a game.
The frequency of appearance of vampires, which determines the probability that a vampire appears on a given cycle. Thus,if the frequency is 0.2, 
a vampire appears randomly on each cycle with a probability of one in five.
The dimensions of the board; at the easiest level the board dimensions are 8 × 4 and at the hardest level 5 × 6.
*/


public enum Level {
	EASY("easy", 3, 0.1, 8, 4), HARD("hard", 5, 0.2, 7, 3), INSANE("insane", 10, 0.3, 5, 6);

	private String name;
	private int numberOfVampires;
	private double vampireFrequency;
	private int dim_x, dim_y;

	private Level(String name, int numberOfVampires, double vampireFrequency, int dim_x, int dim_y) {
		this.name = name;
		this.numberOfVampires = numberOfVampires;
		this.vampireFrequency = vampireFrequency;
		this.dim_x = dim_x;
		this.dim_y = dim_y;
	}

	/*public static int getValue(Level lvl) {
		int value = 0;
		if(lvl == EASY)
			value = 1;
		else if (lvl == HARD)
			value = 2;
		else if(lvl == INSANE)
			value = 3;
		
		return value;	
	}*/ //never used

    public static Level parse(String inputString) {
        for (Level level : Level.values())
            if (level.name().equalsIgnoreCase(inputString)) 
            	return level;
        return null;
    }
    
    public static String all(String separator) {
        String allLevels = "";
        for (Level level : Level.values())
            allLevels += level.name() + separator;
        return allLevels.substring(0, allLevels.length() - separator.length());
    }

	public int getVampNumber() {
		return numberOfVampires;
	}
	
	public double getvampireFrequency() {
		return vampireFrequency;
	}

	public int getRows() {
		return dim_y;
	}

	public int getColums() {
		return dim_x;
	}

}
