

/**
 * This object contains most of the logic and rules necessary for the board game
 * @author M. Moussavi - Edited by Albin Radaj
 * @version 1.0
 * @since February 7, 2020
 */
public class Board implements Constants {
	/**
	 * char array containing the data all moves within the board
	 */
	private char theBoard[][];
	/**
	 * counts the number of plays within the board game
	 */
	private int markCount;

	/**
	 * Sets all board elements to "space" (empty)
	 */
	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}

	/**
	 * getter function of a mark on the board
	 * @param row is the row of the element/mark
	 * @param col is the column of the element/mark
	 * @return the char of the mark
	 */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}
	
	/**
	 * Determines if the board is full or not
	 * @return true if the board is full. Otherwise, false
	 */
	public boolean isFull() {
		return markCount == 9;
	}
	
	/**
	 * Determines if the xPlayer won the game
	 * @return true if the xPlayer has won
	 */
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}

	/**
	 * Determines if the oPlayer won the game
	 * @return true if the oPlayer has won
	 */
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1)
			return true;
		else
			return false;
	}
	
	/**
	 * Displays the board game, including all marks
	 */
	public void display() {
		displayColumnHeaders();
		addHyphens();
		for (int row = 0; row < 3; row++) {
			addSpaces();
			System.out.print("    row " + row + ' ');
			for (int col = 0; col < 3; col++)
				System.out.print("|  " + getMark(row, col) + "  ");
			System.out.println("|");
			addSpaces();
			addHyphens();
		}
	}

	/**
	 * Adds a new mark to the board
	 * @param row is the row of the new mark
	 * @param col is the column of the new mark
	 * @param mark is the mark ('X' or 'O') of the player
	 */
	public void addMark(int row, int col, char mark) {
		theBoard[row][col] = mark;
		markCount++;
	}
	
	/**
	 * Clears the board game
	 */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}

	/**
	 * Determines if there is one of the players is a winner
	 * @param mark is the mark ('X' or 'O') of the player
	 * @return 1 if the player wins, 0 otherwise
	 */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;

		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}

		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}
	
	/**
	 * Displays the columns directly above the board
	 */
	void displayColumnHeaders() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|col " + j);
		System.out.println();
	}

	/**
	 * Adds hyphens to design the look of the board
	 */
	void addHyphens() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("+-----");
		System.out.println("+");
	}

	/**
	 * Adds spaces to design the look of the board
	 */
	void addSpaces() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|     ");
		System.out.println("|");
	}
	
	public char[][] getTheBoard() {
		return this.theBoard;
	}
}
