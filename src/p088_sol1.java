import java.util.Arrays;
/**
 * My original AC solution without help
 * The problem is not as easy as it is labeled
 * My raw idea is attaching larger elements to the end of A
 * then left-shift to the start
 * Last step is set remaining values to 0 (however, it seems leetcode only check m+n-1 length in A, so this step is not necessary
 * in this setting
 * 
 * Also we have several boundary cases need to handle
 * for example m == 0(which costs me super long time to figure it out), the error in leetcode: [], I thought it is null case or empty 
 * case, but it is not. The case is A.length ==1, but m = 0, i.e no initial value for A 
 * 
 * Other boundary cases:
 * 1) since m and n given are 1 based, we need convert it to 0 based, there comes a problem.
 * If after reduction m == 0 and n ==0, then we need handle this case 
 * 2) if m == 0 && n != 0 or n== 0&& m != 0, we need check this case 
 * 3) left-shift may > 0
 * 
 * Anyway, there are so many boundary cases to handle, so this "easy" problem costs me a lot of time
 * 
 * But, in most time, a time-consuming problem is actually not designed in that way, there are must some convenient way to solve the problem
 * So, see sol2 for a shorter and clever approach.
 * 
 * @author hpPlayer
 * @date Apr 6, 2015 11:16:11 PM
 */
public class p088_sol1 {
	public static void main(String[] args) {
		int[] a = new int[9];
		a[0] = 0;
		a[1] = 0;
		a[2] = 3;
		int b[] = {-1,1,1,1,2,3};
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
		if (A == null || B == null || A.length == 0 || B.length == 0) return;
		int m2 = m;
		int n2 = n;
        if(m==0){
            for(int i = 0; i < n; i++){
                A[i] = B[i];
            }
            return;
        }
        if(n == 0) return;
		int i = A.length - 1;
		m --;
		n --;
		if(m == 0 && n == 0){
			int a = Math.max(A[0], B[0]);
			int b = Math.min(A[0], B[0]);
			A[0] = b;
			A[1] = a;
			return;
		}
		while (m+1 > 0 && n+1 > 0) {
			if (A[m] > B[n]) {
				A[i] = A[m];
				i--;
				m--;
			} else if (A[m] == B[n]) {
				A[i] = A[m];
				A[i-1] = B[n];
				i -= 2;
				m--;
				n--;
			} else {
				A[i] = B[n];
				n--;
				i--;
			}
		}
		System.out.println(m);
		if (m >= 0 && n+1 == 0) {
			System.out.println(Arrays.toString(A));
			while (m >= 0) {
				A[i] = A[m];
				m--;
				i--;
			}
		}

		if (n >= 0 && m+1 == 0) {
			System.out.println(Arrays.toString(A));
			while (n >= 0) {
				A[i] = B[n];
				n--;
				i--;
			}
		}
		
		for (int j = i; j < A.length&&j>=0; j++) {
			A[j - i] = A[j];
		}
		//below is not necessary, as leetcode does not check it
		for(int j = m2 + n2; j < A.length; j++){
			A[j] = 0;
		}

	}
}
