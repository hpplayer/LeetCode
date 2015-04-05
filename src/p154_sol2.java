/**
 * Think about 4 elements example:
 * 1 2 3 4 normal order
 * rotated order:
 * 3 4 1 2 min in right part case 1
 * 4 1 2 3 min in left part case 2
 * 
 * other cases can only caused by duplicates, and make us judge which subsequence is useless, so we have to keep both, and let
 * start ++ or end -- to force loop continue
 * 
 * So in worst case, where there are contain all duplicates like (11111)
 * the time complexity increased from logn to n
 * @author hpPlayer
 * @date Apr 4, 2015 10:33:50 PM
 */
public class p154_sol2 {
    public int findMin(int[] num) {
    	int start = 0, end = num.length -1;
    	while(start < end){
    		if(num[start] < num[end]){//normal order, simply return min 
    			return num[start];
    		}
    		//rotate version, calculate mid
    		int mid = start + (end - start)/2;
    		if(num[mid] > num[end]){//case 1
    			start = mid +1;
    		}else if (num[mid] < num[start]){//case 2
    			end = mid;
    		}else{//other cases that caused by duplicates
    			start ++;//or let end --, force loop continue;
    		}
    	}
    	return num[start];//the loop ended when start == end, and it means we found the min
    }
}
