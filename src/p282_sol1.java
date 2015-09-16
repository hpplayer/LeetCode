import java.util.ArrayList;
import java.util.List;

/*
Expression Add Operators

Given a string that contains only digits 0-9 and a target value, return all possibilities to add operators +, -, or * between the digits
so they evaluate to the target value.

Examples: 
"123", 6 -> ["1+2+3", "1*2*3"] 
"232", 8 -> ["2*3+2", "2+3*2"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []
*/		


/**
 * This is a really hard problem, I mean really hard!
 * Couple of things need to be noticed
 * 1) The order of operations, if we can add * behind +/-, then we need follow this order
 * 2) Exceed integer.max_value
 * 3) We can merge digits like s = '123', target = 15, then we can have 12 + 3
 * 4) heading zero, like 00 is not valid, 0 is valid, so we may have s = "000", target = 0, then we cannot merge 0s
 * 5) build strings, how can we add signs? if we add signs based on previous signs, how about the initial case when index = 0?
 * 6) finally how can we deal with the initial case, where to start our DFS?
 * 
 * Sol1 provides a very good approach to solve this problem.
 * 1) In the main program, we search the first left number in input num. We put it in main program because it is inital case where we don't
 * have sign before it
 * 2) In the DFS(), we search the left right part one by one, and try to merge it with left. If the new left value = target and we don't
 * have right digit left, then we add it into the result. As discussed in 3), right part can be a single digit or a number with several
 * digits, thus we need a for loop for each input left number.
 * 3) How can we combine left and right part? if we assume the sign between them is just +/-, then we can merge them directly
 * if the sign is "*", then we need the information of last added right part. So we just remove this right part from last part, then
 * multiple it by current right to build a new right. Here, we also need the sign information we used to add the old right part,
 * so we can merge the new right accordingly. If there is no sign before the old right part, which means we are dealing with the "*"
 * right after the first left number, then we can just multiple old left directly by current right. Since we merge old right and new right
 * together, we will keep use the sign before old right and pass it to next recursion
 * 4) It is possible that we have a input number > Integer.MAX_VALUE, so all numeric input are declared with long
 * 5) It is possible we have several heading 0s, then we just need to check the string value with new string from converted numeric value 
 * if they are not same, it means we have several heading 0s. We can safely stop here
 * 
 * Ok, this is the whole part of algorithm. It is not so complicated but also not easy.
 * I only list recursive solution here since it is a new problem and I don't have much reference.
 * U will update it in future if necessary
 * 
 * @author hpPlayer
 * @date Sep 16, 2015 3:50:51 PM
 */

public class p282_sol1 {
	public static void main(String[] args){
		String num = "2147483647";
		int target = 2147483647;
		
		System.out.println(new p282_sol1().addOperators(num, target));
	}
   public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<String>();
        //search each possible left start number
        for(int i = 1; i <= num.length(); i++){
            String left = num.substring(0, i);
            long left_long = Long.valueOf(left);
            if(!(left_long + "").equals(left)) break;//if it starts with multiyple 0s, skip them all
            DFS(num, i, target, left, left_long, left_long, '#', result);
        }
        
        return result;
    }
    
    
    public void DFS(String num, int index, int target, String leftExpr, long leftValue, long prevValue, char sign, List<String> result){
        //if we reach end of string and left value equals target
        if(index == num.length() && target == leftValue){
            result.add(leftExpr);
            return;
        }
        //start from index + 1, end at num.length, the input index is already the first index after left
        for(int i = index+1; i <= num.length(); i++){
            String right = num.substring(index, i);
            long right_long = Long.valueOf(right);
            if(!(right_long + "").equals(right)) return;//if it starts with multiple 0s, skip them all
            
            //operator between left and right is "+"
            DFS(num, i, target, leftExpr + "+" + right, leftValue + right_long, right_long, '+', result);
            
            //operator between left and right is "-"
            DFS(num, i, target, leftExpr + "-" + right, leftValue - right_long, right_long, '-', result);
            
            //operator between left and right is "*", then we merge this * with right to left
            long newLeft = 0;
            if(sign == '+'){
                //remove last number, add last number * current right
                newLeft = leftValue - prevValue + prevValue * right_long;
            }else if (sign == '-'){
                newLeft = leftValue + prevValue - prevValue * right_long;
            }else{//inital case, first left, where we don't have +/- before it 
                newLeft = prevValue * right_long;
            }
            //since we merge a * b to a new a, we will keep the sign before a
            DFS(num, i, target, leftExpr + "*" + right, newLeft, prevValue*right_long, sign, result);
        }
    }
}
