import java.util.Arrays;
import java.util.Comparator;
/**
 * Here, I use the similar basic idea as sol1. But I applied several good optimizations
 * 1) Instead build an Integer[], I directly build a string[], so that we don't need to worry about calculations when building strings
 * 2) Since our comparator will sort the array in descending order, if the first digit in result array is 0, that means our array is all 0
 * so we can simply return 0.
 * 3) Some posts mentioned convert int to string with simply concatenation "" + int is not a good practice, so here I use String.valueOf() to
 * do the conversion
 * 
 * @author hpPlayer
 * @date Sep 6, 2015 5:42:20 PM
 */

public class p179_sol2 {
    public String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++){
            strs[i] = String.valueOf(nums[i]);
        }   
        
        Arrays.sort(strs, new Comparator<String>(){
           public int compare(String s1, String s2){
               //build two strings with two possible order
               String temp1 = s1 + s2;
               String temp2 = s2 + s1;
               //we will sort the array in descending order, so we need reverse the order we usually use which gives ascending order
               return temp2.compareTo(temp1);
           } 
        });
        
        //we sort the array with descending order, so if the first number is 0, that means we only get 0 in our result, just return it
        if(strs[0].equals("0")) return "0";
        StringBuilder sb = new StringBuilder();
        
        for(String str: strs){
            sb.append(str);
        }
        
        return sb.toString();
    }
}
