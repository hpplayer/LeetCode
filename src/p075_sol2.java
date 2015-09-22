/**
 * This solution is a one pass solution
 * The logic is not hard. We keep two variables to record the end of zeros ("zero") and the one start of twos ("two")
 * "zero" is the index where we can insert new zero, while "two" is the index where we can insert new two
 * We won't change any value before zero and after two.
 * 
 * Then we go to the loop.
 * If current value is 1, we just leave it there, since after we insert all 0 and 2 into proper positions, 1 will automatically sorted
 * If current value is 0, then we swap current cell with the cell indexed by "zero", then let zero ++
 * If current value is 2, then we swap current cell with the cell indexed by "two". Since we don't know what does "two" points to, it may 
 * points to 1 which we can simply skip, or maybe it points to 0, which we have to put into the proper position, so in addition to two --
 * we also let i--, so that we can check the updated num[i] in next loop. Maybe here comes a question, why we don't need to check the updated
 * current value when current value is 0? That's because we have visited the cell "zero" points to, so we guarantee it is ordered already.
 * While "two" points to a cell we have not visited yet, so we need to check it
 * 
 * @author hpPlayer
 * @date Sep 7, 2015 4:38:54 PM
 */
public class p075_sol2 {
    public void swap(int x, int y, int nums[]){
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
    public void sortColors(int[] nums) {
        //place to insert zero and place to insert two
    	//0 is the first place to insert 0, nums.length - 1 is the last place to insert 2
        int zero = 0, two = nums.length - 1;
        //we just need to care about values before two, values after two are all sorted 2
        //two is the index before first 2, so we need visit it 
        //This is like two-pointer problem, start pointer is i, end pointer is two
        //our end condition is start pointer <= end pointer, here we have another pointer points to 0, so we need to check the
        //last number as well
        for(int i = 0; i <= two; i++){
            if(nums[i] == 0){
                swap(zero, i, nums); //we don't need recheck nums[i] since new nums[i] is updated to a cell we have been visited before
                zero++;
            }else if (nums[i] == 2){
                swap(two, i, nums);
                i--;//check updated nums[i], very important !!!!! we need recheck nums[i] since now it has been updated to an unvisited cell
                two--;//update new index to insert twos
            }
        }
    }
}
