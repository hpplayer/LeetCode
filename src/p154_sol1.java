/**
 * My original solution that get AC, I looked up problem 81 (search in rotated sorted array 2)
 * 
 * My original idea is getting avoid of duplicates by checking mid with start or mid with end,
 * and do similar search as for 153.
 * But it is possible that start and end are duplicates, so I use the technique in p81, that simply let start++, if we face that situation
 * 
 * But finally, I found out the previous two operations are not necessary, whenever we have cases that not belong to p153, it must be
 * involved duplicates, for those cases we can not safely throw any subsequence away, so we just need to let start ++ to force loop
 * continue.
 * 
 * consider:
 * we have 3 elements: 1, 2, 3 
 * it may rotate 0 time, so we have 1 2 3
 * it may rotate 1 time, so we have 2 3 1(mid > right and > left, if more elements, we may have 2 3 4 1, 2 3 4 5 1), obviously we need search right part
 * it may rotate 2 time, so we have 3 1 2 (mid < right and < left, if more elements, we may have 4 1 2 3, 5 1 2 3 4), obviously we need search left part
 * it may rotate 3 time, so we have 1 2 3....
 * for case 0, 123, we simply return the leftmost node
 * for case 1  231, we check if mid > right or mid > left
 * for case 2  312, we check if mid < left or mid < right
 * 
 * if we still find cases belong to those cases, we can just throw half sequence as there is no duplicates
 * if we found other cases not belong to those cases, then we have to let start ++ and force loop continue
 * 
 * Based on this assumption, we dont have to avoid duplicates by cheching ahead, ref to sol2 for a more concise solutionn
 * So in worst case, where there are contain all duplicates like (11111)
 * the time complexity increased from logn to n
 * @author hpPlayer
 * @date Apr 4, 2015 10:16:08 PM
 */
public class p154_sol1 {
	 public int findMin(int[] num) {
	        if(num.length == 2) return Math.min(num[0], num[1]);
	        int start = 0, end = num.length -1;
	        while(start < end){
	            if(num[start] < num[end]) return num[start];
	            
	            int mid = start + (end - start)/2;
	            if(num[start] == num[mid]){
	                 start ++;
	                 continue;
	            }
	            if(num[end] == num[mid]) {
	                end --;
	                continue;
	                }
	            if(num[mid] > num[end]){
	                start = mid +1;
	            }else if (num[mid] < num[start]){
	                end = mid;
	            }else{//we are in a trap that no useful information is provided, so we need help loop continue, and search information again
	                start ++;
	            }
	        }
	        return num[start];
	    }
}
