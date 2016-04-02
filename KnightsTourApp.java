// Lab 3 submitted for credit for CSCI-232
// A java implementation of the Knight's tour
// Authors: Jason Armstrong, Anthony Fopp, Kenneth Beartusk
// Instructor: Dr. Qing Yang
//

import java.io.*; // for reader and other io classes

public class KnightsTourApp {
	Board knightsTourBoard;
	int position;
	
	public static void main(String[] args) throws IOException {
		int size = 0;
		int position = 0;
		
		// get size of board
		System.out.print("Enter board size (8 for 8x8 board) ");
		size = Integer.parseInt(getString());
		System.out.println("Enter the beginning square (1 to "+size+"):");
		position = Integer.parseInt(getString());
		Board knightsTourBoard = new Board(position);	
		knightsTourBoard.getPath(position);
	}  // end method main()
	
	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	} // end method getString

	public static String getText() throws IOException {
		String outStr="", str = "";
		while(true) {
			str = getString();
			if( str.equals("$") ){
				// System.out.println(outStr); // debugging output
				return outStr;
			} // end if
			outStr = outStr + str + "\n";
		} // end while
	} // end mtehod:getText()

	public static char getChar() throws IOException {
		String s = getString();
		return s.charAt(0);
	} // end method:getChar
	
	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	} // end method:getInt
	
	public void printOutput () {
		if (knightsTourBoard.getSuccess() ) {
			System.out.println("SUCCESS:");
		} else {
			System.out.println("FAILURE:");
		}
		System.out.println("Total Number of Moves="+knightsTourBoard.movesCount);
		System.out.println("Moving Sequence: "+knightsTourBoard.boardStack);
	}
} // end class KnightsTourApp
