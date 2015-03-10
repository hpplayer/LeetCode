/**
 * my original approach that tries to do DFS in 'O' that covered by 'X'..but does not work out...
 * too sleepy when doing this problem 
 * 
 * @author hpPlayer
 * @date Mar 10, 2015 3:11:25 PM
 */
public class p130_sol1 {
	public static void main(String[] args) {
		char[][] board = { { 'X', 'X', 'X' }, { 'X', 'O', 'X' },
				{ 'X', 'X', 'X' } };
		solve(board);
		for (char[] i : board) {
			for (char c : i) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	public static void solve(char[][] board) {
		if (board.length == 0) {
			return;
		}
		boolean isCovered[][] = new boolean[board.length][board[0].length];
		for (int i = 1; i < board.length - 1; i++) {

			for (int j = 1; j < board[0].length - 1; j++) {

				if (board[i][j] == 'O') {
					if (board[i - 1][j] == 'O') {
						continue;
					}
					if (board[i][j - 1] == 'O') {
						continue;
					}
					isCovered[i][j] = true; 
					DFS(j, i, board, isCovered);
				}
			}
		}
	
		for (int i = 1; i < board.length - 1; i++) {
			for (int j = 1; j < board[0].length - 1; j++) {
				if (isCovered[i][j] == true && board[i][j] == 'O') {
					board[i][j] = 'X';
				}

			}
		}
	}

	// only search bot and right
	public static void DFS(int x, int y, char[][] board, boolean[][] isCovered) {// x
														// start
																			// with
																			// 1,
																			// y
																			// start
																			// with
																			// 1
		// if(reachB(x,y,board)) return;

		if (x + 1 == board[0].length - 1 && board[y][x + 1] == 'O') {
			isCovered[y][x] = false;
			return;
			// return false;
		}
		if (y + 1 == board.length && board[y][x + 1] == 'O') {
			// if(board[y+1][x] == 'X')
			isCovered[y][x] = false;
			return;
			// return false;
		}
		System.out.println("imhere");
		if (board[y][x + 1] == 'O') {
		
			isCovered[y][x + 1] = true;
			isCovered[y][x] = true;
			DFS(x + 1, y, board, isCovered);
			if (isCovered[y][x + 1] == false) {
				if (board[y + 1][x] == 'O') {
					isCovered[y + 1][x] = true;
					DFS(x, y + 1, board, isCovered);
					if (isCovered[y + 1][x] == false) {
						isCovered[y][x] = false;
					}
				}
			}
		}

	}
}
