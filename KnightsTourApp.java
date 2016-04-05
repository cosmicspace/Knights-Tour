// Lab 3 submitted for credit for CSCI-232
// A java implementation of the Knight's tour
// Authors: Jason Armstrong, Anthony Fopp, Kenneth Beartusk
// Instructor: Dr. Qing Yang
//

import java.io.*; // for reader and other io classes

public class KnightsTourApp {
	int position;
	
	public static void main(String[] args) throws IOException {
		int size = 0;
		int position = 0;
		int squared = 0;
		// get size of board
		System.out.print("Enter board size (8 for 8x8 board) ");
		size = Integer.parseInt(getString());
		squared = size*size;
		System.out.println("Enter the beginning square (1 to "+squared+"):");
		position = Integer.parseInt(getString());
		Board knightsTourBoard = new Board(size);
                while(knightsTourBoard.pos != -1){
		knightsTourBoard.getPath(position);
                }
		knightsTourBoard.printOutput();
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
	} // end method:getText()

	public static char getChar() throws IOException {
		String s = getString();
		return s.charAt(0);
	} // end method:getChar
	
	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	} // end method:getInt
	

	
} // end class KnightsTourApp
