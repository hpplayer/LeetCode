import java.util.HashSet;

/* Consecutive Sequence 

Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
*/


/**
 * There are two important keys to solve this problem.
 * The first one is using hashMap to search the keys, which I have figured out 
 * The second one is how to smartly use the HashMap, which I still need improvement
 * Below is a very smart algorithm to solve the problem.
 * It works in following way:
 * 1) convert the input array into a HashSet
 * 2) search each value in the array with the help of the hashset
 * 3) If current value's -1 value is not in the Hashset, we will start our search
 * Here the condition check num - 1 is very important since it will help us skip previous searches
 * and increase the speed
 * 4) we will begin search num + 1, if it is in hashset then continue search num + 2.
 * After the search stop, it means we have reached the consecutive element that not in the hashset,
 * so we can simply get the length of search by current Value - num, and update the result if necessary
 * 
 * Remark:
 * When I look the code, I though I could speed up the search by indexing, and jumping the index to
 * the value that not in array, but I am wrong. That because we are searching based on values, that means
 * the value we got from search is the consecutive value not the index, so we cannot move index pointer
 * based on returned value. We have to do search in each element of array. Fortunately, our check 
 * condition if(!set.contains(m)) will help us skip previous searches.
 * 
 * The time complexity:
 * Since we do search in each element, but we will start new search only if num - 1 not in hashset
 * so we will not repeat searches, the results of which is O(n) time complexity
 * 
 * sol2 is another HashMap solution, and the running time should be O(n) but actually it includes 
 * many duplicate searches, so it is not as fast as sol1
 * sol3 is python version of sol1
 * sol4 is python version of sol2
 * 
 * Sol1 is the best solution
 * Short and fast!!!
 * @author hpPlayer
 * @date Aug 15, 2015 11:01:28 PM
 */
public class p128_sol1 {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        for(int num : nums){
            set.add(num);
        }
        int max = 0;
        for(int num: nums){
            if(!set.contains(num - 1)){
                int m = num + 1;
                while(set.contains(m)){
                    m ++;
                }
                max = Math.max(m-num, max);
            }
        }
        
        return max;
    }
}
