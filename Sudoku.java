/*Vicki Young
2022.02.10
CS 245-03
*/

public class Sudoku {

	private int size;
	private int[][] board;
	/**
	 * Default constructor -- construct an empty Sudoku puzzle
	 */
	public Sudoku() {
		size = 9;
		board = new int[size][size];
	}
	/**
	 * Input an initial starting board for Sudoku. Consider using one online, such as
	 * https://www.sudokuoftheday.com/
	 * @param givenBoard
	 * @throws IllegalArgumentException if given double array is not a valid initial 9x9 Sudoku board
	 */
	public void inputBoard(char[][] givenBoard) throws IllegalArgumentException {
		//checks number of rows of givenBoard and checks length of each of givenBoard's rows
		for (int row = 0; row < givenBoard.length; row++) {
			if (givenBoard.length != givenBoard[row].length || givenBoard[row].length != 9) {
				throw new IllegalArgumentException("Given Sudoku board size is not 9x9");
			}
		}
		//if all checks pass, givenBoard's length becomes the sudoku board size
		size = givenBoard.length;

		//fills in all empty board placements with 0s
		//also checks that all givenBoard entries fall within range of 0 to 9; throws IllegalArgumentException if an entry does not
		char c;
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				c = givenBoard[row][column];
				//converts givenBoard's char value of '.' or '0' to 0, signifying empty placement in board
				if (c == '.' || Character.getNumericValue(c) == 0) {
					board[row][column] = 0;
				//checks that givenBoard's entry fall within range of 0-9; throws exception if entry does not	
				} else if (Character.getNumericValue(c) < 1 || Character.getNumericValue(c) > 9) {
					throw new IllegalArgumentException("Given Sudoku board contains values not within 0 to 9");
				//converts givenBoard's char value to int
				} else {
					board[row][column] = Character.getNumericValue(c);
				}
			}
		}
	}

	/**
	 * Solves the Sudoku puzzle from the starting position, if possible.
	 * @return true if the puzzle is solved; false if it cannot be solved.
	 */
	public boolean solveSudoku() {
		return solveSudoku(0);
	}

	/**
	 * Function places numbers in valid place along column 
	 * Recursively calls itself to place numbers in next row
	 * @param row where to place next number
	 * @return true if successful; false if unsuccessful
	 */
	private boolean solveSudoku(int row) {
		//base case
		if (row == size) {
			return true;
		}

		//loops through column of given row
		for (int column = 0; column < size; column++) {
			//checks if board placement is empty
			if (board[row][column] == 0) {
				//loops through numbers 1-9 inclusive
				for (int number = 1; number <= 9; number++) {
					//checks if given number works in given empty placement
					if (checkPlacement(row, column, number) == true) {
						board[row][column] = number;
						//backtracking algorithm; checks validity of the next empty placement
						if (solveSudoku(row) == true) {
							return true;
						} //else
						board[row][column] = 0;
					}
				}
				//if no number works in the given empty placement, returns false
				return false;
			}
		}
		//the current row has been completely filled in; function calls itself to check the next row
		return solveSudoku(row+1);
	}

	/**
	 * Function checks if current placement for the current number is valid
	 * Checks along placement's row, column, and sub-grid 
	 * @param row current row of current placement
	 * @param column currow column of current placement
	 * @param number current number
	 * @return true if successful; false if unsuccessful
	 */
	private boolean checkPlacement(int row, int column, int number) {
		//check row
		for (int i = 0; i < size; i++) {
			if (i != column && board[row][i] == number) {
				return false;
			}
		}
		//check column
		for (int i = 0; i < size; i++) {
			if (i != row && board[i][column] == number) {
				return false;
			}
		}
		//check sub-grid
		//find the sub-grid's top left placement coordinates
		int subGridRow = (row/3) * 3;
		int subGridColumn = (column/3) * 3;
		//loop through sub-grid
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (!(subGridRow + i == row && subGridColumn + j == column) && board[subGridRow + i][subGridColumn + j] == number) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Prints the Sudoku board to the console.
	 */
	public void printBoard() {
		int subGridColumn = 0;
		int subGridRow = 0;
		System.out.println("  ---------------------");
		for (int row = 0; row < size; row++) {
			System.out.print("|");
			for (int column = 0; column < size; column++) {
				System.out.print(" " + board[row][column]);
				subGridColumn++;
				if (subGridColumn == 3) {
					System.out.print(" |");
					subGridColumn = 0;
				}
			}
			System.out.println();
			subGridRow++;
			if (subGridRow == 3) {
				System.out.println("  ---------------------");
				subGridRow = 0;
			}
		}
	}
	
	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku();
		char[][] board = {{'5','3','.','.','7','.','.','.','.'},
        			 	  {'6','.','.','1','9','5','.','.','.'},
       				 	  {'.','9','8','.','.','.','.','6','.'},
        				  {'8','.','.','.','6','.','.','.','3'},
        				  {'4','.','.','8','.','3','.','.','1'},
		                  {'7','.','.','.','2','.','.','.','6'},
		                  {'.','6','.','.','.','.','2','8','.'},
		                  {'.','.','.','4','1','9','.','.','5'},
		                  {'.','.','.','.','8','.','.','7','9'}};
		sudoku.inputBoard(board);
		System.out.println("Initial board:");
		sudoku.printBoard();
		System.out.println("\nSolution:");
		if (sudoku.solveSudoku()) {
			sudoku.printBoard();
		} else {
			System.out.println("No solution found; unable to fill in board.");
		}
	}
	
}
