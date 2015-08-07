/**
 * This is shorter and more clean solution
 * The key idea is breaking the loop
 * Since if we don't pick n, that means we are free to pick n-1 and n + 1, like it becomes a single array without loop
 * But also we need consider the case that include picking n, so we can break the loop in other places
 * However, we want include the breakpoint in previous part in this second pass, so we must choose consecutive breakpoint that 
 * we are guaranteed include breakpoint in first pass into our second pass
 * 
 * In following code, we first rewrite the method in p198 to add the search range
 * Then we simply break the circle in 0 and n-1, so that we have two breakpoints and cover all cases
 * 
 * Remark:
 * Since our approach is based on the assumption that we at least have two houses (two breakpoints), so we need to check the length
 * of nums to see if it at least has two houses. If not just return the result directly
 * This solution is better than mine, best solution!
 * @author hpPlayer
 * @date Aug 6, 2015 11:14:35 PM
 */
public class p213_sol2 {
	
	//robInRange is modified from p198, just add start and end index
    public int robInRange(int start, int end, int[] nums){
        int prevYes = 0, prevNo = 0;
        for(int i = start; i <= end; i++){
            int temp = prevYes;
            prevYes = prevNo + nums[i];
            prevNo = Math.max(temp, prevNo);
        }
        return Math.max(prevYes, prevNo);
    }
    //break the circle either on 0 or nums.length - 1
    public int rob(int[] nums) {
        if(nums.length == 1) return nums[0];//our method will not work on single house
        return Math.max(robInRange(0, nums.length - 2, nums), robInRange(1, nums.length -1, nums));
    }
}
