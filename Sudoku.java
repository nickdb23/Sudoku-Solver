public class Sudoku {
	
	private static final int GRID_SIZE = 9;
	
	public static void main(String[] args) {
		
		int[][] board = {
			{1,0,0,0,0,5,2,7,0},
			{0,5,0,0,0,0,8,0,0},
			{0,4,0,9,2,0,0,5,3},
			{0,0,0,6,0,2,9,8,0},
			{0,2,0,0,9,1,0,0,0},
			{7,9,0,0,0,0,6,0,2},
			{5,0,4,1,0,0,0,0,0},
			{0,0,0,2,6,7,5,4,1},
			{0,0,0,5,0,0,0,0,0}
		};
		printBoard(board);
		
		if (solveBoard(board)) {
			System.out.println("Solved successfully!");
		}
		else {
			System.out.println("Unsolvable board :(");
		}
    
		printBoard(board);
	}
	
	private static void printBoard(int[][] board) {
		for (int i = 0; i < GRID_SIZE; i++) {
			System.out.println("---------");
			for (int j = 0; j < GRID_SIZE; j++) {
				System.out.print("|");
				System.out.print(board[i][j]);
			} System.out.println();
		}
	}
	
	private static boolean isNumberInRow(int[][] board, int number, int row) {
		
		boolean found = false;
		
		for (int i = 0; i < GRID_SIZE; i++) {
			if(board[row][i] == number) {
				found = true;
			}
		} return found;
	}
	
	private static boolean isNumberInColumn(int[][] board, int number, int column) {
		
		boolean found = false;
		
		for (int i = 0; i < GRID_SIZE; i++) {
			if(board[i][column] == number) {
				found = true;
			}
		} return found;
	}
	
	private static boolean isNumberInCell(int[][] board, int number,int row, int column) {
		
		int localRow = row - (row%3);
		int localColumn = column - (column%3);
		boolean found = false;
		
		for (int i = localRow; i < localRow + 3; i++) {
			for (int j = localColumn; j < localColumn + 3; j++) {
				if (board[i][j] == number) {
					found = true;
				}
			}
		} return found;
	}
	
	private static boolean isValid(int[][] board, int number, int row, int column) {
		return !isNumberInRow(board, number, row) && !isNumberInColumn(board, number, column) && !isNumberInCell(board, number, row, column);
	}
	
	private static boolean solveBoard(int[][] board) {
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int column = 0; column < GRID_SIZE; column++) {
				if (board[row][column] == 0) {
					for (int possibleNum = 1; possibleNum <= GRID_SIZE; possibleNum++) {
						if (isValid(board, possibleNum, row, column)) {
							board[row][column] = possibleNum;
							
							if(solveBoard(board)) {
								return true;
							} else {
								board[row][column] = 0;
							}
						}
					} return false;
				}
			}
		} return true;
	} 
}