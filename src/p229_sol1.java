import java.util.ArrayList;
import java.util.List;
/*
Majority Element II 

Given an integer array of size n, find all elements that appear more than floor(n/3) times. The algorithm should run in linear time and in O(1) space.

Hint:

How many majority elements could it possibly have?
Do you have a better hint? Suggest it!
*/

/**
 * This is not my original algorithm. The algorithm is modified Boyer¨CMoore majority vote algorithm
 * This algorithms runs in O(n) and use O(1) space complexity
 * How it works?
 * We will sweep down the sequence from the beginning. As we sweep we maintain a pair consisting of a current candidate and a counter
 * Initially the current candidate is unknown(0, in our case) and the counter is 0
 * 
 * When we move the pointer forward over an element e:
 * if the counter is 0, we set candidate to e and we set the counter to 1
 * if the counter is not 0, we increment or decrement the counter according to whether e is current candidate
 * 
 * When we are done, the current candidate is the majority element, if there is a majority
 * 
 * In this problem, the problems states us find elements that appear more than floor(n/3), more than means we at most will have 2 candidates
 * So, in our problem, we have two candidates and two counters. After applying vote algorithm we will find two majorities, but we would not 
 * sure about whether each of them appears more than floor(n/3) tims, so we need check  them
 * 
 * More details can be found below.
 * 
 * I believe this algorithm is the only way to solve the problem with O(n) time and O(1) space
 * two important keys:
 * 1) there will only be two candidates as we require each of them appear more than n/3 times
 * 2) the application of Boyer¨CMoore majority vote algorithm
 * 3) From the algorithm, we only get two majorities, we need rescan the array to check the occurrences
 * Sol2 is the Python version of sol1
 * @author hpPlayer
 * @date Aug 14, 2015 10:01:54 PM
 */

public class p229_sol1 {
    public static void main(String[] args){
    	p229_sol1 test = new p229_sol1();
    	int nums[] = {1,2,3,4,5,5,5,5,5,5,1,2,3,4,6,6,6,6,6,6};
    	for(Integer i : test.majorityElement(nums)){
    		System.out.println(i);
    	}
    }
	public List<Integer> majorityElement(int[] nums) {
        int candidateA = 0, candidateB = 0;
        int counterA = 0, counterB = 0;
        /*
         * this is my origial code, since I mixed the check of A and B, i have to add candidateA != num during the check
        if (counterA == 0){
            candidateA = num;
            counterA ++;
        }else if (counterB == 0 && candidateA != num){
            candidateB = num;
            counterB ++;
        }else if(num == candidateA){
        	counterA++;
        }else if (num == candidateB){
        	counterB++;
        }else{
        	counterA--;
            counterB--;
        }
        */
        //below is revised code, we check A then B, so it makes the code more clean
        for(int num : nums){
            if (counterA == 0){
                candidateA = num;
                counterA ++;
            }else if(num == candidateA){//follow this order, we can avoid check candidateA != num
            	counterA++;
            }else if (counterB == 0){
                candidateB = num;
                counterB ++;
            }else if (num == candidateB){
            	counterB++;
            }else{//to make fair competition between candidateA and candidateB, we have to decrease them same time
            	counterA--;
                counterB--;
            }
        }
        
        List<Integer> result = new ArrayList<Integer>();
        int filter = nums.length/3;
       // System.out.println(filter);
        //System.out.println(candidateA);
        //System.out.println(candidateB);
        counterA = 0;
        counterB = 0;
        for(int i : nums){
            if(i == candidateA){
                counterA ++;
            }else if(i == candidateB){//else if here will help us get rid of adding same number to the result twice
                counterB ++;
            }
        }
       // System.out.println(candidateA);
        //System.out.println(candidateB);
        if(counterA > filter){
            result.add(candidateA);
        }
        if(counterB > filter){
            result.add(candidateB);
        }
        
        return result;
    }
}
