import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * An optimized DFS solution
 * Since we only needs to record the position of row that we place Q in each column, so we can just use a single column array to record
 * the row number, for example col[1] = 5 means in column 1 we place Q in 5
 * Then things become simple. In is valid() function, we just need to visit the column to check previous columns
 * For example,
 * to check if same Q in the same row, we have col[i] == y
 * to check if same Q in , we have abs(row2-row1) == abs(col2 - col1) (this is so smart!!!)
 * we just need one iteration to check the validity
 * 
 * print() also becomes simple, we insert Q into position (col[i], i)
 * 
 * This method also let us to make DFS simpler, we dont need to reset the current position after DFS is done, since we are recording 
 * the position instead of place Q in real board, so we can manipulate them more easily
 * 
 * @author hpPlayer
 * @date Mar 13, 2015 4:33:20 PM
 */

public class p051_sol2 {
	public static void main(String[] args){
		List<String[]> result = solveNQueens(4);
		System.out.println(result.size());
		for(String[] str:  result){
			System.out.println(Arrays.toString(str));
			System.out.println();
		}
	}
	 public static List<String[]> solveNQueens(int n) {
	        List<String[]> result = new ArrayList<String[]>();
	        if(n <= 0){
	            return result;
	        }
	        int[] col = new int[n];

	        DFS(result, col, 0);
	        return result;
	    }
	    
	    public static void DFS(List<String[]> result, int[] col, int x){
	     	//System.out.println("I reach here x: " + x + " y: " + y);
	        if(x == col.length){
	            result.add(buildString(col));
	            return;
	        }
	       // for(int i = x; i < board[0].length; i++){//look col by col, j: row, i: col
	            for(int j = 0; j < col.length; j++){
	                if(isValid(col, x, j)){
	                    col[x] = j;//place row in corresponding col
	                    DFS(result, col, x+1);
	                }
	            }
	        //}
	    }
	    
	    public static String[] buildString(int[] col){
	        String[] result = new String[col.length];
	        for(int i = 0; i < col.length; i++){//check row
	             StringBuilder sb = new StringBuilder();
	             for(int j = 0; j < col.length; j++){//check col
	            	 if(i == col[j]) sb.append("Q");//if col j record i, then this (i, j) is Q
	            	 else sb.append(".");
	             }
	            result[i] = sb.toString();
	        }
	        return result;
	    }
	    public static boolean isValid(int[] col, int x, int y){
	    	for(int i = 0; i < x; i++){//i is prevCol
	    		int prevRow = col[i];
	    		if(prevRow == y) return false;
	    		
	    		int colDistance = x - i;
	    		int rowDistance = Math.abs(y - prevRow);
	    		if(colDistance == rowDistance){
	    			return false;
	    		}
	    	}
	    	
	    	return true;
	    	
	    }
}
