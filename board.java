// Lab 3 submitted for credit for CSCI-232
// A java implementation of the Knight's tour
// Authors: Jason Armstrong, Anthony Fopp, Kenneth Beartusk
// Instructor: Dr. Qing Yang
//

//BASIC ALGORITHM
//---------------------------
//get board size and starting position
//put starting position on stack
//start loop
// 		check next positions from current position
//		if all next positions fail, check stack size
// 			if the stack size greater than (size^2)-2, then success! every position was reached (maybe check each position is on the current path as a backup). output the path
//			if the size less than 1, then the board setup failed, output failure for the board
//			otherwise stack is between 2 and size^2)-2, pop the stack and mark the popped item as exhausted (no further available paths from there), mark as not on the path
// 		if a position succeeds, push next position to the stack, mark item on top of stack as being on current path
//loop to start of loop

import java.util.*; // for Stack class

public class Board {
	private boolean[] goodPosNotOnCurrentPath; // reads as good position, not on current path
	private boolean[] goodPosNotExhausted; // reads as good position, not exhausted. This means there are other paths to explore from this position
	private int size;
	private boolean done = true;
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

	public Stack<Integer> getPath(int position) {
		Stack<Integer> boardStack = new Stack<Integer>();
		int poppedItem = 0;
		boardStack.push(position);
		while (!done) { // not done
			int nextPos = getNextPos(boardStack.peek());
			if (nextPos == -1) { // failed to find moves from current position
				if (boardStack.size() > (size^2)-2) { // success, every position was reached!
					done = true; // with a success, we are done
					return boardStack;
				} else if (boardStack.size() < 1) { // failure, down to first position with no moves
					done = true; // with a failure, we are done
					Stack<Integer> emptyStack = new Stack<Integer>(); // make an empty stack
					return emptyStack; // then return it
				} else { // stack size between 2 and (size^2)-2
					poppedItem = boardStack.peek();
					boardStack.pop();
					goodPosNotExhausted[poppedItem]=false;
					goodPosNotOnCurrentPath[poppedItem]=true;	
				}	
			} else {
				boardStack.push(nextPos); // put found nextPos onto the stack
				goodPosNotOnCurrentPath[nextPos] = false; // pushed item is now on the current path		
			}

		}
		// debugging output, in case we manage to get out of the while loop without succeeding or failing
		Stack<Integer> errorStack = new Stack<Integer>(); // make an empty stack for errors
		errorStack.push(-1);
		return errorStack;
	}
	
	public int getNextPos(int currentPosition) { // picks next possible
		int cycle = 0;
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