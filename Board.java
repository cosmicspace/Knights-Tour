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
// loop to start of loop
//
// Note: There is a discrepancy between array numbers and position numbers for the spaces on the board
// Array numbers are numbered 0-63 for 8x8 board
// boolean arrays of goodPosNotOnCurrentPath and goodPosNotExhausted will be numbered as arrays (just like normal)
//
// Position numbers are numbered 1-64 for 8x8 board
// Sizes will generally be in terms of position numbers
//
//

import java.util.*; // for Stack class

public class Board {
	Stack<Integer> boardStack = new Stack<Integer>();
	private boolean[] goodPosNotOnCurrentPath; // reads as good position, not on current path
	private boolean[] goodPosNotExhausted; // reads as good position, not exhausted. This means there are other paths to explore from this position
	private int size;
	private boolean notDone = true;
	int movesCount = 0;
	int squared = 0;
        int pos = 0;
	private boolean success = false; // true indicates a solution was found, while false indicates no solution found 
	private static int[][] possibleMoves =
		{ {+1,+2},{+2,+1},{+2,-1},{+1,-2},{-1,-2},{-2,-1},{-2,+1},{-1,+2} };
	// changed from original to follow clock pattern of numbers, for easier, more intuitive, debugging
	
	public Board(int dimension) { // constructor
		size = dimension;
		// initialize boolean values to be "good" to use
		squared = (size*size); // number of squares on the board, so the size of the array 
		goodPosNotOnCurrentPath = new boolean[squared];
		goodPosNotExhausted = new boolean[squared];
		for (int i = 0; i<convertPosToArray(squared) +1; i++) {
			System.out.println("i="+i);; //debugging output
			goodPosNotOnCurrentPath[i] = true;
			goodPosNotExhausted[i] = true;
		} // end for 
	} // end constructor

	public Stack<Integer> getPath(int position) {
		System.out.println("Starting method: getPath"); // debugging output
                pos = position;
                System.out.println("pos = " + pos);
		int poppedItem = 0;
		boardStack.push(position);
		System.out.println("notDone=" + notDone); // debugging output
		System.out.println("boardStack=" + boardStack); // debugging output		
		while (notDone) {
			pressAnyKey(); // debugging pause to prevent fast looping and overscrolling
                        if (boardStack.size() < 1) { // failure, down to first position with no moves
					System.out.println("Failure since down to first position with no moves"); // debugging output
					success = false; // since no solution was found
					notDone = false; // with a failure, we are done
					Stack<Integer> emptyStack = new Stack<Integer>(); // make an empty stack
					boardStack = emptyStack;
                                        System.out.println(" EPIC FAIL");
                                        break;
				
                        }
			int nextPos = getNextPos(boardStack.peek());
			System.out.println("nextPos=" + nextPos); // debugging output
                        System.out.println("boardstack.peek() = " + boardStack.peek());// what is boardStack.peek looking at?
                         if (nextPos == -1) { // failed to find moves from current position
				System.out.println("Failed for find moves from current position"); // debugging output
				System.out.println("boardStack.size()="+boardStack.size()); // debugging output
				if (boardStack.size() >= squared) { // success, every position was reached!
					System.out.println("Success, every position reached since boardStack>="+squared); // debugging output
					success = true; // since a solution was found
					notDone = false; // with a success, we are done
					return boardStack;
				}  else if (checkPosition (position) == false) { // stack size between 2 and (size^2)-2 which means we are in the middle but have run out of moves
					System.out.println("Backtrack since in the middle with no moves."); // debugging output
					movesCount++;
					poppedItem = boardStack.pop();
					goodPosNotExhausted[convertPosToArray(poppedItem)]=false;//
					goodPosNotOnCurrentPath[convertPosToArray(poppedItem)]=true; //
				} // end else	
			} else { // found a valid move
				System.out.println("Found a move @ nextPos=" + nextPos);
				boardStack.push(nextPos); // put found nextPos onto the stack
				goodPosNotOnCurrentPath[convertPosToArray(nextPos)] = false; // pushed item is now on the current path
				System.out.println("boardStack=" + boardStack); // debugging output
			} // end else
		} // end while
		// debugging output, in case we manage to get out of the while loop without succeeding or failing
		Stack<Integer> errorStack = new Stack<Integer>(); // make an empty stack for errors
		errorStack.push(-1);
                System.out.println("errorStack=" + errorStack);
		return errorStack;
	} // end method: getPath
	
	public int getNextPos(int currentPosition) { // picks next possible
		System.out.println("starting method: getNextPos"); // debugging output
		int cycle = 0;
		while(cycle < 8) { // move
			int dx = possibleMoves[cycle][0]; // get move in
			int dy = possibleMoves[cycle][1]; // (x,y) format
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
					System.out.println("Found position: " + nextPos); // debugging output
					return nextPos; // found a move
				} // end if(knightsTourBoard...
			} // end if(x>=0...)
		} // end while // no possible move		
		//cycle = 0; // reset move index
		return -1; // failure
	} // end getNextPos()
	
	public boolean checkPosition (int position) {
		if ((goodPosNotOnCurrentPath[convertPosToArray(position)]) && (goodPosNotExhausted[convertPosToArray(position)])) {
			return true;
		} else {
			return false;
		}
	} // end method checkPosition
	
	public boolean getSuccess () {
		return success;
	} // end method: checkPosition
	
	public void printOutput () {
		if (getSuccess() ) {
			System.out.println("SUCCESS:");
		} else {
			System.out.println("FAILURE:");
		}
		System.out.println("Total Number of Moves=" + movesCount);
		System.out.println("Moving Sequence: " + boardStack);
		System.out.println();
	} // end method: printOutput
	
	//While many can argue that this method is not necessary, I needed some help keeping things organized mentally
	public int convertPosToArray (int position) {
		int array = position - 1; 
		return array;
	} // end method: convertPosToArray

	//While many can argue that this method is not necessary, I needed some help keeping things organized mentally
	public int convertArrayToPos (int array) {
		int position = array + 1;
		return position;
	} // end method: convertArrayToPos	
	
	 private void pressAnyKey() { 
	        System.out.println("Press any key to continue...");
	        try {  System.in.read(); }  
	        catch(Exception e) {}
	        System.out.println();
	 } // end method: pressAnyKey
	 
	 private void outputVariables () {
		 System.out.println("");
	 }
} // end class Board