import java.util.*;

/*
Pascal's Triangle

Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/	
		
/**
 * This is my original AC solution without help
 * The difficulty of this problem is indexing. The input n is 1 based while the index in our list is 0 based
 * Besides, the start 1 will be added each layer while the end 1 will be added only after layer num > 1
 * 
 * The main program is to look up the upper layer and find values in index j and index j -1, sum them up and we will get our current value in j
 * 
 * The idea is same, but we can implement in different way, see sol2
 * 
 * Comparing sol1 and sol2, I still like my solution since it is more intuitive and clear
 * 
 * @author hpPlayer
 * @date Sep 12, 2015 7:35:21 PM
 */

public class p118_sol1 {
	public static void main(String[] args){
		for(List<Integer> lst : new p118_sol1().generate(5)){
			System.out.println(lst);
		}
	}
	
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(numRows == 0) return result;
        
        for(int i = 1; i <= numRows; i++){
            List<Integer> list = new ArrayList<Integer>();
            list.add(1);//head is 1
            
            for(int j = 1; j < i-1; j++){
                list.add(result.get(i-2).get(j) + result.get(i-2).get(j-1));
            }
            
            if(i > 1) list.add(1);
            result.add(list);
        }
        
        return result;
    }
}
