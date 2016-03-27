// Lab 3 submitted for credit for CSCI-232
// A java implementation of the Knight's tour
// Authors: Jason Armstrong, Anthony Fopp, Kenneth Beartusk
// Instructor: Dr. Qing Yang
//

import java.io.*; // for reader and other io classes

public KnightsTourApp {
	private int n=0;
	// get size of board
			System.out.print("Enter board size (8 for 8x8 board) ");
			private int size = Integer.parseInt(getString());
			System.out.println("Enter the beginning square (1 to "+size+"):");
			private Board knightsTourBoard = new Board(size);	
}

=======

//--------------------------------------------------------------
private static int[][] moves8 =
			{ {+1,-2}, {+2,-1}, {+2,+1}, {+1,+2},{-1,+2}, {-2,+1}, {-2,-1}, {-1,-2} };

public int getNextPos() { // picks next possible
	while(cycle < 8) { // move
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
			if(Board.get(nextPos)==false) { // unoccupied cell?
				// yes
				return nextPos; // found a move
			}
		} // end if(x>=0...)
	} // end while // no possible move
	cycle = 0; // reset move index
	return -1; // failure
} // end getNextPos()
>>>>>>> origin/master
