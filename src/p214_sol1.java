/**
 * This is an amazing solution. It is short but powerful.
 * The baisc idea is:
 * 1) we are using two pointers to scan the string, one from left and the other one from right
 * 2) right pointer moves one step forward in each loop and left pointer moves when we found chars pondered by two pointers are matched
 * 3) After the scan, the string will be split by the left pointer into two parts. left part may be a palindrome or not, but right part 
 * must not be a palindrome, and needs us insert the reverse of right part in front to build a palindrome.
 * 4) Since we don't know if the left part is palindrome, we have to do recursive call on this left part to continue check.
 * 5) If our left pointer reach the end after the scan, that means our current string is a palindrome, we can stop the recursive call and return
 * current string
 * 6) The average time complexity is around O(logN) since we always cut the string into two halves, and discard the right part.
 * 
 * Remark:
 * 1) It is too hard to translate this recursive solution to iterative solution as it increases inserting substring in middle of result string
 * and mark the left pointer, etc. So if we need a method avoid overflow, sol2 KMP solution is recommended
 * 
 * Sol1 is recursive solution with two pointers
 * Sol2 is KMP solution
 * Sol3 is the Python version of sol1
 * Sol4 is the Python version of sol2
 * 
 * Both sol1 and sol2 solutions are brilliant and both are recommended
 * @author hpPlayer
 * @date Aug 25, 2015 3:53:34 PM
 */
public class p214_sol1 {
	public static void main(String[] args){
		System.out.println(new p214_sol1().shortestPalindrome("abad"));
	}
	
    public String shortestPalindrome(String s) {
        int left = 0, right = s.length() -1;
        for(; right >= 0; right--){
            if(s.charAt(left) == s.charAt(right)){
                left ++;
            }
        }
        //if current string is palindrome, then left will now points to the index out of the string's index
        //like s = "a", after the for loop, left pointer will point to index 1
        if(left == s.length()){//palindrome
            return s;
        }
        
        return new StringBuilder(s.substring(left)).reverse().toString() + shortestPalindrome(s.substring(0, left)) + s.substring(left);
    }
    
}
