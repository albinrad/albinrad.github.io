import java.util.Scanner;

public class HumanPlayer extends Player {
	
	private Scanner scan;
	
	public HumanPlayer(String s, char m) {
		super(s, m);
		scan = new Scanner(System.in);
	}

	@Override
	public void makeMove() {
		scan = new Scanner(System.in);
		System.out.println(this.getName() + ", what row should your next " + this.getMark() + " be placed in?");
		int row = scan.nextInt();
		System.out.println(this.getName() + ", what column should your next " + this.getMark() + " be placed in?");
		int col = scan.nextInt();
		scan.nextLine();
		super.getBoard().addMark(row, col, this.getMark());
	}
}
