
public class RandomPlayer extends Player {
	protected RandomGenerator rg;
	
	public RandomPlayer(String s, char m) {
		super(s, m);
		rg = new RandomGenerator();
	}

	@Override
	public void makeMove() {
		char [][] temp = super.getBoard().getTheBoard();
		while(true) {
			int i = rg.discrete(0, 2);
			int j = rg.discrete(0, 2);
			if(temp[i][j] == SPACE_CHAR) {
				super.getBoard().addMark(i, j, this.getMark());
				break;
			}
		}
	}
	
}