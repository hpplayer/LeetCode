import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
/**
 * In my first try, I didn't realize the arraylist's contains() function is time consuming, which may costs O(n) time
 * The main issue in this problem is how to handle duplicate..
 * Basic idea:
 * Before everything start, the first thing we need to do is sorting array, sorted array can help us avoid duplicate and increase 
 * search speed
 * we have two loops,
 * The main loop is visiting each number in the ary, if we found ary[i] == ary[i-1], then we can skip current value due to duplicate
 * We have two pointers in inner loop, to save time of searching, one pointer(p) points to the i+1, one(q) points to the tail of ary 
 * if num[p] + num[q] = num[i], then we found a match, and add them to the result 
 * The next important thing is skipping duplicate.
 * Because we don't allow duplicates in the result, so we would not use same num[p] and num[q] value again if num[i] is same 
 * Thus, we firstly manually help change the p and q value, then skip all p and q values that are same as the one we just matched
 * 
 * Remark:
 * 1) Remember, use arraylist.contains() is time-consuming
 * 2) We need mannually change p and q index if match found, because we will never use same p and q again otherwise it will cause 
 * duplicate
 * @author hpPlayer
 * @date Mar 23, 2015 12:38:42 AM
 */

public class p015_sol1 {
	public static void main(String[] args){
		//int[] ary = {-1, 0, 1, 2, -1, -4};
		//int[] ary = {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
		//int[] ary = {0,0,0};
		int[] ary = {1, -1, -1, 0};
		for(List<Integer> temp: new p015_sol1().threeSum(ary)){
			System.out.println(temp);
		}
	}

    public List<List<Integer>> threeSum(int[] num) {
    	Arrays.sort(num);
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	for(int i = 0; i < num.length; i++){
    		if(i >=1 && num[i] == num[i-1]) continue;//skip i, if curr value is same with prev value
    		int currSum = num[i];
    		int start = i+1; 
    		int end = num.length -1;
    		while(start < end){
    			if(num[start] + num[end] == -currSum){//equal
    				List<Integer> temp = new ArrayList<Integer>();
    				temp.add(num[i]);
    				temp.add(num[start]);
    				temp.add(num[end]);
    				
    				result.add(temp);
    				//current start and end has been used, we will never use them again due to no-duplicate property
    				start ++;
    				end --;
    				//now we need skip duplicates 
    				while(start < end && num[end] == num[end+1]){//end has been reduced 1, see above
    					end --;
    				}
    				while(start < end && num[start] == num[start-1]){//end has been reduced 1, see above
    					start ++;
    				}
    			}else if (num[start] + num[end] < -currSum){//too small
    				start ++;
    			}else{//too big
    				end--;
    			}
    		}
    	}
       return result;
    }
}
