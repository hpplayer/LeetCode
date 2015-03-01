/**
 * Bascially, to save time and space, we need start from the last row
 * remember the row size = row number, so we just use n space to store value(last row has i element, so we need at least i-length array
 * we uses an array to store the mini value for that index, which includes itself and the min value from children
 * for min value from children we simply check the min value in the array of index i and index i+1
 * Only one boundary case we need handle, when comparing the value of last element in last row(i.e. last element in the array), we just add
 * its value without compare next element, since it is the last index of array and does not have next element;
 * In last, we return first index of that array which would be our solution
 */

import java.util.ArrayList;
import java.util.List;


/*
 * [
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
 */
public class p120_sol2 {
	public static void main(String[] args) {
	//	List<Integer> inner1 = new ArrayList<Integer>();
		//List<List<Integer>> outer = new ArrayList<List<Integer>>();
		//outer.add(inner1);
		
		List<Integer> inner1 = new ArrayList<Integer>();
		inner1.add(-1);
		List<Integer> inner2 = new ArrayList<Integer>();
		inner2.add(-1);
		inner2.add(-3);
		List<Integer> inner3 = new ArrayList<Integer>();
		inner3.add(-8);
		inner3.add(-8);
		inner3.add(-1);
		List<List<Integer>> outer = new ArrayList<List<Integer>>();
		outer.add(inner1);
		outer.add(inner2);
		outer.add(inner3);
	
		System.out.println(minimumTotal(outer));

	}
	/*
	 * for all solutions, the below one(minimumTotal and minimumTotal2) are the best choice so far!
	 */
	public static int minimumTotal(List<List<Integer>> triangle) {
		if(triangle == null || triangle.isEmpty() ||triangle.get(0).size() ==0) return -1;
				
		int rows = triangle.size();
		int[] currentSum = new int[rows];
		
		for(int i = rows-1; i >= 0; i--){
			for(int j = 0; j <= i; j++){//i is the index of last element
				//triangle.size()-1 is the last row, j = that means check if we are in last row and we are checking the last element
				//if yes add 0, else add last one.
				currentSum[j] = triangle.get(i).get(j) + Math.min(currentSum[j], j==rows-1? 0:currentSum[j+1]);
			}
		}
		return currentSum[0];
	}
	
	
	public static int minimumTotal2(List<List<Integer>> triangle) {
		int rows = triangle.size();
		int lengths = triangle.get(triangle.size() - 1).size();//largest layer size
		int[] dp = new int[lengths];//make it lengths +1, so the last element in last row would do following loop without problem
		for (int i = rows - 1; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				//for (int j = 0; j < triangle.get(i).size(); j++) {
				//return min(dp[j],dp[j+1]) tells parents what is the min value of its children
				//for the last row, all dp[j] = orinial value
				if(j == rows -1){
					dp[j] = triangle.get(i).get(j);
				}else{
					dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
				}
	
			}
		}
		return dp[0];
	}
	
	public static int minimumTotal3(List<List<Integer>> outer) {
	    int res = Integer.MAX_VALUE;
	    int[] d = new int[outer.get(outer.size() - 1).size()];
	    d[0] = outer.get(0).get(0);
	    for (int i = 1; i < outer.size(); i++) {
	        int rowSize = outer.get(i).size();
	        int[] tmp = new int[rowSize];
	        for (int j = 0; j < rowSize; j++)
	            tmp[j] = d[j];
	        for (int j = 0; j < outer.get(i).size(); j++) {
	            int fromAbove = j < rowSize - 1 ? d[j] : Integer.MAX_VALUE;
	            int fromUpperLeft = j > 0 ? tmp[j - 1] : Integer.MAX_VALUE;
	            d[j] = Math.min(fromAbove, fromUpperLeft) + outer.get(i).get(j);
	        }
	    }
	    for (int i = 0; i < d.length; i++) {
	        res = Math.min(res, d[i]);
	    }
	    return res;
	}
}
