/**
 * Before discussing this solution.
 * We firstly need to know an algorithm called Brian Kernighan¡¯s Algorithm that can remove rightmost 1 in the given input
 * The main part of algorithm is very simple:
 * n = n & (n-1)
 * Example:
 * we have n = 111
 * Run first time:
 * 111 & 110 = 110 
 * Run second time:
 * 110 & 101 = 100
 * Run third time:
 * 100 & 011 = 000
 * Each execution of Brian Kernighan¡¯s Algorithm will remove one 1 from right.
 * 
 * Sol2 has a very short code, but is not easy to understand.
 * Actually, so2's idea is very similar to sol1, i.e found the leftmost common part
 * 
 * As described in sol1, if m != n, then we must have 0 in index 1, then the result will also have 0 in index i.
 * So, when n > m, we can safely let all 1s in postfix part become 0, this is like in sol1, we right shift bit then add postfix back to form 0s
 * 
 * We let this loop running until n <= m
 * If n == m, that means we found the leftmost common part, return n will give the result that has perfect combination of prefix + postfix
 * Now m and n should exactly have prefix + all zeros in postfix
 * 
 * if n < m, what should we get? since in last loop we still have n > m, now after removing rightmost 1, we have n < m
 * As described before, we are actually looking for the leftmost common part, which should appeared in all numbers in the range
 * So now our n should be the prefix + postfix, while m maybe is prefix + some 1 in postfix. That's why we share same prefix but n is still 
 * smaller than m
 * 
 * 
 * @author hpPlayer
 * @date Sep 2, 2015 12:37:56 AM
 */
public class p201_sol2 {
    public int rangeBitwiseAnd(int m, int n) {
        while (n > m){
            n &= n-1;
        }
        //if m == n, then m and n should be like prefix + all zeros
        //if n < m, then n is still prefix + all zeros, but m should be like prefix + (some zeros and some ones)
        return n;
    }
}
