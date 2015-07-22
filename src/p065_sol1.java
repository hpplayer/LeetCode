/* Valid Number 
 * 
Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
 
 * 
 */

/**
 * This is my own solution towards this problem
 * The way I used to solve the problem is fail-and-try
 * There is no difficult part in understanding the problem, but we need to find out all legal and illegal string
 * 
 * But of course, this is not the ideal way to solve the problem
 * The official solution can be found in sol2
 * I also found a similar solution but with more clean and organized format, please see sol3
 * @author hpPlayer
 * @date Jul 22, 2015 4:39:01 PM
 */
public class p065_sol1 {
	public static void main(String[] args) {
		System.out.println(new p065_sol1().isNumber("6e6.5"));
	}

	public boolean isNumber(String s) {
		s = s.trim();
		boolean afterE = false;
		//System.out.println(s);
		if (s.equals("")){
		    return false;
		}
		int countC = 0;
		int countE = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			
			//count number of "." in the string
			if (c == '.'){
				countC ++;
				//System.out.println(count);
				if (countC > 1){
					return false;
				}
			}
			//count number of "e" in the string
			if (c == 'e'){
				countE ++;
				//System.out.println(count);
				if (countE > 1){
					return false;
				}
			}
			//general digit case
			if ('0' <= c && c <= '9') {
				continue;
			}
			//deal with sign in the first bit
			if((c == '-'|| c == '+') && i == 0 && i <s.length()-1&& !afterE){
				continue;
			}	
			
			//deal with sign in the middle
			if((c == '-'|| c == '+') && i > 0 && i <s.length()-1){
				char prevC = s.charAt(i - 1);
				if (prevC == 'e'){
					continue;
				}
			}
			
			//deal with the case "." in the middle (we dont allow "." after "e")
			if ((c == '.') && i > 0 && i < s.length() - 1 && !afterE) {
				char prevC = s.charAt(i - 1);
				char postC = s.charAt(i + 1);
				if ((prevC <= '9' && prevC >= '0') || prevC == '+' || prevC == '-') {
					if ((postC <= '9' && postC >= '0') || postC == 'e' ) {
						System.out.println("im here");
						continue;
					}
				}
			}
			//deal with case "e"
		    if ((c == 'e') && i > 0 && i < s.length() - 1) {
				char prevC = s.charAt(i - 1);
				char postC = s.charAt(i + 1);
				//".e" and "e+" and "e-"is legal
				if ((prevC <= '9' && prevC >= '0') || prevC == '.') {
					if ((postC <= '9' && postC >= '0') || postC == '+' || postC == '-') {
					    afterE = true;
						continue;
					}
				}
			}			
		    //deal with the "." in the first bit case
			if (c == '.' && i == 0 && i < s.length() - 1 && !afterE) {
				char postC = s.charAt(i + 1);
				if (postC <= '9' && postC >= '0') {
					continue;
				}
			}
			//deal with the "." in the last bit case
			if (c == '.' && i == s.length() - 1 && i > 0 && !afterE) {
				char prevC = s.charAt(i - 1);
				if (prevC <= '9' && prevC >= '0') {
					continue;
				}
			}

			return false;
		}
		return true;
	}
}
