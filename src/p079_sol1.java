/**
 * My AC solution..
 * DFS...
 * With help...
 * 
 * Very similar to previous 2D problem(p130)
 * Difference:
 * 1) we need change the char in the board, when we do DFS to it, so its children will not visit this node again
 * 2) !!!!!Remember to reset the char to the original char when DFS fails!!!!!!
 * 3) pass substring to next iteration, so when substring.size() == 0, we know we have found such string!
 
 * @author hpPlayer
 * @date Mar 12, 2015 11:33:42 PM
 */
public class p079_sol1 {
	
	public static void main(String[] args) {
		String word = "AAB";
		char[][] board = { { 'C', 'A', 'A'}, {'A', 'A', 'A'}, {'B', 'C', 'D'} };
		System.out.println(exist(board, word));
	}

	public static boolean exist(char[][] board, String word) {
		if (word == null || board.length == 0 || word.length() == 0)
			return true;
		boolean result = false;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == word.charAt(0)) {
					char temp = board[i][j];
					board[i][j] = '0';
					result = DFS(word.substring(1), board, i, j);
					if (result == true) {
						return true;
					}
					board[i][j] = temp;
				}
			}
		}
		return result;
	}

	public static boolean DFS(String word, char[][] board, int y, int x) {
		int[] Yoffset = { 1,0, 0, -1 };
		int[] Xoffset = { 0, 1,-1, 0 };
		boolean result = false;
		if (word.length() == 0) {
			return true;
		}
		for (int i = 0; i < Yoffset.length; i++) {
			int yPlus = Yoffset[i];
			int xPlus = Xoffset[i];

			if (y + yPlus >= 0 && y + yPlus < board.length && x + xPlus >= 0 && x + xPlus < board[0].length
					&& board[y + yPlus][x + xPlus] == word.charAt(0)) {
				char temp = board[y + yPlus][x + xPlus];
				board[y + yPlus][x + xPlus] = '0';
				result = DFS(word.substring(1), board, y + yPlus, x + xPlus);
				if (result == true) {
					return true;
				}
				board[y + yPlus][x + xPlus] = temp;
			}
		}

		return result;
	}
}
