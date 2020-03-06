

/**
 * This class contains the info of each player - name, opponent, and mark
 * @author Albin Radaj
 * @version 1.0
 * @since February 7, 2020
 */
public abstract class Player implements Constants {
	/**
	 * Name of the player
	 */
	protected String name;
	/**
	 * Object of the board that is currently in play
	 */
	protected Board board;
	/**
	 * Player object of the opponent player
	 */
	protected Player opponent;
	/**
	 * char value of the mark of the player
	 */
	protected char mark;
	
	/**
	 * Constructor of Player that assigns the name and mark
	 * @param s is the name for the Player object
	 * @param m is the mark for the Player object
	 */
	public Player(String s, char m) {
		this.setName(s);
		this.setMark(m);
		board = new Board();
		this.setOpponent(null);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public Player getOpponent() {
		return opponent;
	}
	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}
	public char getMark() {
		return mark;
	}
	public void setMark(char mark) {
		this.mark = mark;
	}
	
	/**
	 * Begins a turn for the game from a certain player
	 */
	public void play() {
		if(!this.getBoard().xWins() && !this.getBoard().oWins() && !this.getBoard().isFull()) {
			this.makeMove();
			this.getBoard().display();
			this.getOpponent().play();
		}
		else {
			if(this.getMark() == LETTER_O) {
				if(this.getBoard().oWins())
					System.out.println("O wins! The winner is: " + this.getName());
				else if(this.getBoard().xWins())
					System.out.println("X wins! The winner is: " + this.getOpponent().getName());
				else
					System.out.println("Game tie! No winners this time.");
			}
			else	{
				if(this.board.oWins())
					System.out.println("O wins! The winner is: " + this.getOpponent().getName());
				else if(this.board.xWins())
					System.out.println("X wins! The winner is: " + this.getName());
				else
					System.out.println("Game tie! No winners this time.");
			}
		}
	}
	/**
	 * Allows the user or computer to make a move on the board
	 */
	public abstract void makeMove();
}
