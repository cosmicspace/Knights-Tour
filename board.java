// Lab 3 submitted for credit for CSCI-232
// A java implementation of the Knight's tour
// Authors: Jason Armstrong, Anthony Fopp, Kenneth Beartusk
// Instructor: Dr. Qing Yang
//

public class Board {
	private int[] solutionPath;
	private boolean[] goodPosNotOnCurrentPath; // reads as good position, not on current path
	private boolean[] goodPosNotExhausted; // reads as good position, not exhausted. This means there are other paths to explore from this position
	private int size;
	int movesCount = 0;
	int squared = 0;
	private boolean success = false; // true indicates a solution was found, while false indicates no solution found 
	private static int[][] possibleMoves =
		{ {+1,+2},{+2,+1},{+2,-1},{+1,-2},{-1,-2},{-2,-1},{-2,+1},{-1,+2} };
	// changed from original to follow clock pattern of numbers, for easier, more intuitive, debugging
	
	public Board(int dimension) { // constructor
		int[] solutionPath = new int[squared];
		size = dimension;
		// initialize boolean values to be "good" to use
		squared = (size*size); // number of squares on the board, so the size of the array 
		goodPosNotOnCurrentPath = new boolean[squared];
		goodPosNotExhausted = new boolean[squared];
		for (int i = 0; i<convertPosToArray(squared)+1; i++) {
			System.out.println("i="+i);; //debugging output
			goodPosNotOnCurrentPath[i] = true;
			goodPosNotExhausted[i] = true;
			// outputArrays(); // debugging output
		} // end for 
	} // end constructor

	public void getPath(int currentPosition) {
		// recursive function to find knights tour path
		// base case: path failure of reaching a point where we have no further moves
		
		System.out.println("Starting method: getPath from position:" + currentPosition); // debugging output
		pressAnyKey();
		
		int nextPosition = -1;
		goodPosNotOnCurrentPath[convertPosToArray(currentPosition)] = false;
		outputArrays(); // debugging output
		nextPosition = getNextPos(currentPosition);
		System.out.println("nextPosition=" + nextPosition); // debugging output
		if (nextPosition == -1) { // failure to find a valid new position
			// do nothing, ending recursion as a base case
			
			//output path info for debugging
			System.out.print("Reached dead end with solutionPath=");
			for (int i=0; i<squared; i++) {
				System.out.print(solutionPath[i] + " ");
			}
			System.out.println();
			
		} else { // found a move, so continue recursion
			System.out.println("Found a move, continuing recursion");
			movesCount++;
			System.out.println("movesCount=" + movesCount);// debugging output
			getPath(nextPosition);
			goodPosNotExhausted[convertPosToArray(currentPosition)] = false;
			goodPosNotOnCurrentPath[convertPosToArray(currentPosition)] = true;
			outputArrays(); // debugging output
			solutionPath = insertPosition(solutionPath, currentPosition); // add position to the beginning of the array
			
			//output path info for debugging
			System.out.print("solutionPath=");
			for (int i=0; i<squared; i++) {
				System.out.print(solutionPath[i] + " ");
			}
			System.out.println();
		} // end if
	}
	
	public int[] insertPosition(int[] array, int number) { // add position to the beginning of the array
		for (int i=array.length; i<=1; i--) {
			array[i-1]=array[i-2];
		} // end for loop
		array[0]=number;
		return array;
	}

	public int getNextPos(int currentPosition) { // picks next possible
		System.out.println("starting method: getNextPos=" + currentPosition); // debugging output
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
				int nextPos	= x + y*size + 1; // (x,y) to j
				if(checkPosition(nextPos)) { // usable position?
					// yes
					System.out.println("Found position: " + nextPos); // debugging output
					return nextPos; // found a move
				} // end if(knightsTourBoard...
			} // end if(x>=0...)
		} // end while // no possible move		
		cycle = 0; // reset move index
		return -1; // failure
	} // end getNextPos()
	
	public boolean checkPosition (int position) {
		System.out.println("Checking position:" + position);
		if ( (goodPosNotOnCurrentPath[convertPosToArray(position)]) && goodPosNotExhausted[convertPosToArray(position)] ) {
			outputArrays();
			return true;
		} else {
			return false; }
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
		System.out.println("Moving Sequence: " + solutionPath);
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
	
	public void pressAnyKey() { 
		System.out.println("Press any key to continue...");
		try {  System.in.read(); }  
		catch(Exception e) {}
		System.out.println();
	} // end method: pressAnyKey
	
	public void outputArrays () { // debugging output
		System.out.print("goodPosNotOnPath=");
		for (int i=0; i<squared; i++) {
			System.out.print(goodPosNotOnCurrentPath[i] + " ");
		}
		System.out.println();
		System.out.print("goodPosNotExhausted=");
		for (int i=0; i<squared; i++) {
			System.out.print(goodPosNotExhausted[i] + " ");
		}
		System.out.println();

	}
} // end class Board