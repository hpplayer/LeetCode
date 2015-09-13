import java.util.*;

/**
 * A different solution to this problem.
 * The basic idea is same, i.e. find the j and j -1 values from previous row, then sum up to get the value in j in current row 
 * But we use array.set() and array.size() to update the result. The 1 in first layer will always be the last 1 in each layer, while
 * we add a new 1 in each next layers. We use same row to query and update values, but still have to declare a list when inserting into result 
 * list.
 * 
 * @author hpPlayer
 * @date Sep 12, 2015 7:55:49 PM
 */
public class p118_sol2 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(numRows == 0) return result;
        
        ArrayList<Integer> ary = new ArrayList<Integer>();
        for(int i = 0; i < numRows; i++){
            ary.add(0,1);//always attach 1 in front
            for(int j = 1; j < ary.size()-1; j++){
                //ary.size() -1 is the 1 we attached in firsy layer, so don't touch it
                //we have inserted the 0 in front, so current ary index should be rightshift by 1 by prev index
                //so we should add the j-1 and j indexed value from previous row, now it becomes j and j+1
                ary.set(j, ary.get(j) + ary.get(j+1));
            }
            
            //we have to create a new list, so later operations will not affect previous stored list
            result.add(new ArrayList<Integer>(ary));
        }
        
        return result;
    }
}
