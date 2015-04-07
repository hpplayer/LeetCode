import java.util.Arrays;
/**
 * Actually, we dont need place value in end of A, then left shift
 * We can actually directly put value in m+n -1, why? because, our final output array length will be m + n, and the corresponding index 
 * in array should be m+n-1, and we dont have a or b values there, so we can just update values there. Is it possible, to a certain time 
 * we begin overlap values in A? no, since we guarantee all values in B can be inserted in the array, and we have n extra space to store
 * values, so it is not possible.
 * 
 * Besides, this approach also implemented a clever way to deal with boundary cases,
 * if we got value same in A and B, what should we do? we can treat it as B[i] > A[j] or B[i] < A[j], either way is ok
 * We may have boundary cases that n <=0, that is perfect, since that means we have inserted B into A, and we dont need update remaining
 * values in A. 
 * For the boundary case that m < 0, that means we our remaining task is to insert each remaining values in B to A, which can be treated 
 * as B[i] >= A[j]. where we just need to insert values into A[index]
 * 
 * Remark:
 * 1)since given m and n are 1 based, so we still need to convert m and n to 0 based by m-- and n--
 *   But the initial m+n-1 place need to be kept, so we can know where our final array ends
 * 2)Be careful about the difference of i-- and --i
 * A[i--] = b means we update values in A[i] while let i--
 * A[--i] = b means we update values in A[i-1] while let i--;
 * 
 * @author hpPlayer
 * @date Apr 6, 2015 11:26:18 PM
 */

public class p088_sol2 {
	public static void main(String[] args) {
		int[] a = new int[9];
		a[0] = 0;
		a[1] = 0;
		a[2] = 3;
		int b[] = {-1,1,1,1,2,3};
		int aaa = 3;
		System.out.println(aaa--);
		System.out.println(--aaa);
		/*
		int a[] = new int[6];
		a[0] = 4;
		a[1] = 5;
		a[2] = 6;
		//a[1] = 0;
		//a[2] = 1;
		//a[3] = 1;
		int b[] = {1,2,3};
		*/
		merge(a,3,b,6);
		System.out.println(Arrays.toString(a));
	}
	  public static void merge(int A[], int m, int B[], int n) {
	        int index = m+n-1;//current place to put value
	        m--;
	        n--;
	        //now m and n become index in two array, so n ==0 means index = 0 in B, which is meaningful
	        while(n >= 0){
	          if (m < 0 || A[m] <= B[n]){//we must first check if m < 0, otherwise we may use negative m to retrieve index 
	                A[index--] = B[n--];
	            }else if(A[m] > B[n]){
	                A[index--] = A[m--];
	            }
	        }
	    }
}
