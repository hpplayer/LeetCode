/**
 * My one-pass AC solution
 * 
 * This problem is actually the down-grade version of Jump Game II (p045)
 * I used the similar algorithm as used in p045
 * We search the array by sections.
 * The range of next section is determined by the values in current section.
 * In other words, the right bound of next section is the largest value in current section
 * The left bound of next section is the next cell after the right bound of current section
 * 
 * Our algorithm runs in O(n) time and costs O(1) space
 * 
 * Remark:
 * Actually, we can simplify the solution because we don't need to count jumps
 * 
 * 1) We don't need to count the numbers of jump, so we can only care about the right bound the search range
 * 2) if at some points, our index i has passed right bound, that means current index i is not reachable
 * One optimization for my algorithm is that we can report FALSE, if we found 
 * 3) canJump2() lists such simplified version
 * @author hpPlayer
 * @date Sep 2, 2015 5:55:14 PM
 */
public class p055_sol1 {
    public boolean canJump(int[] nums) {
        int pointer = 0, rightBound = 0;
        for(int i = 0; i < nums.length; i++){
        	if(i > rightBound) return false;//stop the loop, if rightBound stop increase
            pointer = Math.max(pointer, i + nums[i]);
            if(rightBound == i){
                rightBound = pointer;
            }
        }
        
        return rightBound >= (nums.length - 1);
    }
    
    public boolean canJump2(int[] nums) {
        int rightBound = 0;
        for(int i = 0; i < nums.length; i++){
            if(i > rightBound) return false;//if current i has passed the rightBound
            rightBound = Math.max(rightBound, i + nums[i]);
        }
        
        return rightBound >= (nums.length - 1);//actually, we can just return true because last index i is still <= rightBound
    }
}
