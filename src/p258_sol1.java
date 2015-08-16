/*
Add Digits 

Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime?

Hint:

A naive implementation of the above process is trivial. Could you come up with other methods?
What are all the possible results?
How do they occur, periodically or randomly?
*/

/**
 * This is actually a math problem, but I can solve in the naive way
 * sol1 is using recursion while sol2 is using iteration
 * In sol1, during each recursion, I just convert current integer into String
 * then sum them up to get the new int, then pass new int to next recursion
 * 
 * sol1 is recursive solution
 * sol2 is iterative solution
 * sol3 is python version of sol1
 * sol4 is python version of sol2
 * 
 * Math solution will be updated later, stay tuned!
 * @author hpPlayer
 * @date Aug 16, 2015 12:16:25 AM
 */
		
public class p258_sol1 {
    public int addDigits(int num) {
        if(num <= 0) return num;
        if ((num + "").length() == 1){
            return num;
        }
        int sum = 0;
        String s = num +"";
        for(int i = 0; i < s.length(); i++){
            sum += s.charAt(i) - '0';
        }
        
        return addDigits(sum);
    }
}
