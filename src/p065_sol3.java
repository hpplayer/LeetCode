/**
 * This is similar way to my sol1
 * It deal with ".", "e", and "+/-" case separately
 * This solution needs us to think carefully each special case like when "e" is legal. All special cases are not follow order, 
 * so it is to miss some cases
 * 
 * By contrast, sol2 is more smoothly, we can think the problem in more nature way, so sol2 is the best solution 
 * 
 * More details about sol3 can be found below
 * @author hpPlayer
 * @date Jul 22, 2015 6:00:20 PM
 */
public class p065_sol3 {
	public static void main(String[] args) {
		System.out.println(new p065_sol3().isNumber(".1"));
	}
	public boolean isNumber(String s) {
		if ( s== null) return false;
		boolean hasE = false;
		boolean hasDot = false;
		boolean hasNum = false;
		s = s.trim();
		if (s.length() == 0) return false;//only contains whitespace
		
		for (int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			
			//illegal char
			if (!isValid(c)){
				return false;
			}
			
			if ( c <= '9' && c >= '0') hasNum = true;
			
			//dot case
			
			if (c == '.'){
				//we can't have two dots and we also can't have dot after e
				if(hasDot || hasE){
					return false;
				}
				//if dot is in last index, we must have digits before this index
				if(i == s.length()- 1 && !hasNum) return false;
				hasDot = true;
			}
			
			if(c == 'e'){
				//we must have only one "e" in the string also we must have digits before e
				if(hasE || !hasNum) return false;
				//e could not be in the last index
				if(i == s.length() - 1) return false;
				hasE = true;
			}
			
			if(c == '+' | c == '-'){
				//sign is in first index
				if (i == 0){
					continue;
				}else if (hasE && s.charAt(i-1) == 'e' && i < s.length() -1 && Character.isDigit(s.charAt(i+1))){//sign after e and before digit
					continue;
				}else{//else just return false
					return false;
				}
			}		
		}
		return true;
	}
	
	public boolean isValid(char c){
		return c == '.' || c == '+' || c == '-' || c == 'e' || Character.isDigit(c); 
	}
}
