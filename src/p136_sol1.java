import java.util.HashSet;
/**
 * My hashset solution, at first I use for(String s : hashset) to get the value but got LTE
 * After looking other's solution, if using hashset, either use the iterator with initialization or direct call set.iterator.next() will get AC
 * So plz be careful about how iterator is implemented
 * HashSet<Integer> set
 * Iterator<Integer> it = set.iterator();
 * while(it.hasNext()){
 * 	System.out.println(it.next());
 * }
 * 
 * Basic idea is simple, if counter a array that not in hashset just insert into set, if already in set then remove it
 * so finally the only one value stored in set will be the answer.
 *  
 * This answer supposed to be linear and costs O(n) space complexity (n/2)
 * If we want solve this question without extra space, use bit manipulation, see sol2
 * @author hpPlayer
 * @date Apr 5, 2015 12:18:42 AM
 */

public class p136_sol1 {
	public static void main(String[] args){
		int[] ary = {16705, 9374, 16705,9374,123};
		System.out.println(singleNumber(ary));
	}
    public static int singleNumber(int[] A) {
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < A.length; i++){
            if(!set.contains(A[i])){
                set.add(A[i]);
            }else{
                set.remove(A[i]);
            }
        }
        return set.iterator().next();
        /*
        Iterator<Integer> setIterator = set.iterator();
        int result = Integer.MIN_VALUE;
        if(setIterator.hasNext()){
        	result = (Integer) setIterator.next();
        }
        return result;
        */
    }
}
