/*
Integer to English Words

Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

For example,
123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Hint:

Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
Group the number by thousands (3 digits). You can write a helper function that takes a number less than 1000 and convert just that chunk to words.
There are many edge cases. What are some good test cases? Does your code work with input such as 0?
Or 1000010? (middle chunk is zero and should not be printed out)
*/		
		


/**
 * This is my original AC solution without help.
 * 
 * It is really annoying to solve this problem. Especially when dealing with 0s in mid
 * Basically, I split the input number of len 3, then I use my helper() to read this substring properly, 
 * Then I append the unit after substring properly. For mid 0s, I will check the return length of helper(), it is zero, then do nothing
 * just let loop continue. In other words, if the num%1000 = 0, it means we don't need to append any chars for this substring, which
 * is reflected by return substring'len == 0, in such case, we just let the loop pass this period and continue.
 * Besides, for input "0", it is a very special case, and we just need to return "zero", as we won't have zero in other cases
 * 
 * Remark:
 * There are several parts that can be optimized:
 * 1) we don't need to convert and pass substring, instead we can just use int/1000 or int%1000
 * 2) we can combine num < 20 together into one array
 * 3) we can use trim() to remove tailing spaces
 * 
 * Sol2 applies those optimizations
 * 
 * Sol1 is my original solution
 * Sol2 is a solution similar to my sol1 but applies several optimizations, so sol2 is recommended
 * @author hpPlayer
 * @date Sep 24, 2015 2:10:16 PM
 */
public class p273_sol1 {
	public static void main(String[] args){
		System.out.println(new p273_sol1().numberToWords(1000));
	}
	
    public String numberToWords(int num) {
    	if(num == 0) return "Zero";
    	String[] map4 = {"", " Thousand", " Million", " Billion", " Trillion"}; 
    	String result = "";
        int i = 0;
        while(num > 0){
        	String temp =  helper(num%1000);
        	num /= 1000;
        	if(temp.length() == 0){
        		i++;
        		continue;
        	}
        	result = temp + map4[i]  + (result.length() == 0? result : " " + result);
        	i++;
        }
        
        return result;
    }
    
    public String helper(int num){
        String temp = num + "";
        String[] map1 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        String[] map2 = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] map3 = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"}; 
        
        if(temp.length() == 1){
            return map1[temp.charAt(0) - '0'];
        }
        
        if(temp.length() == 2){
            if(num < 20 && num >= 10){
                return map2[num - 10];
            }else{
            	String subStr = helper(temp.charAt(1) -'0');
                return map3[temp.charAt(0) - '0'] + (subStr.length() == 0? "" : " " + subStr); 
            }
        }
        
        String subStr = helper(Integer.valueOf(temp.substring(1, temp.length())));
        return map1[temp.charAt(0) - '0'] + " Hundred" + (subStr.length() == 0? "" : " " + subStr); 
    }
}
