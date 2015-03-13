import java.util.Stack;
/**
 * This problem can be solved by two approaches, DFS and BFS, 
 * this solution if for DFS
 * 
 * DFS can be implemented in two ways:
 * 1)recursive call, which takes a lot of memory and may cause stackoverflow
 * 2)use stack to mimic recursive call, but release certain space during the execution which will save space
 * 
 * So both 1) and 2) should be correct theoretically
 * but 2) can be AC, and 1) is not
 * 
 * Basic idea:
 * we search boundary of the board, if found "O" then do DFS to this "O", all "O" that connected to this "O" will be kept 
 * And we marked them with "D"
 * after we done this to all boundary and "O" that is still remaining are those not connected by boundary "O", which should is 
 * surrounded by "X", we just paint those "O" with X ,and paint "D" with "O"
 * 
 * 
 * Remark:
 * 1) when we search the first row and last row, we just do normal but after done this search and begin search the fist col and 
 * last col, we can just skip the intersection part(0 and row-1) to save time
 * 2) Be familiar with stack implementation of DFS, though recursive DFS is good and easy to write but using stack can help save space! 
 * 3) the use of "D" is very clever!
 * 4)DFS2() is the recurisve DFS, DFS() is the stack version of DFS
 * 5) the use of offsetX and offsetY is also clever, which makes code more readable and concise
 * 6) dont forget the boundary case "null" and "board.length == 0"!!!! 
 * @author hpPlayer
 * @date Mar 10, 2015 2:52:59 PM
 */

public class p130_sol2 {

	public static void main(String[] args) {
		char[][] board = { { 'X', 'X', 'X','O' }, { 'X', 'O', 'X','O'  },
				{ 'X', 'X', 'X','O'  } };
		solve(board);
		for (char[] i : board) {
			for (char c : i) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	private static class pair{
		public int x;
		public int y;
		public pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
    public static void solve(char[][] board) {
        if(board == null || board.length <=0 ) return;
       int row = board.length;
       int col = board[0].length;
       
       for(int i = 0; i < col; i++){//i:col
    	   /*
    	    * check col by col in the fist and last row
    	    */
    	   if(board[0][i] == 'O'){
    		   DFS(board, 0, i);
    	   }
    	   if(board[row-1][i] == 'O'){
    		   DFS(board,row-1,i);
    	   }

       }
       
       for(int i = 1; i < row-1; i++){//i:row, except the first and last
    	   /*
    	    * check row by row in the col and last col(not the fist and last position that has been visited before
    	    */
    	   if(board[i][0] == 'O'){
        	   DFS(board,i,0);//first row
    	   }
    	   if(board[i][col-1] == 'O'){
        	   DFS(board,i,col-1);//last row
    	   }
    	//   DFS(board,i,0);//first col that not visited before
    	   //DFS(board,i, col-1);//last col that not visited before
       }

       for(int i = 0; i < row; i++){
    	   for(int j= 0; j < col; j++){
    		   if(board[i][j] == 'D') board[i][j] = 'O';
    		   else if(board[i][j] == 'O') board[i][j] = 'X';
    	   }
       }
    }
    
    
    public static void DFS(char[][] board, int y, int x){
    	int[] offsetX = {-1,0,1,0};
    	int[] offsetY = {0, -1, 0, 1};
    	Stack<pair> stack = new Stack<pair>();
    	stack.push(new pair(x,y));
    	while(!stack.isEmpty()){
    		pair p = stack.pop();
    		//System.out.println(p.x);
     		//System.out.println(p.y);
    	 	board[p.y][p.x] = 'D';	 	
    	 	for(int i = 0; i < offsetX.length; i++){
    	 		if(p.x + offsetX[i] >= 0 &&p.x+offsetX[i] < board[0].length&& p.y + offsetY[i] >= 0 &&p.y+offsetY[i] < board.length
    	 				&& board[p.y+offsetY[i]][p.x+offsetX[i]] == 'O'){
    	 			stack.push(new pair(p.x+offsetX[i], p.y+offsetY[i]));
    	 		}
    	 	}
    	}
    
    }
    
    public static void DFS2(char[][] board, int x, int y){
    	if(x<0 || x >=board.length ||y< 0 || y >= board[0].length || board[x][y] != 'O') return;
    	//now it is 'O'
    	board[x][y] = 'D';
    	DFS(board,x-1,y);
    	DFS(board,x+1,y);
    	DFS(board,x,y-1);
    	DFS(board,x,y+1);
    }
}
