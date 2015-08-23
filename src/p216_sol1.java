import java.util.ArrayList;
import java.util.List;

/*
Combination Sum III 

Find all possible combinations of k numbers that add up to a number n, 
given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Ensure that numbers within the set are sorted in ascending order.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]
*/	

/**
 * This is my original AC solution without help
 * Sol2 is my another original AC solution without help
 * 
 * This problem limit the potential candidate among 1-9, so it is easier for the program to get the result
 * I don't think this problem has much variation than Combination Sum 1(p39) and Combination Sum 2(p40)
 * Also I don't find any much better solutions from others
 * During each recursion, we check the last number in the temp list, we will start our following recursions after this number.
 * We then do a for loop from last number to 9, to see if any number will give us the result
 * we will return if we found the goal list or we found our sum or k has exceeded the limit.
 * 
 * Remark:
 * 1) Here we use the trick that let n-- and k-- in each recursion, if n==0 and k ==0 then we are reaching the goal list
 * 2) The tricky part is maybe converting the recursive solution to iterative solution
 * 
 * Sol2 is my iterative solution
 * Sol3 is the python version of sol1
 * Sol4 is the python version of sol2
 * Sol5 is the modified iterative python version that using tuple to replace inner class
 * 
 * @author hpPlayer
 * @date Aug 23, 2015 2:52:41 PM
 */
public class p216_sol1 {
	public static void main(String[] args){
		for (List<Integer> list : new p216_sol1().combinationSum3(3, 7)){
			System.out.println(list);
		}
	}
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> temp = new ArrayList<Integer>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        DFS(n, k, result, temp);
        return result;
    }
    //use -- to avoid the use of extra variables
    public void DFS(int n, int k, List<List<Integer>> result, List<Integer> temp){
        if(n < 0 || k < 0) return;
        if(n == 0 && k ==0){
        	List<Integer> temp2 = new ArrayList<Integer>();
        	temp2.addAll(temp);
            result.add(temp2);
            return;
        }
        
        int end = temp.isEmpty()? 0 : temp.get(temp.size()-1);
        for(int i = end + 1; i <= 9; i++){
            temp.add(i);
            DFS(n-i, k-1, result, temp);
            temp.remove(temp.size()-1);
        }
    }
}
