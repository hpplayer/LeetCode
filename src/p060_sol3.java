/**
 * Main idea is same to sol2, but using array to store permutation result
 * @author hpPlayer
 * @date Mar 31, 2015 2:52:06 PM
 */
public class p060_sol3 {
	 public String getPermutation(int n, int k) {
	        filltable(n);
	        char[] ary = new char[n];
	        for(int i = 0; i < n; i++){
	            ary[i] = (char) ('1' + i);
	        }
	        
	        getPQ(n, k-1, 0, ary);//k-1 to convert k to 0 based k, dont forget it, its very important t,!!!!!!!!!!!!!!!!!!!!
	        return new String(ary);
	    }
	    
	    public void getPQ(int n, int k, int offset, char[] ary){
	        if(n == 1) return;
	        int p = f[n-2];//-1 for (n-1)!, -1 for convert index to 0 based to search ary
	        
	        int index = k/p;
	        int newK = k%p;
	        char temp = ary[index + offset];
	        for(int i = index+offset; i >= offset + 1; i--){
	            ary[i] = ary[i-1]; 
	        }
	        ary[offset] = temp;
	        
	        getPQ(n-1, newK, offset+1, ary);
	    }
	    
	    int[] f;
	    public void filltable(int n){
	         f = new int[n];
	         f[0] = 1;
	         for(int i = 1; i < n; i++){
	             f[i] = f[i-1]*(i+1);
	         }
	    }
}
