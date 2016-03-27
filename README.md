Lab 3 submitted for credit for CSCI-232
A java implementation of the Knight's tour
Authors: Jason Armstrong, Anthony Fopp, Kenneth Beartusk
Instructor: Dr. Qing Yang

What do we need for this?

CLASSES
---------------------------
KnightsTourApp, need to get info first and then create knights tour board
Board

VARIABLES
---------------------------
array of booleans to track positions that are off limits for the current path goodPosNotOnCurrentPath
array of booleans to track positions that have been exhausted of possibilities goodPosNotExhausted
stack (array) of NxN 
position integer (will range from 1-NxN)

METHODS
---------------------------
get input
check position for being a valid move
get next position
convert position to xy
convert xy to position 
printStack for debugging
printBooleans for debugging

BASIC ALGORITHM
---------------------------
get board size and current position
start loop
 		check next positions from current position
		if all next positions fail, check stack size
 		if the stack size greater than 62, then success! every position was reached (maybe check each position is on the current path as a backup). output the path
		if the size less than 2, then the board setup failed, output failure for the board
		otherwise stack is 2-62, pop the stack and mark the popped item as exhausted (no further available paths from there)
 		if a position succeeds, add current position to the stack, mark current position as being on current path, mark next position as current position
loop to start of loop

TEST CASES
---------------------------
upper right corner (n^2 position) will fail on first position check
size=3 will fail ultimately. 
one path until failure: 1 8 3 4 9 2 7 6
another path until failure: 1 6 7 2 9 4 3 8x8

From the Assignment pdf:
"The Knight’s Tour is an ancient and famous chess puzzle. The object is to move a knight from one
square to another on an empty chessboard until it has visited every square exactly once. Write a
program that solves this puzzle using the idea of depth-first search. It’s best to make the board size
variable so that you can attempt solutions for smaller boards. The regular 8×8 board can take years to
solve on a desktop computer, but a 5×5 board takes only a minute or so. An animation of the Knight’s
Tour problem can be found from the following link:
https://en.wikipedia.org/wiki/Knight%27s_tour#/media/File:Knights-Tour-Animation.gif
It may be easier to think of a new knight being created and remaining on the new square when a move
is made. This way, a knight corresponds to a vertex, and a sequence of knights can be pushed onto a
stack. When the board is completely filled with knights (the stack is full), you win. In this problem the
board is traditionally numbered sequentially, from 1 at the lower-left corner to 64 at the upper-right
corner (or 1 to 25 on a 5×5 board). When looking for its next move, a knight must 1) make a legal
knight’s move, 2) not move off the board or onto an already-occupied (visited) square. A legal knight’s
move is defined as moving to a square that is two squares horizontally and one square vertically, or two
squares vertically and one square horizontally. The following codes may help you to understand how to
move a knight on a chessboard."


Sample output, also from assignment pdf file: "

Enter board size (8 for 8x8 board): 5
Enter the beginning square (1 to 25): 2
FAILURE:
Total Number of Moves=3658842
Moving Sequence: (2)

Enter board size (8 for 8x8 board): 5
Enter the beginning square (1 to 25): 1
SUCCESS:
Total Number of Moves=57
Moving Sequence: 1 8 5 14 25 18 9 20 23 16 7 4 15 24 17 6 3 10 13 2 11
22 19 12 (21)

Enter board size (8 for 8x8 board): 8
Enter the beginning square (1 to 64): 1
SUCCESS:
Total Number of Moves=6484066
Moving Sequence: 1 11 5 15 32 47 64 54 39 24 30 40 55 61 46 31 16 22 7
13 23 8 14 29 44 38 48 63 53 59 49 34 19 4 21 6 12 2 17 27 37 52 58 41
35 20 10 25 42 57 51 36 26 9 3 18 28 43 33 50 60 45 62 (56)
"