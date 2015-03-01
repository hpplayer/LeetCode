/***
 * use binary search, get the right value
 * caution: (1) use x/mid == mid to judge the value, instead of mid*mid == 2, since * may lead to overflow
 * 			(2) if right < left, then there is no such value can be found, but we know the value must locates between 
 * 				right and left, so we need return the closet small value, which is now right
 * 			(3) for the while case of binary search, remember the condition that jump out of loop is right > left, the case left == right 
 * 			should still in the loop!!!!!
 */
public class p69_sol2 {
	public static void main(String[] args) {
		// System.out.println(sqrt(183692038));
		System.out.println(sqrt2(2));
	}
	

	private static int sqrt2(int x) {
        int left = 1, right = x;//the min is 1, since no x > 1 can take sqrt of 0, the max is x, since the case x =1, will have max = 1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(mid == x/mid){
                return mid;
            }else if(mid > x/mid){ //case we got too big mid
                 right = mid-1;
            }else{
                left = mid +1;
            }
        }
        return right;//case we cant find such exact mid, so we need return right
    }
	
	public int sqrt(int x) {
	    return sqrt(x, 1, x);
	}
	
	private int sqrt(int x, int left, int right){
		if(right < left)
			return right;
		int mid = left + (right - left)/2;
		if(x/mid == mid)// dont use mid*mid, since it may get overflow
			return mid;
		else if (x/mid > mid)
			return sqrt(x, mid+1, right);
		else
			return sqrt(x, left, mid-1);
	}
}
