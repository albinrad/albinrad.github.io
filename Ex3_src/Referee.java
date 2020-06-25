

/**
 * This class will begin the board game, and display the board appropriately
 * @author Albin Radaj
 * @version 1.0
 * @since February 7, 2020
 */
public class Referee {
	/**
	 * The player with the x mark
	 */
	private Player xPlayer;
	/**
	 * The player with the o mark
	 */
	private Player oPlayer;
	/**
	 * The board object that is currently in play
	 */
	private Board board;
	
	/**
	 * Default constructor for the Referee class
	 */
	public Referee() {
		this.setxPlayer(null);
		this.setoPlayer(null);
		this.setBoard(null);
	}
	
	public Player getxPlayer() {
		return xPlayer;
	}
	public void setxPlayer(Player xPlayer) {
		this.xPlayer = xPlayer;
	}
	public Player getoPlayer() {
		return oPlayer;
	}
	public void setoPlayer(Player oPlayer) {
		this.oPlayer = oPlayer;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * Sets the opponents, displays the board then begins the game
	 */
	public void runTheGame() {
		this.getxPlayer().setOpponent(this.getoPlayer());
		this.getoPlayer().setOpponent(this.getxPlayer());
		this.getBoard().display();
		this.getxPlayer().play();
	}
}
