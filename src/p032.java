/*****
 * We have two solutions towards this problem, one is using DP and traverse backwards, one is using stack and traverse forward
 * For the DP: we use an array D to record the max length of parentheses in the substring that start with i. So if String.charAt(i) = ')'
 * then it will be 0, if it is '(', then we need check the index after D[i+1]. i -> ( (....) <-D[i+1] )<--the one that we want to check,
 * lets say it is j. if j is '(', then we know this length is 0, since we can't close it(because D[i+1] is the max length in next index,
 * so we know that j+1 and its following array is not a valid max length array), if it is ')', then we can simply let D[i] = D[i+1] +2,
 * Additionally, in this case, if j+1 != s.length -1, then we may got more consecutive parentheses after it, so we need let D[i]= D[i+1]+2+D[j+1]  
 * 
 * For the stack: it is much simpler, we have stack record the index of unmatched parentheses. if the input if '(', then we just push it 
 * to stack, if the input is ')', then we check the top of stack. If the stack.peek() return the index of ')', then it is still unmatched,
 * we just push the input into stack, otherwise if the stack.peek() return the index of '(', then we know, we have a closed match. we just
 * pop the top on stack 
 * Here comes two cases, if the stack becomes empty, then we know i index is a clear index, which means all parentheses before it are all matched
 * so we just make result = i+1 (+1 means, i is index, we need calculate the real number, also because all parentheses before i are matched, we know
 * this result should be largest number so far). else if the stack still has elements remain, then current length is stack.peek() - i, which means 
 * we will get the length between last unmatched parentheses and current i, which should gives us the length of valid parentheses. In this case,
 *  we dont whether the length is longer than the one we got before, so we use Math.max() to update result.
 */

import java.util.HashMap;
import java.util.Stack;

/*
 * Parentheses usually use stack!
 * 
 */
public class p032 {
	public static void main(String[] args) {
		//String a = "(";
		 //String a = "()(()";
		// String a = ")(((((()())()()))()(()))(";
		String a = ")(()(()(((())(((((()()))((((()()(()()())())())()))()()()())(())()()(((()))))()((()))(((())()((()()())((())))(())))())((()())()()((()((())))))((()(((((()((()))(()()(())))((()))()))())";
		System.out.println(longestValidParentheses4(a));
	}
	
	
	
	
	public static int longestValidParentheses4(String s) {//use DP
		if(s.isEmpty()){//since we will create an array based on s, so we need firstly check if s exists
			return 0;
		}
		int result = 0;
		int[] DP = new int[s.length()];//DP[i] means how long max  Parentheses is in the substring that start with i,
		DP[s.length() -1] = 0;//we start from tail to head, tail is 0, obviously
		for(int i = s.length() -2; i>=0; i--){
			if(s.charAt(i) == ')'){//')' cant start a new Parentheses
				DP[i] = 0;
			}else{//if it is '('
				int j = i+1+DP[i+1];// we want to get the element after next DP[i+1]
				if(j < s.length() && s.charAt(j) == ')'){
					DP[i] = DP[i+1] +2;//we found match, add the value to DP[i](case here: the j is last element, like (()())
					if(j +1 < s.length()){//probably we does not end with last element like ()()(), this is normal case
						DP[i] += DP[j+1];//add boundary case
					}
				}else{
					DP[i] = 0;
					//substring start with i cant match, so set value to 0. However the
					//default the value is 0, the reason we add it here is to make the code more clear
				}
			}
			result = Math.max(result, DP[i]);
		}
		return result;
	}
	public static int longestValidParentheses3(String s) {//use stack
		int res = 0;
		Stack<Integer> stack = new Stack<Integer>();
		char[] arr = s.toCharArray();
		for(int i = 0; i < arr.length; i++){
			if(arr[i] == ')' && !stack.isEmpty() && arr[stack.peek()] == '('){//found match
				stack.pop();
				if(stack.isEmpty())
					res = i+1;//all previous elements are matched, index +1 = num of Parentheses
				else//we need to know how long is matched
					res = Math.max(res, i-stack.peek());
			}else{//uf arr[i] == '('
				stack.push(i);
			}
		}
		return res;
	}
/*
	public static int longestValidParentheses(String s) {
		int count = 0;
		int z = 0, j = 0;
		boolean token = false;
		for (int i = 0; i < s.length() - 1; i++) {
			if (s.charAt(i) == '(') {
		
				if (z == 0) {
					z = i + 1;
				}
				for (j = z; j < s.length(); j++) {
					token = false;
					if (s.charAt(j) == ')'){
						token = true;
						count +=2;
						
						if(j < s.length() - 2){
					
							if(s.charAt(j+1) != '(' || s.charAt(j+2) != ')'){
								System.out.println("I ");
								count =0;
							}
						
						}
				
						break;
					}
				
				}
				z = j+1;
			//	System.out.println(j);
			}

		}
		if(token == false){
			count -=2;
		}
		return count;
	}

	public static int longestValidParentheses2(String s) {
		return longestValidParentheses(s, 0, new HashMap<String, Integer>());
	}

	public static int longestValidParentheses(String s, int result,
			HashMap<String, Integer> hs) {
		if (hs.containsKey(s)) {
			return hs.get(s);
		}
		// System.out.println(s.isEmpty());
		if (s.isEmpty()) {
			return 0;
		}
		int i = 0; // j =s.length()-1;
		int maxResult = 0;
		for (i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(')
				break;
		}
		if (i == s.length()) {
			return 0;
		}
		// while(s.charAt(i) != '('){
		// i++;
		if (s.charAt(i) == '(') {
			for (int j = i + 1; j < s.length(); j++) {
				result = 0;
				if (s.charAt(j) == ')') {
					result++;
					// System.out.println(replaceStr(s, i, j));
					result += longestValidParentheses(replaceStr(s, i, j),
							result, hs);
					if (result > maxResult) {
						maxResult = result;
					}
				}
			}
		}

		// }
		hs.put(s, maxResult);
		return maxResult;
	}

	public static String replaceStr(String str, int a, int b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			if (i == a || i == b) {
				continue;
			}
			sb.append(str.charAt(i));
		}
		return sb.toString();
	}
	*/
}
