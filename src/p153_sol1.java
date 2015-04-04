/**
 * My AC solution with help.
 * I know it looks like not easy understandable,
 * but I do apply the binary search technique.
 * Basically, I iteratively compare the previous element with current elment and next element with current element 
 * if prev and post are both > current element, then it means current element is our min number
 * if we found tail > prev && (post > curr || prev < curr), then search left part 
 * otherwise search right part
 * 
 * I admit the code looks mess and I cannot figure it correctly without test cases..
 * So please ref to sol2 for more clean and good code
 * @author hpPlayer
 * @date Apr 4, 2015 12:09:58 AM
 */
public class p153_sol1 {
	public static void main(String[] args){
		int[] a = {2,3,4,5,1};
		System.out.println(findMin(a));
	}
    public static int findMin(int[] num) {
    	if(num.length == 1) return num[0];
    	if(num.length == 2) return Math.min(num[0], num[1]);
        int pointerA = 0;
        int pointerB = num.length -1;
        while(pointerA <= pointerB){
        	System.out.println("A:" + pointerA);
        	System.out.println("B: " + pointerB);

            int mid = pointerA + (pointerB - pointerA)/2;
        	if(mid == 0){
        		//System.out.println("im here");
        		return Math.min(num[mid], num[mid+1]);
        	}
        	if(mid == num.length-1){
        		return Math.min(num[mid], num[mid-1]);
        	}
            if(num[mid] < num[mid+1] && num[mid] <num[mid-1]){
            	return num[mid];
            }
            if((num[mid] < num[mid+1] || num[mid-1] < num[mid]) && num[pointerB] > num[mid-1]){
            	pointerB = mid-1;
            }else{
            	pointerA = mid+1;
            }
        }
        return num[0];
    }
}
