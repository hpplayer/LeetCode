import java.util.Arrays;

/**
 * The key is for n and k, each subtree has (n-1)!, so we just need to visit
 * k/(n-1)!th subtree to find the result when reach the k/(n-1)! subtree, we
 * just need to get the k%(n-1) th node
 * 
 * But I got big problem in getting correct n and k in recursion... More details
 * coming next day
 * 
 * Update(3/31/2015):
 * getPermutation is my solution while getPermutation2 is other's solution
 * Basically, this problem requires a little trick to speed the search
 * Here are two important keys in solving this question:
 * 1) understand how to find a number in subtree
 * given n and k, by dividing k/(n-1)!, we can get which subtree does the k exists, what does it mean?
 * when n = 3, k = 3. In the initial step, the first letter is not fixed, so we can k/(n-1)! to get fix the letter,
 * we can get 3/2 = 1, 1, the first 1 means we need put letter in index 1 to become the first letter, the second 1 means 
 * we need search k=1 in that subtree whose first letter has been fixed.
 * 
 * Caution: it is easy to make error that we need to convert k before operations, for example, when n = 3, k = 4, 
 * 4/2 = 2, then it indicates we need search the subtree start with 3, but 4th element is actually in subtree start with 2,
 * this error comes from the mix of index, here we have 0 based index (subtree2 start with letter 3), and have 1 based index(k = 1,2,3,4...)
 * So we need firstly convert k to 0 based index by simple -1
 * 
 * After fixing the first letter, we will search new k (got from old k % (n-1)!) in this subtree. Repeat doing this until n == 1 or k ==0, which
 * means we have found the result.
 * 
 * Caution:when search subtree, it is very easy to get confusion with the index. (index in subtree is k, but the real index in char array is 
 * length of our fixed prefix + index in this subtree) So we introduce an offset to indicate the length of fixed prefix. Also, we must be cautious 
 * that when we are fixing prefix, we should not simply swap ary[i] with ary[offset], what we do should be let ary bettween offset and index right shift
 * by 1 otherwise we will not keep the order. Example: 123, when index = 2, if we simply swap 3 and 1, then we get 321, which is not the start  or root of subtree
 * start with 3, the correct start should be 3 1 2, so, there is that.
 * 
 *  Again, it is better to draw a simple example and illustrate the correctness of code on that example.
 *  In this problem, n = 3, k = 1 -9 is a such good example. 
 * 
 * Remark:
 * Another possible optimization to this problem is to precompute the value of permutation and store in an aray.
 * Here I used a function getP() to get the permutation which may have repeated computation.
 * The optimized solution can be seen in sol3
 * 
 * 
 * @author hpPlayer
 * @date Mar 31, 2015 1:27:11 AM
 */

public class p060_sol2 {
	public static void main(String[] args) {
		p060_sol2 test = new p060_sol2();
		// System.out.println(2/4);
		// System.out.println(test.getP(4));
		System.out.println(test.getPermutation(3, 3));
	}

	public void getPermutation(char[] ary, int n, int k, int offset) {
		/*
		 * n=1 means we have only one unfixed index left, it will only have one choice which means we have reached the end
		 * or we can write if(k == 0) return, it is same that indicates we have found the result
		 */
		if (n == 1) return;
	
		int index = k / getP(n - 1);
		int newK = k % getP(n - 1);
		// System.out.println(index);
		// System.out.println(k);
		// System.out.println(offset);
		char temp = ary[index + offset];
		for (int i = index + offset; i >= offset + 1; i--) {
			ary[i] = ary[i - 1];
		}
		ary[offset] = temp;

		// System.out.println(Arrays.toString(ary));
		getPermutation(ary, n - 1, newK, offset + 1);
	}

	public void getPermutation2(char[] ary, int n, int k, StringBuilder Sb) {
		if (n == 1) {
			Sb.append(ary[0]);
			return;
		}

		int index = (k - 1) / getP(n - 1);// which subtree, k-1 to convert 0
											// based
		k = (k - 1) % getP(n - 1);// what is the new k in that subtree, k-1 to
									// convert 0 based
		System.out.println(index);
		System.out.println(k);
		Sb.append(ary[index]);
		while (index < n - 1)
			ary[index++] = ary[index];

		getPermutation2(ary, n - 1, k + 1, Sb);
	}

	public String getPermutation(int n, int k) {
		char[] ary = new char[n];
		for (int i = 0; i < ary.length; i++) {
			ary[i] = (char) ('1' + i);
		}
		// StringBuilder sb = new StringBuilder();
		getPermutation(ary, n, k - 1, 0);
		return new String(ary);
	}

	public int getP(int n) {
		int result = 1;
		for (int i = 1; i <= n; i++) {
			result *= i;
		}
		return result;
	}
}
