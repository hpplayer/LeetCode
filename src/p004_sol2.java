/**
 * Remark: All n/2 below we need take ceiling values like ceil(0.5) = 1, but due to limitation, I can't add ceil notation.
 * According to MIT material, assume total length is n, A length is l, B length is m
 * then if median is in the A, we will have following range of median in A:
 * max(1, n/2 - m) to min(l, n/2)
 * 
 * max(1, n/2 - m) means we put B in the left of A, so the median in A will be as left as possible
 * min(l, n/2) means we put B in the right of A, so the median in A will be as right as possible,
 * we put  1 and l here to limit the range, so that if we cannot find such valid range then median must in B
 * 
 * The basic idea is that, by a given A[i] in the range we get above, we can easily check if it is the global median or not.
 * If not we then use binary search to find the global median.
 * Checking: 
 * if A[i] is median, then in A there will be i-1 values < A[i]
 * Also, in B we will have exactly, n/2 - (i-1) values < A[i],
 * since the array is ordered, we will have B[i]<=A[i] <= B[j+1], where j = n/2 - (i-1).
 * 
 * findMedianSortedArrays() is the original implementation of MIT material with recursive way
 * findMedianSortedArrays2() is the optimized version with iterative way
 * 
 * Remark:
 * 1) I learn a lesson from there that it may cause bug if we replace (m+n)/2 by m + (n-m)/2
 * Ex: n = 0, m = 1
 * (m+n)/2 = 0
 * m+(n-m)/2 = 1 
 * not equal
 * 2) when we got the median, but it is even case 
 * then we will have several cases:
 * second median in B, second median in A, or we don't know
 * so we need three if-else statements to find one
 * In the case we don't know, we will have following case:
 * B[j] < A[i] indicates we found one global median
 * A[i-1] < A[i] array is sorted, by definition,
 * since our return A[i] is the second global median,
 * we need return the one who is closer to A[i], i.e. which is larger
 * 
 * This is the best approach towards this problem so far.
 * sol3 provides another approach that looking for kth element in two arrays for ref
 * @author hpPlayer
 * @date Apr 15, 2015 11:46:22 PM
 */
public class p004_sol2 {
	public static void main(String[] args) {
		//int[] A = { 1, 2, 4, 8, 9, 10};
		int[] A = {4,5,6,7,9};
		int[] B = {};
		//int[] B = { 3, 5, 6, 7  };
		// System.out.println(5 & 0x1);
		System.out.println(findMedianSortedArrays(A, B));
	}

	public static double findMedianSortedArrays2(int A[], int B[]) {
		if (A == null || B == null)
			return 0;
		int m = A.length, n = B.length, mid = (m + n) / 2;
		boolean even = ((m + n) & 1) == 0;

		int left = Math.max(0, mid - n), right = Math.min(m - 1, mid);
		while (left <= right) {
			int i = (left + right) / 2, j = mid - 1 - i;
			if (j >= 0 && A[i] < B[j])//dont' forget the "= 0" case
				left = i + 1;
			else if (j < n - 1 && A[i] > B[j + 1])
				right = i - 1;
			else {
				if (!even)
					return A[i];
				else if (j < 0 || j >= n)//first median not in B
					return (A[i] + A[i - 1]) / 2.0;
				else if (i <= 0)//or i==0, i.e. this case means we reach the head of A
					return (A[i] + B[j]) / 2.0;
				else
					return (A[i] + Math.max(B[j], A[i - 1])) / 2.0;
			}
		}
		return findMedianSortedArrays(B, A);
	}

	public static double helper(int[] A, int[] B, int left, int right) {

		int m = A.length, n = B.length;
		int mid = (m+n)/2;
		//int mid = n + (m - n) / 2;
		//System.out.println(m);
		//System.out.println(n);
		//System.out.println((m+n)/2);
		//System.out.println(m + ((n - m) / 2));
		if (left > right) {//if we don't have valid pair, then the median should be in the B
			//System.out.println("im here");
			return helper(B, A, Math.max(0, mid - m), Math.min(n - 1, mid));
		}
		int i = (left + right) / 2, j = mid - i - 1;//get i and j, try to use binary to find the match
		if (j >= 0 && A[i] < B[j])//our picked median is too small, need increase
			return helper(A, B, i + 1, right);
		if (j < n - 1 && A[i] > B[j + 1])//our picked median is too big, need decrease
			return helper(A, B, left, i - 1);
		if (((m + n) & 0x1) > 0){// || (i <= 0 && (j < 0 || j >= n))) {//if length is odd, and found median
			return A[i];
		}
		if (j < 0 || j >= n)//if even, both median is in A
			return (A[i] + A[i - 1]) / 2.0;
		else if (i <= 0)//if even, another one have to be in B
			return (A[i] + B[j]) / 2.0;
		else//we don't know where other median can be, so we will pick the larger one
			//(our algorithm will pick the second one, so now we need pick first one which is closer to A[i])
			return (A[i] + Math.max(B[j], A[i - 1])) / 2.0;
	}

	public static double findMedianSortedArrays(int A[], int B[]) {
		int m = A.length, n = B.length, mid = (n+m)/2;//mid = m + (n - m) / 2;
		return helper(A, B, Math.max(0, mid - n), Math.min(m - 1, mid));
	}
}
