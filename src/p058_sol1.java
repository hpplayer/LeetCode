/*
Length of Last Word 

Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

For example, 
Given s = "Hello World",
return 5.
*/


/**
 * This is my original AC solution without help
 * I just use split() to split the string into a string[], then read the string in last cell and return its length;
 * 
 * if we are using this method, then this problem become meaningless. I believe the problem has a more clever solution
 * 
 * Sol2 provides a such great solution that without build-in solution and don'need to visit whole string
 * 
 * Remark:
 * s.trim() only return a new string without head and tail spaces
 * So, if we want remove heading and tailing spaces in s, we have to use s = s.trim()
 * 
 * Sol1 is good but without much technique
 * Sol2 is better since it does not use build-in functions and is theoretically much faster
 * I believe there should be no more faster algorithm than sol2, and it is till very short and elegant
 * 
 * So sol2 is recommended
 * @author hpPlayer
 * @date Sep 3, 2015 7:48:59 PM
 */
public class p058_sol1 {
	public static void main(String[] args){
		String s = "a  b";
		System.out.println(new p058_sol1().lengthOfLastWord(s));
	}
    public int lengthOfLastWord(String s) {
    	s = s.trim();//remember, s.trim() will not modify string s itself
    	if(s.length() == 0) return 0;
        String[] str= s.split(" ");
        return str[str.length - 1].length();
    }
}
