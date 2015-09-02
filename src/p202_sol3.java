import java.util.HashSet;
/**
 * Similar idea as sol2, but here we use HashSet to contain visited integers 
 * So if we find a number already in Set, then we must get into a loop
 * 
 * But compared with sol2, it uses extra space to store values, and some values may not important to us(like all numbers not 
 * in 4, 16, 37, 58, 89, 145, 42, 20, and 1)
 * @author hpPlayer
 * @date Sep 1, 2015 9:32:24 PM
 */

public class p202_sol3 {
    public boolean isHappy(int n) {
        if (n == 1) return true;
        HashSet<Integer> visited = new HashSet<Integer>();
        visited.add(n);//initial case
        
        while (n != 1){
            n = helper(n);
            if(visited.contains(n)){
                return false;//falls into a loop, we will never face a visited 1 in the set
            }
            visited.add(n);
        }
        return true;
    }
    
    public int helper(int n){
        int sum = 0;
        while(n > 0){
            sum += (int) Math.pow(n%10, 2);
            n /= 10;
        }
        
        return sum;
    }
}
