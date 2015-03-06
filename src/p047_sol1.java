import java.util.ArrayList;
import java.util.Arrays;
/**
 * similar problem to p46...
 * but after we build the tree for one of the duplicate, we just need to skip same elements and move to next new element(because those nodes
 * should give us same subtree which is redundant)...
 * 
 * Remark:
 * dont forget sort array, since this problem we have duplicates that may not close to each other and we need to gather them together
 * Also, dont forget the reset the boolean[i] = false, after we have done DFS on i and removed it from currentList
 * Other caution is I changed return type from list<list> to ArrayList<ArrayList<>>
 * if it is allowed we just need to change every "arraylist" to "list" except the initiation(i.e.
 *      List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> currArray = new ArrayList<Integer>();
        )
 *  
 * 
 * Damn..I used hashset first to check the redundant before adding to the result, but it gives TLE...
 * It should be..as we have wasted several loops to build redundant tree which we should skip
 */

public class p047_sol1 {
	public static void main(String[] args) {
		int[] input = {1,1,3,1};
		ArrayList<ArrayList<Integer>> temp = permuteUnique(input);
		int count = 0;
		for (ArrayList<Integer> ary : temp) {
			count ++;
			System.out.println(ary);
		}
		System.out.println(count);

	}
    public static ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        ArrayList<ArrayList<Integer>> Result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> currArray = new ArrayList<Integer>();
        boolean[] visited = new boolean[num.length];
        Arrays.sort(num);//dont forget sort array, since we need gather duplicate together!
       DFS(currArray, Result, num, visited);
       return Result;
   }
   
   public static void DFS(ArrayList<Integer> currArray, ArrayList<ArrayList<Integer>> Result, int[] dataInput, boolean[] visited){
       if(currArray.size() == dataInput.length){
           Result.add(new ArrayList<Integer>(currArray));
           return;
       }
       
       for(int i = 0; i < dataInput.length; i++){
    	 //if(i >= 1 && dataInput[i] == dataInput[i-1]) continue;//dont jump here, othersie we will skip all previous duplicate elements
       	if(visited[i] == false){
           	currArray.add(dataInput[i]);
           	visited[i] = true;
               DFS(currArray, Result, dataInput,visited);
               currArray.remove(currArray.size()-1);
               visited[i] = false;     
               while(i+1 < dataInput.length && dataInput[i] == dataInput[i+1]) i ++;//jump here, since here we have done DFS for currentNode,
               //we dont want to move our pointer to next dupliate node(which will give us same result
       	}
       }
   }
}
