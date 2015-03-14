/**
 * Similar problem to p051, except the requirement becomes easy and we just need to return the count, instead of printing the solution
 * My original solution is still add a list, if the DFS can reach the end, then add one item to the list 
 * Finally return the size of the list, but it is too complicated.
 * A simple approach as illustrated here, is use a class variable count, if we reach the end, count ++, finally return the count
 * 
 * Remark:
 * For the invalid(), we need input both the column# and row#, since we have not insert the row value to this column index, we dont 
 * know what row value we are currently looking at. When I was first writing the code, I missed this point, and just read current 
 * row from cols[], which should be 0, so it is incorrect!
 * 
 * @author hpPlayer
 * @date Mar 13, 2015 5:32:24 PM
 */
public class p052_sol1 {
	public static void main(String[] args){
		System.out.println(totalNQueens(4));
	
	}
	static int count = 0;
	public static int totalNQueens(int n) {
        int columns[] = new int[n];
        DFS(columns, 0);
        return count;
    }
    
    public static void DFS(int[] columns, int col){
        if(col == columns.length){
        	count ++;
            return;
        }
        
        for(int i = 0; i < columns.length; i++){//actually we are checking rows
            if(isValid(columns, col, i)){
            	//System.out.println("im here");
                columns[col] = i;
                DFS(columns, col+1);
            }
        }
        
    }
    
    public static boolean isValid(int[] columns, int col, int row){
        for(int i = 0; i < col; i++){//check all cols before col
            if(columns[i] == row)//check each row
            return false;
            
            int diffcol = col - i;
            int diffrow = Math.abs(columns[i] - row);
            if(diffcol == diffrow)
            return false;
            
        }
        
        return true;
    }
}
