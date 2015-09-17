/*
H-Index II

Follow up for H-Index(p274): What if the citations array is sorted in ascending order? Could you optimize your algorithm?

Hint:

Expected runtime complexity is in O(log n) and the input is sorted.
*/

/**
 * This is my original AC solution without help.
 * Haha. When I see the word "ascending order", I immediately come up with binary search idea.
 * The tricky part is indexing. Like I mentioned before, our original input should be from descending order, but now our input is ascending,
 * so we have to convert the index from i to len - i, i.e. let index base converted from one direction to another direction. If our H index is
 * on the diagonal, then we can return the len - mid, if not, then it is the first index after the diagonal, which should be our start pointer.
 * Since our loop end condition is start <= end, if we exit the loop, then the start pointer must points to the first index after target value,
 * which is diagonal(citations[i] = len - mid) in our case. So we just calculate (len - start) to convert the index, then return the result
 * 
 * Sol1 is my iterative solution
 * Sol2 is my recursive solution
 * Both applies the same idea, both are recommended!
 * 
 * @author hpPlayer
 * @date Sep 16, 2015 11:31:52 PM
 */
public class p275_sol1 {
	public static void main(String[] args){
		int a[] = {1};
		System.out.println(new p275_sol1().hIndex(a));
	}
    public int hIndex(int[] citations) {
        int start = 0, end = citations.length - 1, len = citations.length;
        while(start <= end){
            int mid = start + (end - start)/2;
            if(citations[mid] < len - mid){
                start = mid + 1;
            }else if(citations[mid] > len - mid){
                end = mid-1;
            }else{//if it is on the diagonal
            	//System.out.println("here");
                return len - mid;
            }
        }
        
        return len - start;
    }
}
