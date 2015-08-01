/**
 * This is called Narayana Pandita algorithm
 * It works in following way(copied from wiki):
 
The following algorithm generates the next permutation lexicographically after a given permutation. It changes the given permutation in-place.

Find the largest index k such that a[k] < a[k + 1]. If no such index exists, the permutation is the last permutation.
Find the largest index l greater than k such that a[k] < a[l].
Swap the value of a[k] with that of a[l].
Reverse the sequence from a[k + 1] up to and including the final element a[n].

For example, given the sequence [1, 2, 3, 4] which starts in a weakly increasing order,
and given that the index is zero-based, the steps are as follows:

Index k = 2, because 3 is placed at an index that satisfies condition of being the largest index that is still less than a[k + 1] which is 4.
Index l = 3, because 4 is the only value in the sequence that is greater than 3 in order to satisfy the condition a[k] < a[l].
The values of a[2] and a[3] are swapped to form the new sequence [1,2,4,3].
The sequence after k-index a[2] to the final element is reversed. Because only one value lies after this index (the 3),
the sequence remains unchanged in this instance. Thus the lexicographic successor of the initial state is permuted: [1,2,4,3].
 * @author hpPlayer
 * @date Jul 31, 2015 11:46:24 PM
 */
public class p031_sol2 {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int i = 0;
        //looking for first pair that not in decreasing order counting from tail£¬i-1 will be the bit that needs to be replaced in next pair
        for (i = nums.length -1; i>= 1; i--){
            if (nums[i] > nums[i-1]){
                break;
            }
        }
        
        if (i > 0){//in case the array only has length 2
        swap(nums, i-1);
        }
        
        reverse(nums, i);
        
    }
    
    //Since nums after i-1 will be in decreasing order, the first num we found here will be the smallest number that can be used to replace i-1
    public void swap(int[] nums, int i){
        for(int j = nums.length - 1; j > i; j--){
            if(nums[j] > nums[i]){
                nums[j] += nums[i];
                nums[i] = nums[j] - nums[i];
                nums[j] -= nums[i];
                break;
            }
        }
    }
    
    public void reverse(int[] nums, int i){
        int last = nums.length - 1;
        int first = i;
        while (first < last){//this in-place replacement could not handle case if two index are same. Example: [0, 1], first = last = 1, then it will go worng
            nums[first] += nums[last];
            nums[last] = nums[first] - nums[last];
            nums[first] -= nums[last];
            first ++;
            last --;
        }
    }
    
}
