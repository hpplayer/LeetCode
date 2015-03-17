/**
 * My AC solution without help... It may seems lengthy, because I handle the obstacle case in two seperate places(first col and first row
 * we can use a cleaner version, please see sol2
 * Basic idea is almost same to 062, except we need handle obstacle case(check if it is 1, if yes ,set it to 0
 * @author hpPlayer
 * @date Mar 16, 2015 6:21:00 PM
 */
public class p063_sol1 {
		public static void main(String[] args){
			int[] a = {0};
			//int[] b = {1,1};
			//int[] c = {0,0};
			//int[][] d = {a, b ,c};
			int[][] d = {a};
			p063_sol1 test = new p063_sol1();
			System.out.println(test.uniquePathsWithObstacles(d));
		}
	   public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		   if(obstacleGrid.length == 0 && obstacleGrid[0].length == 0) return 0;
		   int m = obstacleGrid.length-1;
		   int n = obstacleGrid[0].length-1;
		   if(obstacleGrid[0][0] == 1 || obstacleGrid[m][n] == 1) return 0;
	        int[] row = new int[obstacleGrid[0].length];
	        row[0] = 1;
	       // System.out.println(row.length);
	        for(int i = 0; i < obstacleGrid.length; i++){
	        	if(obstacleGrid[i][0] == 0){//if clear, need check
	        		if(row[0] == 1){//if above also clear
	        			row[0] = 1;
	        		}else{
		        		row[0] = 0;
	        		}
	        	}else{
	        		row[0] = 0;
	        	}
	        	for(int j = 1; j < obstacleGrid[0].length; j++){
	        		if(i == 0){
	        			if(obstacleGrid[0][j] == 0){
	        				if(row[j-1] == 0){
	        					row[j] = 0;
	        				}else{
		    	        		row[j] = 1;
	        				}
	    	        	}else{
	    	        	
	    	        		row[j] = 0;
	    	        	}
	        		}else{
	        			if(obstacleGrid[i][j] == 1){
	        				row[j] = 0;
	        			}else{
		        			row[j] = row[j] + row[j-1];
	        			}
	        		}
	        	}
	        }
	        return row[n];
	   }
}
