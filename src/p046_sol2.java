import java.util.ArrayList;
/**
 * same version as sol1, but use a boolean[] to save spaces...(maybe)
 * @author hpPlayer
 * @date Mar 5, 2015 3:32:38 PM
 */

public class p046_sol2 {
	public static void main(String[] args) {
		int[] input = { 1, 2, 3 };
		ArrayList<ArrayList<Integer>> temp = permute(input);
		for (ArrayList<Integer> ary : temp) {
			System.out.println(ary);
		}

	}
	public static ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> Result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> currArray = new ArrayList<Integer>();
        boolean[] visited = new boolean[num.length];
       DFS(currArray, Result, num, visited);
       return Result;
   }
   
   public static void DFS(ArrayList<Integer> currArray, ArrayList<ArrayList<Integer>> Result, int[] dataInput, boolean[] visited){
       if(currArray.size() == dataInput.length){
           Result.add(new ArrayList<Integer>(currArray));
           return;
       }
       
       for(int i = 0; i < dataInput.length; i++){
       	if(visited[i] == false){
           	currArray.add(dataInput[i]);
           	visited[i] = true;
               DFS(currArray, Result, dataInput,visited);
               currArray.remove(currArray.size()-1);
               visited[i] = false;
       	}
       }
   }
}
