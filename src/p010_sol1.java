import java.util.Stack;
/**
 * failed...
 * 
 * My idea is using Stack, and it can only pass 411 test cases.
 * This is because stack cannot check the previous cases, for example
 * String s: a b* a c* a
 * String p : a a b* c * a*
 * How can the stack P know the last a* only needs provide one a, and previous string will provide remaining two a...
 * So, failed...
 * 
 * Remark:
 * 
 * Actually, String p is the real string, which may contain "." or "*".
 * So we can only handle different cases on String p, no need work on both
 * @author hpPlayer
 * @date Mar 14, 2015 10:45:32 PM
 */

public class p010_sol1 {
	public static void main(String[] args){
		String a = "aaa";
		String b = "ab*a*c*a";
		System.out.println(a.matches(b));
		System.out.println(isMatch(a,b));
	}
	 public static boolean isMatch(String s, String p) {
	        if(s == null || p == null) return false;
	        Stack<Character> stackS = new  Stack<Character>();
	        Stack<Character> stackP = new  Stack<Character>();
	        for(int i = 0; i < s.length(); i++){
	            stackS.push(s.charAt(i));
	        }
	        for(int i = 0; i < p.length(); i++){
	            stackP.push(p.charAt(i));
	        }
	        //System.out.println(stackS.size());
	        while(stackS.size() != 0 && stackP.size()!= 0){
	            char s_char = stackS.pop();
	            char p_char = stackP.pop();
	           // System.out.println(stackS.size());
	          if(s_char != '.' && s_char != '*' && p_char != '.' && p_char != '*'){
	        	  if(s_char != p_char){
	        		  return false;
	        	  }else{
	        		  continue;
	        	  }
	          }else if(s_char != '.' && s_char != '*' && p_char == '.'){//one one is char
	        	  continue;
	          }else if(p_char != '.' && p_char != '*' && s_char == '.'){
	        	  continue;
	          }else if(p_char == '*'){
	        	 // System.out.println("im here");
	        	  char temp = stackP.peek();
	        	  if(temp == '.') return true;
	        	 // System.out.println(temp);
	           	 // System.out.println(stackS.size());
	          	  if(s_char != temp){
	          		  stackS.push(s_char);
	        		  stackP.pop();   		
	        	  	  continue;
	         
	        	  }else{
	        		  while(stackS.size() > 0 && stackS.peek() == temp){
	 	        		 //System.out.println("im here");
	 	        		  stackS.pop();
	 	        	  }
	        		//  if(stackS.size()== 0) return true;
	        		  //stackS.push(s_char);
	        	  	  stackP.pop();
	        	  	  if(stackP.peek() == '*'){
	        	  		 // System.out.println("im here");
	        	  		  char temp1 = stackP.pop();
	        	  		  char temp2 = stackP.pop();
	        	  		  stackP.push(temp);
	        	  		 // System.out.println(temp);
		        	  	  stackP.push('*');
		        	  	  stackP.push(temp2);
		        	  	//  System.out.println(temp2);
		        	  	  stackP.push(temp1);
	        	  	  }
	        	  	 
	        	  	  continue;
	        	  }
	        
	     
	          }else if(s_char == '*'){
	        	 
	        	  char temp = stackS.peek();
	        	  if(temp == '.') return true;
	           	  if(p_char != temp){
	          		  stackP.push(s_char);
	        		  stackS.pop();
	        	  	  continue;
	         
	        	  }else{
	        		  while(stackP.size() > 0 && stackP.peek() == temp){
	 	        		 //System.out.println("im here");
	 	        		  stackP.pop();
	 	        	  }
	        		//  if(stackP.size()== 0) return true;
	        		  //stackS.push(s_char);
	        	  	  stackS.pop();
	        	  	  continue;
	        	  }
	          }
	        }
	        if(stackS.size() == 0 && stackP.size()== 0){
	           //	System.out.println("im here");
		        return true;
	        }else if(stackS.size() > 0){
	        	//System.out.println("im here");
	          	while(stackS.size() > 0){
	          		char s_char= stackS.pop();
	          		if(s_char != '*'){
	          			return false;
	          		}else{
	          			stackS.pop();
	          		}
	          	}
	        	return true;
	        }else{
	        	System.out.println("im here");
	        	System.out.println(stackP.toString());
	        	while(stackP.size() > 0){
	          		char p_char= stackP.pop();
	          		if(p_char != '*'){
	          			return false;
	          		}else{
	          			stackP.pop();
	          		}
	          	}
	        	return true;
	        }

	    }
}
