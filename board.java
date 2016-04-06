// Lab 3 submitted for credit for CSCI-232
// A java implementation of the Knight's tour
// Authors: Jason Armstrong, Anthony Fopp, Kenneth Beartusk
// Instructor: Dr. Qing Yang
//

public class Board {
	private boolean[] goodPosNotOnCurrentPath; // reads as good position, not on current path
	private boolean[] goodPosNotExhausted; // reads as good position, not exhausted. This means there are other paths to explore from this position
	private int[] solutionPath; // array to hold solution path
	private int size; // size of the chess board, of size*size 
	int movesCount = 0; // running count of moves made
	int squared = 0; // square of size, for simplicity
	private boolean success = false; // true indicates a solution was found, while false indicates no solution found 
	private static int[][] possibleMoves =
		{ {+1,+2},{+2,+1},{+2,-1},{+1,-2},{-1,-2},{-2,-1},{-2,+1},{-1,+2} };
	// 2D array of potential knight moves
	// changed from original to follow clock pattern of numbers, for easier, more intuitive, debugging
	
	public Board(int dimension) { // constructor
		size = dimension;
		squared = (size*size); // number of squares on the board, so the size of the array
 		goodPosNotOnCurrentPath = new boolean[squared];
		goodPosNotExhausted = new boolean[squared];
		solutionPath = new int[squared];

		// initialize array values to be "good" to use
		for (int i = 0; i < ( (convertPosToArray(squared)) + 1 ); i++) {
			goodPosNotOnCurrentPath[i] = true;
			goodPosNotExhausted[i] = true;
			solutionPath[i] = 0;
		} // end for
} // end constructor

	public void getPath(int currentPosition) {
		// recursive function to find knights tour path
		// base case: path failure of reaching a point where we have no further moves
		//outputArrays();
		int nextPosition = -1;
		goodPosNotOnCurrentPath[convertPosToArray(currentPosition)] = false;

		int cycle = 0;
		while(cycle < 8) { // move
			// get move in (x,y) format
			int dx = possibleMoves[cycle][0];
			int dy = possibleMoves[cycle][1];
			// translate from h to (x,y) format
			int x = (convertPosToArray(currentPosition)) % size;
			int y = (convertPosToArray(currentPosition)) / size;
			// add move to position
			x = x + dx;
			y = y + dy;
			cycle++; // used this move
			if(x >= 0 && x < size && y >= 0 && y < size) { // on the board?
				// yes
				// (x,y) to j
				nextPosition = x + y*size + 1;
				if(checkPosition(nextPosition)) { // usable position?
					System.out.println("Found usable position at " + nextPosition);
					// yes
					movesCount++;
					goodPosNotExhausted[convertPosToArray(currentPosition)] = false;
					goodPosNotOnCurrentPath[convertPosToArray(currentPosition)] = true;
					getPath(nextPosition); // found a move
					for (int i= solutionPath.length; i<=1; i--) {
						solutionPath[i-1] = solutionPath[i-2];
					} // end for loop
					solutionPath[0] = currentPosition;
				} // end if
			} // end if(x>=0...)
		} // end while	
	} // end method: getPath

	public boolean checkPosition (int position) {
		if ( (goodPosNotOnCurrentPath[convertPosToArray(position)]) && goodPosNotExhausted[convertPosToArray(position)] ) {
			return true;
		} else {
			return false; }
	} // end method checkPosition
	
	public boolean getSuccess () {
		return success;
	} // end method: getSuccess
	
	public void printOutput () {
		if (getSuccess() ) {
			System.out.println("SUCCESS:");
		} else {
			System.out.println("FAILURE:");
		}
		System.out.println("Total Number of Moves=" + movesCount);
		System.out.print("Moving Sequence: ");
		for (int i=0; i<squared; i++) {
			System.out.print(solutionPath[i] + " ");
		}
		System.out.println();
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
	
	public void pressEnter() { 
		System.out.println("Press enter to continue...");
		try {  System.in.read(); }  
		catch(Exception e) {}
		System.out.println();
	} // end method: pressAnyKey
	
	public void outputArrays () { // debugging output
		System.out.print("> goodPosNotOnPath=");
		for (int i=0; i<squared; i++) {
			System.out.print(goodPosNotOnCurrentPath[i] + " ");
		}
		System.out.println();
		
		System.out.print("> goodPosNotExhausted=");
		for (int i=0; i<squared; i++) {
			System.out.print(goodPosNotExhausted[i] + " ");
		}
		System.out.println();
		
		System.out.print("> solutionPath=");
		for (int i=0; i<squared; i++) {
			System.out.print(solutionPath[i] + " ");
		}
		System.out.println();
		pressEnter(); // debugging pause
	}
} // end class Board