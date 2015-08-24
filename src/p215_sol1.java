/**
 * This problem can be solved with Array.sort(nums), return nums[k]
 * But obviously it is not an ideal solution in the interview. The running time O(logN)
 * Here is the first optimized quick sort algorithm. In quick sort algorithm, we firstly pick the pivotal, then 
 * put elements smaller than pivotal to the left part, put elements larger than pivotal to the right part, for elements with duplicate 
 * values with pivotal, we can put it anywhere around pivotal. Here We observed if we can reverse the put order, i.e. if we put larger elements
 * in the left part, say we have i elements in this part, then our pivotal will be the i+1 largest elements in the array! If we are lucky, we 
 * even dont need to further visit the left part and right part if our pivotal is just the kth largest elements! So, here comes our solution.
 * We firstly do the partition on the array. For convenience, we always pick the leftmost element as the pivotal, then we partition the rest 
 * array around the pivotal. we then return the updated index of pivotal. If we found the index of pivotal is just k -1, which means pivotal 
 * is the kth largest element, then we found the solution and return the result. However, if we found the index of pivotal is larger than 
 * k-1, which means we have a little bit more elements in left part, so we have to move scope only to the left part, and search the left part.
 * Otherwise, we will move scope to the right part, and search the right part.
 * 
 * Since we always search the half part and discard the other part, the best performance according to the quick sort is O(n)
 * However, in worst case, the time complexity will be O(n^2)
 * The proof of the linear time complexity is very hard, and it is more like an algorithm class's HW, but we can ref attachment "Solution explained"
 * to see the proof
 * 
 * 
 * More details can be found below.
 * 
 * Remark:
 * 1) One more time, in-place swap will not work if we are doing the swap on one element (usually the boundary case), so be careful
 * 2) a heap sort algorithm can be found in sol2, and will be updated tomorrow!
 * 
 * Update:
 * heap solution has been updated, but I don't think using heap to store the number is a good idea, since it will cost extra spaces
 * My naive Arrays.sort approach will cost O(logN) in sorting so is the heap insertion and deletion. In plus, my naive solutin would't 
 * cost extra space to store elements.
 * 
 * So based on the result, I believe Sol1 that using partitioning is more fantastic!
 * 
 * Sol2 is the heap sort solution
 * Sol3 is the python version of sol1
 * Sol4 is the python version of sol2
 * 
 * @author hpPlayer
 * @date Aug 23, 2015 9:33:23 PM
 */

public class p215_sol1 {
	public static void main(String[] args){
		//int[] nums = {3,2,1,5,6,4};
		int[] nums = {1,2};
		//System.out.println(9);
		/*
		nums[0] += nums[1];
		nums[1] = nums[0]-nums[1];
		nums[0] -= nums[1];
		*/
		System.out.println(new p215_sol1().findKthLargest(nums, 1));
	}

    public int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        while(true){
        	
            //get the position of pivotal, if there are k-1 larger number before it, then pivotal is our kth largest number
            int pos = partition(nums, left, right);
            //found target
            if(pos == k -1 ){
                return nums[pos];
            }else if (pos > k-1){
                //there are more than k larger numbers in the left, so we need search in left part by moving right bound leftward
                right = pos - 1;
            }else{
                //there are less than k larger numbers in the left, so we need search in right part by moving left bound rightward
                left = pos + 1;
            }
        }
    }
    
    public int partition(int[] nums, int left, int right){
        int pivotal = nums[left];
        int temp = left++;//backup left
        while (left <= right){
        	//System.out.println(Arrays.toString(nums));
            //if we found a pair that can be switched (our goal is putting larger number in front, so we can get kth largest number from head to tail)
            if(nums[left] < pivotal && nums[right] > pivotal){
                    nums[left] += nums[right];
                    nums[right] = nums[left] - nums[right];
                    nums[left++] -= nums[right--];
            }
            if (nums[left] >= pivotal){
                //a larger number already in front of array, we just skip it
                left ++;
            }
            if (nums[right] <= pivotal){
                //a smaller number already in back of array, we just skip it
                right --;
            }
        }
        //After the while loop end, the right pointer should points to the last element that is larger than pivotal (say at index i)
        //so after we swap this element with pivotal, our pivotal will become i+1 th largest number
        //Notice!!! Remember, our in-place algorithm will not work on the single element, which means if now temp and right points to the 
        //same cell, we can't use in-place swap!
        int pivotal_temp = nums[temp];
        nums[temp]= nums[right];
        nums[right] = pivotal_temp;
        return right;//return right, which now become the index of pivotal
    }
}
