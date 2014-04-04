/**
 *
 * @author Nicki Ogg
 */
public class Location {
    private int row, col;
    public Location(int r, int c) {
	row = r;
	col = c;
    }
    public int getRow() {
	return row;
    }
    public int getCol() {
	return col;
    }
    public boolean equals(Object other) {
	if (!(other instanceof Location))
	    return false;
	Location loc = (Location) other;
	if (row == loc.getRow() && col == loc.getCol())
	    return true;
	return false;
    }
    public String toString() {
	return "Location[" + row + ", " + col + "]";
    }
}
