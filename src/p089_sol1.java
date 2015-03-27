import java.util.ArrayList;
import java.util.List;
/**
 * This algorithm is so brilliant, it observed that the code is symmetric. Symmetric is the key to find the solution
 * for example when n = 3
 * we will have:
 * 000
 * 001
 * 011
 * 010
 * 110
 * 111
 * 101
 * 100
 * We found that part start with 1 is the reverse of part start with 0
 * So, when we firstly do it to part start with 0, using DFS.
 * When reaching boundary case (n = 0), we add 0 to it 
 * then left part return back, we reversely read value from list from left part and add 1 to the rightmost part(<< n-1)
 * then we add left and right to the result;
 * 
 * Finally, we will get out output, so smart!!!
 * Remark:
 * n is actually 1 based start with 1, so when we add 1 to the leftmost index, we need add to the position of n-1
 * @author hpPlayer
 * @date Mar 27, 2015 12:21:41 AM
 */

public class p089_sol1 {

	public static void main(String[] args){
		for(int i : grayCode(2)){
			System.out.println(i);
		}
	}
	
    public static List<Integer> grayCode(int n) {
        return DFS(n);
     }
     public static List<Integer> DFS(int n){
         if(n == 0){
             List<Integer> temp = new ArrayList<Integer>();
             temp.add(0);
             return temp;
         }
         List<Integer> result = new ArrayList<Integer>();
         List<Integer> startWithZero = DFS(n-1);
         List<Integer> startWithOne = new ArrayList<Integer>();
         for(int i = startWithZero.size()-1; i >= 0; i--){
             startWithOne.add(startWithZero.get(i) + (1 << (n-1)));
         }
         result.addAll(startWithZero);
         result.addAll(startWithOne);
         return result;
     }
}
