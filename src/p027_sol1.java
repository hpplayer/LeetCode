/*
Remove Element

Given an array and a value, remove all instances of that value in place and return the new length.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.
*/

/**
 * My AC solution without help, and the running time is very fast(almost reach the head!)
 * This is a two pointer algorithm, one pointer moves forward from head, one pointer move backward from tail
 * We force the forward pointer points those invalid number while backward pointer points those valid number 
 * if such pair exist, then swap the value and let forward points move one step forward to continue the loop;
 * After the loop, we check if current forward pointer's value is valid, if invalid, then forward pointer's index num --, finally 
 * we output prev pointer index +1, why? since prev pointer is 0 based. and we need length(ex: [1], k = 3, prev = 0, and we need return 1) 
 * @author hpPlayer
 * @date Mar 26, 2015 12:48:28 AM
 */
public class p027_sol1 {
	public static void main(String[] args){
		int a[] = {1};
		System.out.println(new p027_sol1().removeElement(a, 3));
	}
    public int removeElement(int[] A, int elem) {
    	if( A == null || A.length == 0) return 0;
        //if(A.length == 1 && A[0] == elem) return 0;
        //if(A.length == 1 && A[0] != elem) return 1;
        int prev = 0, post = A.length -1;
        while(prev < post){
            while(prev < post && A[post] == elem) post--;
            while(prev < post && A[prev] != elem) prev++;
            if(A[prev] == elem && A[post] != elem){
                A[prev] = A[post];
                A[post] = elem;
                prev ++;
            }
        }
        if(A[post] == elem){
        	prev --;
        }
        return prev +1;
    }
}
