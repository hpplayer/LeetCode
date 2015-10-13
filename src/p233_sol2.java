/**
 * This solution is short but not easy to understand
 * Basically, it will scan the input n bit by bit, and decide how many 1 will be in this bit
 * EX1: 123456
 * we look at hundred bit, which is 4 now
 * we have 123 before it, so we can guarantee there will be 123 times 1 in hundred bit
 * we also have 4 in hundred bit, so there will be 123 + 1 = 124 times 1 in hundred bit
 * Notice: what we talk above is the streak, each streak has 100 times 1 in hundred bit, its like 100->101->...->199, 100 times 1 in hundred
 * So there would be 124 times streak in hundred bit, each streak contains 100 times 1 in hundred bit, can be written as 100*124
 * 
 * we look at tenth bit, which is 5 now
 * we have 1234 before it, so we can guarantee there will be 1234 times 1 in tenth bit
 * we also have 5 in tenth bit, so there will be 1234 + 1 = 1235 times 1 in tenth bit
 * Notice: what we talk above is the streak, each streak has 10 times 1 in tenth bit, its like 10->11->...->19, 10 times 1 in tenth
 * So there would be 1235 times streak in tenth bit, each streak contains 10 times 1 in tenth bit, can be written as 10*1235
 * 
 * EX2: 123156, we look at hundred bit, which is 1 now
 * we have 123 before it, so we can guarantee there will be 123 times 1 in hundred bit
 * but we have 1 in hundred bit, so this time we cannot plus 1 steak to the count, since 156 is not a full streak, we only have 57 
 * numbers(including 100) in "last streak", can be written as 123*100 + 57
 * 
 * EX3: 123056, we look at hundred bit, which is 0 now
 * we have 123 before it, so we can guarantee there will be 123 times 1 in hundred bit
 * but we have 0 in hundred bit, so this time we only have 123 streak in it, can be written as 123 * 100
 * 
 * In summary, each time we scan one bit, and decide how many 1s will appear in this bit, either based on bits before it, or after it(if not full streak)
 * This solution uses a tricky to tell the value in current bit is >= 2 or <2
 * n/k get current bit, +8 if it is >= 2, we will include a full streak, if it is <= 1, we will leave front part as it is, *k is streak size
 * for not-full streak case, we check if current bit's value is 1, if it is add bits after it to result, else + 0, since we have +1/+0 in previous
 * part
 * 
 * So this is the short solution towards this problem
 * @author hpPlayer
 * @date Aug 13, 2015 10:14:22 PM
 */
public class p233_sol2 {
    public int countDigitOne(int n) {
        int result = 0;
        //k must be long, since if n is Integer.MAX_VALUE, k*10 may get overflow
        for(long k = 1; k <= n; k*= 10){
        	/*
        	 * n/k will help get us all digits before target digits, +8 will test if the last digit is >= 2
        	 * if it is, we are safe to add 1 from last digit, otherwise add 0
        	 * n/k%10 will help get digit, if it is 1, then we need add values after that which can be got from n%k+1(include 1 in itself)
        	 * else do nothing (>=2 + 1*k which has been done before, =0 + 0, which has also been done before
        	 * 
        	 */
        	
        	result += (n/k + 8)/10 * k + (n/k%10 == 1? n%k+1 : 0);
        }
        
        return result;
    }
}
