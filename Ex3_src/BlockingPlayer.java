import java.util.Arrays;

public class BlockingPlayer extends RandomPlayer {
	
	public BlockingPlayer(String s, char m) {
		super(s, m);
		super.rg = new RandomGenerator();
	}
	
	@Override
	public void makeMove() {
		boolean breakLoop = false;
		for(int i = 0; i < 3; i++) {
			for(int j= 0; j < 3; j++) {
				char [][] temp = new char[3][3];
				for (int k = 0; k < super.getBoard().getTheBoard()[i].length; k++) {
					temp[k] = Arrays.copyOf(super.getBoard().getTheBoard()[i], super.getBoard().getTheBoard()[i].length);
				}
				temp[i][j] = super.getOpponent().getMark();
				
				if(testForBlocking(temp) == 1 && breakLoop == false) {
					super.getBoard().addMark(i, j, this.getMark());
					breakLoop = true;
					return;
				}
			}
			if(breakLoop) {
				break;
			}
		}
		
		char [][] temp = super.getBoard().getTheBoard();
		while(true && breakLoop == false) {
			int i = rg.discrete(0, 2);
			int j = rg.discrete(0, 2);
			if(temp[i][j] == SPACE_CHAR) {
				super.getBoard().addMark(i, j, this.getMark());
				break;
			}
		}
	}
	
	public int testForBlocking(char [][] b) {
		int row, col;
		int result = 0;

		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (b[row][col] != super.getOpponent().getMark())
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (b[row][col] != super.getOpponent().getMark())
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}

		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (b[row][row] != super.getOpponent().getMark())
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (b[row][3 - 1 - row] != super.getOpponent().getMark())
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}
}
