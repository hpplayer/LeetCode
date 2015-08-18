/**
 * Based on sol2, we can even optimized the space complexity to O(1)
 * Here we use a variable prev to record values among * and /, we will add prev to the result if current sign is + or -, which means 
 * current sign allows us to add the value to results. If current sign is * or /, we have to get the value from * and / expression
 * before add the prev to results
 * Example:
 * 1 - 5 * 6 /3 + 2
 * when look at 1: sign is +, we add prev 0 to our result, update prev to 1
 * we update sign to -
 * 
 * when look at 5: sign is -, we are allowed to add prev to result, so we add prev 1 to our result, update prev -5
 * we update sign to *
 * 
 * when look at 6: sign is *, we are not allowed to update result, we simply update prev to -5*6
 * we update sign to /
 * 
 * when look at 3: sign is /, we are not allowed to update result, we simply update prev to -5*6/3
 * we update sign to +
 * 
 * when look at 2, sign is +, we are allowed to add prev to result, so we add prev -5*6/3 to our result, update prev to 2
 * 
 * loop end 
 * we just add prev to result so now the result is like 
 * 1 + -5*6/3 + 2, which is correct result
 * 
 * 
 * @author hpPlayer
 * @date Aug 17, 2015 10:36:04 PM
 */
public class p227_sol3 {
	public static void main(String[] args){
		String s= "0*0";
		System.out.println(new p227_sol3().calculate(s));
	}
    public int calculate(String s) {
        s = s.trim().replaceAll(" +", "");//replace any sequence of spaces with ""
        int prev = 0;
        int result = 0;
        int i = 0;
        char sign = '+';
        while (i < s.length()){
            int curr = 0;
            while(i < s.length() && Character.isDigit(s.charAt(i))){
                curr = curr * 10 + s.charAt(i++) - '0';
            } 
            //since we have removed space from s, we can safely assume now string only composed of signs and ints
            //we will reach here when we encouter a sign, now we need update previous result
            if (sign == '+'){//current sign is +, we can update previous value to result
                result += prev;
                prev = curr;
            }else if (sign == '-'){///current sign is -, we can update previous value to result,
                result += prev;
                prev = -curr;
            }else if (sign == '*'){//curent sign is *, we can only combine prev and current result
                prev = prev * curr;
            }else if (sign == '/'){
                prev = prev / curr;
            }
            //we will reach here only if we encounter a sign
            if(i < s.length()){
                sign = s.charAt(i++);
            }
        }
        //add the last prev
        result += prev;
        return result;
    }
}
