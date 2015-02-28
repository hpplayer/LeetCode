
public class p125_sol2 {
	public static void main(String[] args){
		System.out.println(isPalindrome("a."));
	}
	
	public static boolean isPalindrome(String s){

			

		int i = 0, j = s.length() -1;
		while(i < j){
			while(i < j &&!Character.isLetterOrDigit(s.charAt(i))) i ++;//use Character.isLetterOrDigit!!!!!
			while(i < j && !Character.isLetterOrDigit(s.charAt(j))) j --;
			if(Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))){
				return false;
			}
			i++;
			j--;
		}
	
		return true;

	}

}
