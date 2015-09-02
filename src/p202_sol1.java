/*
Happy Number 

Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer,
replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), 
or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 19 is a happy number

12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1

*/

/**
 * This is my original AC solution without help
 * Actually, I am very lucky to get this solution
 * 
 * Happy number is a sequence of magic numbers that will end with repeat 1 
 * Why I am lucky? because initially i thought all numbers will coverage to a number between 1 and 9, but it is incorrect
 * All unhappy numbers in [1,9] will finally drop into a loop, which indicated by the experiment is 4, 16, 37, 58, 89, 145, 42, 20, 4.....
 * In fact, we only have two happy numbers in [1,9] that are 1 and 7.
 * 
 * The logic then become simple, we will form a loop anyway. With happy numbers, the loop will only contains 1. With unhappy numbers,
 * the loop will only contains 4, 16, 37, 58, 89, 145, 42, 20.
 * 
 * So we can use some methods to detect the loop. If the return value from loop is 1, then report happy number, if the return value 
 * is other value, then report unhappy number.
 * 
 * My solution below uses the trick that stop the loop when the value in loop is smaller than 10, if it is 1 or 7, then report true. if the value 
 * is other values < 10, then report false
 * 
 * Use the same logic, we can write isHappy2() below, which check if the loop contains 1 or 4
 * 
 * Of course, here we assume that we know happy number 1 and 7, and unhappy number 4
 * 
 * Sol2 assumes that we don't know any happy number other than 1, and don't know any unhappy number
 * But we assume we know the unhappy number will finally fall into a loop
 * 
 * Sol1 here provides a recursive solution with knowing values in loop
 * Sol2 provides an iterative solution with fast and slow pointers
 * Sol3 provides an iterative solution with HashSet to record visited integers thus found the loop
 * 
 * Considering the easiness to memory the algorithm, sol2 is more recommended since it only requires you to remember all numbers fall into a 
 * loop, and happy number's loop only contains 1. Also, compared with sol3, it does not require extra space (maybe consume stack when doing 
 * recursive calls)
 * 
 * 
 * Update:
 * The problem has stated that "repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1".
 * So we are given the condition that each number must finally fall into loops
 *
 * @author hpPlayer
 * @date Sep 1, 2015 8:51:31 PM
 */
public class p202_sol1 {
    public boolean isHappy(int n) {
        if(n == 1 || n == 7) return true;
        if(n < 10) return false;
        
        int sum = 0;
        while(n > 0){
            sum += (int) Math.pow(n%10, 2);
            n /= 10;
        }
        return isHappy(sum);
    }
    
    public boolean isHappy2(int n) {
        if(n == 1) return true; //1 is included in happy number loop
        if(n == 4) return false; //4 is included in unhappy number loop
        
        int sum = 0;
        while(n > 0){
            sum += (int) Math.pow(n%10, 2);//get last digit, then power it by 2, then add to sum
            n /= 10;//we have checked last digit, right shift input n
        }
        return isHappy(sum);
    }
}
