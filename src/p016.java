import java.util.Arrays;
/**
 * My AC approach without help, and I am confident in this solution
 * O(n^2) approach, similar to p15, using two pointers with a little modification.
 * No need to check duplicate, and we need check stored result and stored difference, and compare with current 
 * result and difference, if it is smaller, updating those parameters.
 * 
 *  Remark:
 *  As before, sorting will help us improve efficiency, since sorting will guide us move pointers smartly.
 *  At first galance, I thought there is no need to use sort, but I found then the movements of pointers will be in mass, 
 *  So remember using sort in such math related problem, which can help us move pointers smartly
 * 
 * @author hpPlayer
 * @date Mar 23, 2015 11:25:38 AM
 */

public class p016 {
	public static void main(String[] args){
		p016 test = new p016();
		int[] ary = {1,1,1,0};
		//int ary[] = {-1,2,1,-4};
		System.out.println(test.threeSumClosest(ary, 100));
	}
	
    public int threeSumClosest(int[] num, int target) {
    	Arrays.sort(num);
        int minDiff = Integer.MAX_VALUE;
        int result = Integer.MAX_VALUE;
        for(int i = 0; i < num.length-2; i ++){
            int start = i+1;
            int end = num.length -1;
            while(start < end){//we cannot let start and end points to same index, otherwise same value will be used twice
                int currSum = num[start] + num[end] + num[i];
               // System.out.println(currSum);
                if(Math.abs(currSum - target) < minDiff){
                    minDiff = Math.abs(currSum - target);
                    result = num[start] + num[end] + num[i];
                }
                
                if(target == currSum){
                	return currSum;
                }else if (target > currSum){//too small
                	start ++;
                }else{
                	end--;
                }
            }
        }
        return result;
    }
}
