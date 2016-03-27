// Lab 3 submitted for credit for CSCI-232
// A java implementation of the Knight's tour
// Authors: Jason Armstrong, Anthony Fopp, Kenneth Beartusk
// Instructor: Dr. Qing Yang
//

import java.util.*; // for Stack class

public class Board {
	private boolean[] goodPosNotOnCurrentPath; // reads as good position, not on current path
	private boolean[] goodPosNotExhausted; // reads as good position, not exhausted. This means there are other paths to explore from this position
	private int cycle;
	private int currentPosition;
	private int size;
	private Stack boardStack = null;
	private static int[][] moves8 =
		{ {+1,+2},{+2,+1},{+2,-1},{+1,-2},{-1,-2},{-2,-1},{-2,+1},{-1,+2} };
	// changed from original to follow clock pattern of numbers, for easier, more intuitive, debugging
	
	public Board(int dimension) { // constructor
		// initialize boolean values to be "good" to use
		for (int i = 0; i<dimension; i++) {
			goodPosNotOnCurrentPath[i] = true;
			goodPosNotExhausted[i] = true;
		} // end for 
	} // end constructor

	public int getNextPos() { // picks next possible
		while(cycle < 8) { // move
			int dx = moves8[cycle][0]; // get move in
			int dy = moves8[cycle][1]; // (x,y) format
			int x = (currentPosition-1)%size; // translate from j
			int y = (currentPosition-1)/size; // to (x,y) format
			x = x + dx; // add move to
			y = y + dy; // position
			cycle++; // used this move
			if(x>=0 && x<size && y>=0 && y<size) { // on the board?
				// yes
				int nextPos	 = x + y*size + 1; // (x,y) to j
				if(checkPosition(nextPos)) { // usable position?
					// yes
					return nextPos; // found a move
				} // end if(knightsTourBoard...
			} // end if(x>=0...)
		} // end while // no possible move		
		cycle = 0; // reset move index
		return -1; // failure
	} // end getNextPos()
	
	public boolean checkPosition (int position) {
		if ((goodPosNotOnCurrentPath[position] == true) && (goodPosNotExhausted[position] == true)) {
			return true;
		} else {
			return false;
		}
	} // end method checkPosition

} // end class Board