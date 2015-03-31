import java.util.Arrays;
/**
 * The key is for n and k, each subtree has (n-1)!, so we just need to visit k/(n-1)!th subtree to find the result
 * when reach the k/(n-1)! subtree, we just need to get the k%(n-1) th node
 * 
 * But I got big problem in getting correct n and k in recursion... More details coming next day
 * @author hpPlayer
 * @date Mar 31, 2015 1:27:11 AM
 */

public class p060_sol2 {
	public static void main(String[] args) {
		p060_sol2 test = new p060_sol2();
		//System.out.println(2/4);
		//System.out.println(test.getP(4));
		System.out.println(test.getPermutation(3, 2));
	}

	public void getPermutation(char[] ary, int n, int k, StringBuilder Sb) {
		if(n ==1){
			Sb.append(ary[0]);
			return;
		}
	
		int index = (k-1)/getP(n-1);//which subtree, k-1 to convert 0 based
		k = (k-1)%getP(n-1);//what is the new k in that subtree, k-1 to convert 0 based
		System.out.println(index);
		System.out.println(k);
		Sb.append(ary[index]);
		while(index < n-1) ary[index++] = ary[index];
	

		getPermutation(ary, n-1, k+1, Sb);
	}
	
	public String getPermutation(int n, int k) {
		char[] ary = new char[n];
		for(int i = 0; i < ary.length; i++){
			ary[i] = (char) ('1' + i);
		}
		StringBuilder sb = new StringBuilder();
		getPermutation(ary, n, k, sb);
		return sb.toString();
	}
	
	public int getP(int n){
		int result =  1;
		for(int i= 1; i <= n; i++){
			result *= i; 
		}
		return result;
	}
}
