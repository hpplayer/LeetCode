/**
 * Stepwise technique, step left or down based on current value vs target value
 * Firstly I thought this would not work due to the consideration of diagonal value.
 * It is true diagonal value maybe larger or smaller than previous value, but left and down value 
 * strictly follow problem's description, i.e. left value < right value and down value > up value
 * 
 * To begin with our search, we pick the point that is the max in one direction and min in the other direction, so we can 
 * make the difference and move the cell accordingly
 * So we can either pick right top cell(searchMatrix()) or bottom left cell(searchMatrix2()).
 * @author hpPlayer
 * @date Aug 10, 2015 10:04:09 PM
 */
public class p240_sol2 {
	//start from top right
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0){
            if (matrix[row][col] == target){
                return true;
            }else if (matrix[row][col] < target){
                row ++;
            }else{
                col --;
            }
        }
        return false;
    }
    
    //start from bottom left
    public boolean searchMatrix2(int[][] matrix, int target) {
        int row = matrix.length-1;
        int col = 0;
        while (row >= 0 && col < matrix[0].length){
            if (matrix[row][col] == target){
                return true;
            }else if (matrix[row][col] < target){
                col ++;
            }else{
                row --;
            }
        }
        return false;
    }
}
