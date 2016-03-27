// Lab 3 submitted for credit for CSCI-232
// A java implementation of the Knight's tour
// Authors: Jason Armstrong, Anthony Fopp, Kenneth Beartusk
// Instructor: Dr. Qing Yang
//

import java.util.*; // for Stack class

public Board (int dimension) {
	private boolean goodPosNotOnCurrentPath[dimension]; // reads as good position, not on current path
	private boolean goodPosNotExhausted[dimension]; // reads as good position, not exhausted. This means there are other paths to explore from this position
	private Stack boardStack = null;
	
	// initialize boolean values to be "good" to use
	for (int i = 0; i<n; i++);{
		goodPosNotOnCurrentPath[i] = true;
		goodPosNotExhausted[i] = true;
	}
		
	private static int[][] moves8 =
			{ {+1,+2},{+2,+1},{+2,-1},{+1,-2},{-1,-2},{-2,-1},{-2,+1},{-1,+2} };
	// changed from original to follow clock pattern of numbers, for easier, more intuitive, debugging

	
	public checkPosition (int position) {
		if (goodPosNotOnCurrentPath[position] = true && 
			goodPosNotExhausted[i] = true) {
				return true;
	}
	public int getNextPos() { // picks next possible move
		while(cycle < 8) { 
			int dx = moves8[cycle][0]; // get move in
			int dy = moves8[cycle][1]; // (x,y) format
			int x = (position-1)%N; // translate from j
			int y = (position-1)/N; // to (x,y) format
			x = x + dx; // add move to
			y = y + dy; // position
			cycle++; // used this move
			if(x>=0 && x<N && y>=0 && y<N) { // on the board?
				// yes
				int nextPos = x + y*N + 1; // (x,y) to j
				if(knightsTourBoard.checkPosition(nextPos)==true) { // usable position?
					// yes					
					return nextPos; // found a move
				}
			} // end if(x>=0...)
		} // end while // no possible move
		cycle = 0; // reset move index
		return -1; // failure
	} // end getNextPos()
}