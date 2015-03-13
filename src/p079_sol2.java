
/**
 * An interesting way that use bit mask
 * one ASCII char is 8 bit, so we use mask 256, which is 2 ^8, 100000000
 * 
 * the use of ^= 256, make us do not use extra variable to store the old value
 * @author hpPlayer
 * @date Mar 12, 2015 11:48:30 PM
 */
public class p079_sol2 {
	
	public static void main(String[] args) {
		String word = "AAB";
		char[][] board = { { 'C', 'A', 'A'}, {'A', 'A', 'A'}, {'B', 'C', 'D'} };
		System.out.println(exist(board, word));
	}
	public static boolean exist(char[][] board, String word) {
	    char[] w = word.toCharArray();
	    for (int y=0; y<board.length; y++) {
	        for (int x=0; x<board[y].length; x++) {
	            if (exist(board, y, x, w, 0)) return true;
	        }
	    }
	    return false;
	}

	private static boolean exist(char[][] board, int y, int x, char[] word, int i) {
	    if (i == word.length) return true;
	    if (y<0 || x<0 || y == board.length || x == board[y].length) return false;
	    if (board[y][x] != word[i]) return false;
	    board[y][x] ^= 256;
	    boolean exist = exist(board, y, x+1, word, i+1)
	        || exist(board, y, x-1, word, i+1)
	        || exist(board, y+1, x, word, i+1)
	        || exist(board, y-1, x, word, i+1);
	    board[y][x] ^= 256;
	    return exist;
	}
}
