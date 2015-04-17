/*
Median of Two Sorted Arrays 

There are two sorted arrays A and B of size m and n respectively.
Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
*/


/**
 * This problem is hard, I can't solve it.
 * Please ref to sol2 and sol3 for correct solutions.
 * @author hpPlayer
 * @date Apr 16, 2015 12:30:26 AM
 */
public class p004_sol1 {
	 public static void main(String[] args){
		 int[] A = {1};
		 int[] B = {1};
		 System.out.println(5 & 0x1);
		// System.out.println(findMedianSortedArrays(A,B));
	 }
	 public static double findMedianSortedArrays(int A[], int B[]) {
	       boolean flag = (A.length + B.length)%2 == 0;
	       if(A.length == 0){
	    	   if(flag){
	    		   return (double) (B[B.length/2] + B[B.length/2-1]) /2;
	    	   }else{
	    		   return (double) B[B.length/2];
	    	   }
	       }
	       if(B.length == 0){
	    	   if(flag){
	    		   return (double) (A[A.length/2] + A[A.length/2-1]) /2;
	    	   }else{
	    		   return (double) A[A.length/2];
	    	   }
	       }
	       int i = 0, j = 0, mid = (A.length + B.length) /2-1;
	       while(mid >=0 && i < A.length && j < B.length){
	            if(A[i] < B[j]){
	                i++;
	                mid--;
	                if(mid == 0){
	                    if(!flag){
	                    	//System.out.println("im here");
	                        return (double) A[i];
	                    }else{
	                        if(i+1 < A.length){
	                        	//System.out.println("im here");
	                            return (double) (A[i] + Math.min(B[j], A[i+1])/2);
	                        }else{
	                             return (double) (A[i] +B[j])/2;
	                        }
	                    }
	                }
	            }else{//A[i] >= B[j]
	                j++;
	                mid--;
	                    if(mid == 0){
	                    if(!flag){
	                        return (double) B[j];
	                    }else{
	                        if(j+1 < B.length){
	                        	//System.out.println("im here");
	                            return (double) (B[j] + Math.min(A[i], B[j+1])/2);
	                        }else{
	                        	//System.out.println("im here");
	                             return (double) (A[i] +B[j])/2;
	                        }
	                    }
	                }
	            }
	       }
	       //System.out.println(i);
	      System.out.println(mid);
	      // System.out.println("im here222");
	       if(i == A.length-1){
	            if(flag){
	            	//System.out.println("lala");
	            	//System.out.println(mid);
	                return (double) (B[j+mid] + B[j+mid-1])/2;
	            }else{
	            	//System.out.println("lala");
	                return (double) B[j+mid];
	            }
	       }
	       
	       if(j == B.length-1){
	            if(flag){
	                //System.out.println("lala");
	                return (double) (A[i+mid] + A[i+mid-1])/2;
	            }else{
	            	//System.out.println("lala");
	                return (double) A[i+mid];
	            }
	       }
		return -1.0;
	    }
}
