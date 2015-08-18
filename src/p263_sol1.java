/**
 * My original AC solution without help
 * The basic idea is that our result will only have prime factors of 1,2,3,5
 * So we will try to divide those numbers if one of the number's prime factors is among them
 * If we reach the tail of recursion, where we cannot further divide the number, we will check if the remainder is among those numbers too
 * Example: 
 * 6, can be divided by 2, remainder is 3, 3 is our prime factor return true
 * 8, can be divided by 2, remainder is 4, 4 can be further divided by 2, the remainder 2 is our prime factor
 * 14 can be divided by 2, remainder is 7, 7 cannot be futher divided and it is not our prime number, thus return false
 * The main idea is that our final remainder should among 1,2,3,5, if the num get other numbers it means it is not an ugly number
 * Ex: final remainder of 6 is 2 and 3, final remainder of 8 is 2, final remainder of 14 is 1 and 14, so 14 is not an ugly number
 * 
 * sol2 is my iterative solution
 * 
 * @author hpPlayer
 * @date Aug 18, 2015 2:37:06 PM
 */
public class p263_sol1 {
    public boolean isUgly(int num) {
        if(num <= 0) return false;
        if(num == 1 || num == 2 || num == 3 || num == 5) return true;
        if(num%2 == 0){
            if(isUgly(num/2)) return true;
        }
        if(num%3 == 0){
            if(isUgly(num/3)) return true;
        }
        if(num%5 == 0){
            if(isUgly(num/5)) return true;
        }
        return false;
    }
}
