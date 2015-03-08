
public class p125_sol1 {
	public static void main(String[] args){
		System.out.println(isPalindrome("a"));
	}
	   public static boolean isPalindrome(String s) {
	        if(s == null) return false;
	        if(s.length() == 0) return true;
	 
	        StringBuffer sb = new StringBuffer();
	        for(char c : s.toLowerCase().toCharArray()){
	        	if((c >= 'a' && c <= 'z' )|| (c >='0' && c <= '9')){
	        		sb.append(c);
	        	}
	        }
	        String temp = sb.toString();
	        if(temp.length() == 0) return true;
	        
	        System.out.println(temp);
	        for(int i = 0; i < temp.length()/2; i++){
	        	if(sb.charAt(i) != sb.charAt(temp.length() -1 - i)){
	        		return false;
	        	}
	        }
	        return true;
	    }
}
