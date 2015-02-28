import java.util.HashMap;
import java.util.Stack;

/*
 * Parentheses usually use stack!
 * 
 */
public class p32 {
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
