import java.util.HashSet;
/**
 * HashSet version
 * Simple and elegant
 * 
 * containsDuplicate() is my version
 * containsDuplicate2() is other's version, which replace if-else with one sentence hs.add().
 * Actually, python version would be more simple.
 * @author hpPlayer
 * @date Aug 7, 2015 7:45:07 PM
 */

public class p217_sol2 {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> hs = new HashSet<Integer>();
        for(int i : nums){
            if (hs.contains(i)){
                return true;
            }else{
                hs.add(i);
            }
        }
        
        return false;
    }
    
    public boolean containsDuplicate2(int[] nums) {
        HashSet<Integer> hs = new HashSet<Integer>();
        for(int i : nums){
            if (!hs.add(i)) return true;
        }
        
        return false;
    }
}
