/*
Excel Sheet Column Title 

Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
 */

/**
 * Ok, this problem drove me crazy. I have been struggled for hours and still couldn't make it. It is even an easy problem!
 * In my first thought, I think it is a 26 hex number conversion, but then I found the problem is not so naive.
 * Let's compare the number here with 10 hex number
 * In 10 hex, we have 
 * XYZ = x * 10^2 + y * 10^1 + z * 10^0
 * we select the boundary case in 10 hex
 * 9 = 	0 * 10 ^ 1 + 9 * 10^0 = 9
 * 10 = 1 * 10 ^ 1 + 0 * 10 ^0 = 10, the 0 before 10^0 comes from (10 - 1 * 10 ^ 1)
 * 11 = 1 * 10 ^ 1 + 1 * 10 ^ 0 = 11, the 1 before 10^0 comes from (11 - 1 * 10 ^ 1)
 * In 26 hex, we have:
 * XYZ = x * 26^2 + y * 26^1 + z * 26^0
 * we select the boundary case in 10 hex
 * 25 = 0 * 26^1 + 25 * 26 ^ 0 = Y 
 * 26 = 1 * 26^1 + 0 * 26 ^ 0 = Z <--the transition value is contained in last layer
 * 27 = 1 * 26^1 + 1 * 26 ^ 0 =  AA
 * Now we found a very important observation. 
 * We found that the max value in each range can go up as high as 26, unlike 10 hex, the maximum value in range is 9
 * Accordingly, unlike 9 to 11, in which, we treat 10 as the new start of next range, here we still treat 26 as the last value in last range
 * So, the transition value 26 is actually contained in the last value of each layer
 * In our calculation, if the result value is transition value like 1 * 26^1 + 0 * 26 ^ 0, we have to set it to the value of last layer
 *  
 * 9 can be compared to 25 (Y)
 * 10 can be compared to 26 (Z)
 * 11 can be compared to 27 (AA)
 * 
 * So when get the value as the multiplies of 26, we have to manually take care of it. Set its char to "Z", and take 1 from it, so that 
 * the remain range behaves as normal from 1-25, which is similar to 1 - 9
 * 
 * In short words, we have to be careful with corner case multiplies of 26
 * 
 * Sol1 is a long but easy-understanding solution
 * Sol2 is a short but hard-understanding solution using recursion
 * Sol3 is the iterative solution of sol2
 * 
 * Sol1 is more recommended
 * @author hpPlayer
 * @date Sep 7, 2015 9:32:43 PM
 */
public class p168_sol1 {
	public static void main(String[] args){
		int n = 1;
		System.out.println(new p168_sol1().convertToTitle2(n));
	}
	
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while(n > 0){
            char c = 'A';
            int i = n%26;
            if(i == 0){//manually set the boundary range
                c = 'Z';
                n = n -1;//take one from 1 to let the range behaves normal
            }else{
                c = (char) ('A' + (n-1)%26);//(n-1)%26 to convert range 1-25 to range 0-24
            }
            sb.insert(0, c);
            n = n/26;
        }
        
        return sb.toString();
    }
    
    public String convertToTitle2(int n) {
        StringBuilder sb = new StringBuilder();
        while(n > 0){
            if ( (n%26) == 0){
                sb.insert(0, 'Z');
                n = n -1;
            }else{
                char c = (char) ((n-1)%26 + 'A');
                sb.insert(0, sb.insert(0, c));
            }
            
            n = n/26;
        }
        
        return sb.toString();
    }
}
