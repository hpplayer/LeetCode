/**
 * My AC solution...succeed at second try...
 * (first try return compile error cuz I wrote the code on the leetcode directly)
 * Basically, I am doing binary search and find the first match index,
 * then do linear search before and after the index and find all possible nodes.
 * However, this is not optimized, since if we have an array that contains purely target, then 
 * this solution will take O(n) time instead of O(logn), anyway it pass the testcase though
 * @author hpPlayer
 * @date Mar 20, 2015 6:09:57 PM
 */
public class p034_sol1 {
	public int[] searchRange(int[] A, int target) {
        int start = 0, mid = 0;
        int end = A.length -1;
        boolean flag = false;
        while(start <= end){
            mid = (start + end) /2;
            if(A[mid] == target){
                flag = true;
                break;
            }else if (A[mid] > target){
                end = mid -1;
            }else{
                start = mid +1;
            }
        }
        int[] result = new int[2];
        if(!flag){
            result[0] = -1;
            result[1] = -1;
            return result;
        }else{
            start = mid;
            while(start -1 >= 0 && A[start-1] == target){
                start --;
            }
            end = mid;
            while(end + 1 < A.length && A[end+1] == target){
                end ++;
            }
            result[0] = start;
            result[1] = end;
            return result;
        }
    }
}
