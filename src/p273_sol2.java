/**
 * This solution uses the similar basic idea as my sol1, where we use divide-and-conquer to convert int to string on each len of 3
 * But here we uses several tricks:
 * 1) we can combine number 1- 19 into one array
 * 2) we can use int / 1000 to pass next int and use int%1000 to get current int
 * 3) we always append " " after newly generated result. If we don't have numbers left, then we can use trim() to remove redundant tailing 0s
 * 4) we only analyze sub int, if it is > 0 
 * 
 * 
 * @author hpPlayer
 * @date Sep 24, 2015 2:51:06 PM
 */
public class p273_sol2 {
    public String numberToWords(int num) {
    	if(num == 0) return "Zero";
    	String[] LargerThan1000 = {"", "Thousand", "Million", "Billion", "Trillion"}; 
    	String result = "";
        int i = 0;
        
        while(num > 0){
        	
        	if(num%1000 != 0){
                 result = helper(num%1000) + LargerThan1000[i] + " " + result;
        	}
        	
        	num /= 1000;
        	i++;
        }
        
        return result.trim();
    }
    
    //we can use trim() to remove heading and tailing 0s
    public String helper(int num){
        String[] lessThan20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] lessThan100 = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"}; 
        if(num == 0){
            return "";//don't append " ", since we dont have anything in this seg, and prev seg already has a tailing " "
        }else if(num < 20){
                return lessThan20[num] + " ";
            
        }else if (num < 100){
            //we are free to append " " after previous segment, since we can use trim() to remove tailing 0s
                return lessThan100[num/10] + " " + helper(num%10);
        }

        return lessThan20[num/100] + " Hundred " + helper(num%100); 
    }
}
