/**
 * This is my original AC solution without help
 * I just finished p220, which uses the modified Moore voting algorithm to solve the majority problem
 * So the problem becomes a piece of cake, as we can solve it with original Moore voting algorithm
 * The idea is simple, let me copy the explanation in sol1
 * 
 * We will sweep down the sequence from the beginning. As we sweep we maintain a pair consisting of a current candidate and a counter
 * Initially the current candidate is unknown(0, in our case) and the counter is 0
 * 
 * When we move the pointer forward over an element e:
 * if the counter is 0, we set candidate to e and we set the counter to 1
 * if the counter is not 0, we increment or decrement the counter according to whether e is current candidate
 * 
 * When we are done, the current candidate is the majority element, if there is a majority
 * 
 * A minor improvement:
 * return candidate as long as our counter has > nums.length/2
 * 
 * Runtime: O(n) 
 * 
 * Remark:
 * there are many ways to solve the problem, like using hash, sorting, divide and conquer, vote algorithm, bit manipulation, etc.
 * I think hashing and sorting is too easy, so I do not show them in my solution
 * 
 * sol2 is bit manipulation approach
 * sol3 is divide and conquer approach
 * sol4 is python version of sol1
 * sol5 is python version of sol2
 * sol6 is python version of sol3
 * @author hpPlayer
 * @date Aug 14, 2015 10:49:34 PM
 */
public class p169_sol1 {
    public int majorityElement(int[] nums) {
        int candidate = 0, counter = 0;
        for (int num : nums){
            if (counter == 0){
                counter ++;
                candidate = num;
            }else if(num == candidate){
                counter++;
                if(counter > nums.length/2) return candidate;
            }else{
                counter --;
            }
        }
        
        return candidate;
    }
}
