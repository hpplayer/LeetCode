import java.util.*;

/*
Pascal's Triangle II

Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?
*/	
		
/**
 * This is my original AC solution without help
 * Ok I admit the sol2 from p118 does have some help.
 * It provides a way to use only O(k) spaces to get the target row
 * In each loop, we always insert a 1 in index 0. Then we will try to insert numbers between index 1 and index result.size() -1 (exclusive). Why we don't 
 * insert number on result.size() -1? Because we want reserve this spot to the 1 in last index. This means, after we insert 1 in row 0, we will always keep
 * it as the last 1 in each row. For elements between index 1 and index result.size() -1 (exclusive), we can easily calculate the value by summing index j
 * and index j+1, since we insert 1 in the beginning of each loop, our index has right shifted by 1 unit. Also, we only use O(k) space, we actually 
 * are updating the row, so here we use result.set(index, value) to update values
 * 
 * Remark:
 * 1) we still treat the row number as 1 based, which is consistent with p118, but the input rowIndex is 0 based, so we will let rowIndex ++ to convert it
 * to 1 based 
 * 
 * Sol2 is another way to expand and update the array only use O(k) space. Plus it does not insert 1 in front, thus it reduce the insertion of 1 from O(n)
 * to O(1)
 * 
 * So sol2 is more recommended, though sol1 is also a great solution
 * @author hpPlayer
 * @date Sep 12, 2015 11:02:45 PM
 */
public class p119_sol1 {
    public List<Integer> getRow(int rowIndex) {
        rowIndex = rowIndex + 1;
        List<Integer> result = new ArrayList<Integer>();
        if(rowIndex <= 0) return result;
        
        for(int i = 0; i < rowIndex; i++){
            result.add(0, 1);
            //last index is reserved for 1, which we insert in the first loop
            for(int j = 1; j < result.size() -1; j++){
                //we are supposed to find j -1 and j, but we have added 0 in front so index is rightshfited
                result.set(j, result.get(j) + result.get(j+1));
            }
        }
        
        return result;
    }
}
