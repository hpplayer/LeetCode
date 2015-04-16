/**
 * This is one of the approach that use find kth (k here is the median) element in two arrays
 * Basically, we can calculate the median of two array respectively, if
 * they are same, then we found the global median, if A[i] > B[j], then
 * we know the global median should be in the first half of A or second half of B
 * since other halves will either be too big or be too small.
 * Similarly, if A[i] < B[j], then we know the global median should be in the first half of B or second half of A
 * since other halves will either be too big or be too small.
 * By doing this, we can shrink our scope to two new sub-arrays, and we can do same technique 
 * 
 * Remark:
 * 1)This approach is kind of hard to implement, especially, when we are getting the mid of ary, we need to notice the boundary case
 * when we calculate the new mid, we should use:
 * int aMid = aLen * k / (aLen + bLen);
 * int bMid = k - aMid -1;
 * By doing this aMid and bMid will both in the range of 0 to k, 
 * if we directly get aLen/2, then we may have aMid = aMid, so that bMid <0
 * 2) An important thing here is that the length of A and B may be different, but we treat them as same length, so we dispose same length of
 * useless subarray each recursion
 * 
 * This maybe not the best approach, but it provides a new approach towards this problem
 * @author hpPlayer
 * @date Apr 15, 2015 11:04:40 PM
 */
public class p004_sol3 {
	public static void main(String[] args) {
		int[] A = { 1,1 };
		int[] B = { 1,1 };
		// System.out.println(5 & 0x1);
		System.out.println(findMedianSortedArrays(A, B));
	}
	
	public static double findMedianSortedArrays(int A[], int B[]) {
		int m = A.length;
		int n = B.length;
		if(((m+n) & 1) >0){
			return (double) findKth(A, B, (m+n)/2, 0, m-1, 0, n-1);
		}else{
			return (double) (findKth(A, B, (m+n)/2, 0, m-1, 0, n-1) + findKth(A, B, (m+n)/2 -1, 0, m-1, 0, n-1))/2;
		}
	}
	
	public static int findKth(int[] A, int[] B, int k, int aStart, int aEnd, int bStart, int bEnd){
		int aLen = aEnd - aStart +1;
		int bLen = bEnd - bStart +1;
		
		if(aLen == 0){//only need to get kth element in B
			return B[bStart + k];
		}
		if(bLen == 0){//only need to get kth element in B
			return A[aStart +k];
		}
		if(k==0){
			return Math.min(A[aStart], B[bStart]);//return min when k = 1
		}
		//int aMid = aLen/2+1;//get a Mid
		//System.out.println(k);
		//System.out.println(aLen);
		//System.out.println(aLen * k / (aLen + bLen));
		//System.out.println(aLen/2);
		//System.out.println();
		
		//aMid + bMid + 1 = k must be satisfied, also aMid >= 0, bMid >= 0;
		/*
		int aMid, bMid;
		Random r = new Random();
		if (aLen > bLen) {
		bMid = (int)(Math.min(bLen,k) * r.nextDouble());
		aMid = k - bMid - 1;
		} else {
		aMid = (int)(Math.min(aLen,k) * r.nextDouble());
		bMid = k - aMid - 1;
		}
		*/
			//int aMid = aLen/2;
		//literatly, aMid = aLen / 2 and k = £¨aLen + bLen)/2, so aMid = aLen * k/(aLen + bLen), but calculate this way can help us get k>aMid> 0
		int aMid = aLen * k / (aLen + bLen);//guarantee k>aMid> 0(if use aLen/2, we may get k == aMid, so that bMid will be -1)
		//System.out.println(aMid);
		int bMid = k - aMid -1;//get bMid
		//System.out.println(k);
		aMid = aMid + aStart;//get index in the array
		bMid = bMid + bStart;
		if(A[aMid] > B[bMid]){//median in the first half of A or second half of B
			//k = k - (aMid - aStart +1);
			k = k - (bMid - bStart +1);//update k, we can get rid of first half of B
			aEnd = aMid;
			bStart = bMid +1;
		}else{
			k = k - (aMid - aStart +1);//update K, we can get rid of second half of A
			//k = k - (bMid - bStart +1);
			bEnd = bMid;
			aStart = aMid +1;
		}
		return findKth(A, B, k, aStart, aEnd, bStart, bEnd);
	}
}
