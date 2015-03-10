import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/**
 * BFS solution, almost same to DFS, except we are using a queue. But this change will not pass tests as shown "execution time limited"
 * error. Why? since when we are using BFS we may add duplicate element in the queue which has not been visited yet(i.e. still in the que
 * but not marked as "D"), so if we dont do anything then it will become node->marked "D" do BFS, que continue, duplicate node, which is
 * actually marked as "D" but we still treat it as "O" and do BFS on it, so it costs a lot of time.
 * 
 * For the DFS, it still has the similar problem, but since it goes as deep as possible, duplicate problem will not be so serious
 * BFS, we always play around in same level or close level, so it has more server dupliate effects
 * To solve this, simply add an if-else case in each iteration check if we have visited that node before, if yes, jumpt the BFS on this node
 * if not, we do bfs on it.
 * 
 * @author hpPlayer
 * @date Mar 10, 2015 3:34:54 PM
 */


public class p130_sol3 {
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
    		  BFS(board, 0, i);
    	   }
    	   if(board[row-1][i] == 'O'){
    		   BFS(board,row-1,i);
    	   }

       }
       
       for(int i = 1; i < row-1; i++){//i:row, except the first and last
    	   /*
    	    * check row by row in the col and last col(not the fist and last position that has been visited before
    	    */
    	   if(board[i][0] == 'O'){
        	  BFS(board,i,0);//first row
    	   }
    	   if(board[i][col-1] == 'O'){
        	   BFS(board,i,col-1);//last row
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
    
    // queue, priority queue !
    public static void BFS(char[][] board, int y, int x){
    	int[] offsetX = {-1,0,1,0};
    	int[] offsetY = {0, -1, 0, 1};
    	Queue<pair> que = new LinkedList<pair>();
    	que.add(new pair(x,y));
    	while(!que.isEmpty()){
    		pair p = que.poll();
    	  	if (board[p.y][p.x] == 'D'){
	           continue;
    	  	}
    		//System.out.println(p.x);
     		//System.out.println(p.y);
    	 	board[p.y][p.x] = 'D';	 	
    	 	for(int i = 0; i < offsetX.length; i++){
    	 		if(p.x + offsetX[i] >= 0 &&p.x+offsetX[i] < board[0].length&& p.y + offsetY[i] >= 0 &&p.y+offsetY[i] < board.length
    	 				&& board[p.y+offsetY[i]][p.x+offsetX[i]] == 'O'){
    	 			que.add(new pair(p.x+offsetX[i], p.y+offsetY[i]));
    	 		}
    	 	}
    	}
    
    }
}
