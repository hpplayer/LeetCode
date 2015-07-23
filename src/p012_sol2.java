/**
 * This is the approach that avoid the use of hashmap and prove the beauty of math
 * The basic idea is similar, but it uses the trick that start from the largest key, we can divide input by current largest key, 
 * so that achieve same goal without the for loop I used in sol1. 
 * But we also need to append corresponding Roman numerals to the result, so we need a for loop that can append Roman numeral.
 * 
 * Remark:
 * The official approach uses the similar way, so I don't put the official solution here
 * 
 * Anyway this approach is beautiful.
 * @author hpPlayer
 * @date Jul 23, 2015 1:03:59 AM
 */
public class p012_sol2 {
    public String intToRoman(int num) {
        int[] keys = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};//equal the keys in the hashMap, but reverse order
        String[] values = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};//equal the values in the hashMap, but reverse order
        StringBuilder sb = new StringBuilder();
        int i = 0;
        
        while(num > 0){
        	int times = num / keys[i]; //Start from the biggest key, try to minimize the num with biggest key as much as possible
        	num -= keys[i] * times;//update num
        	//append "times" keys to the stringBuilder
        	for(;times > 0;times --){
        		sb.append(values[i]);
        	}
        	i++;
        }
        
        return sb.toString();
    }
}
