import java.util.ArrayList;
/**
 * Recursive way to try to solve this problem, but get LTE error.
 * @author hpPlayer
 * @date Mar 19, 2015 12:56:47 AM
 */

public class p132_sol1 {
	public static void main(String[] args){
		//String s = "seeslaveidemonstrateyetartsnomedievalsees";
		String s = "abcbcc";
		p132_sol1 test = new p132_sol1();
		
		System.out.println(test.minCut("ababababababababababababcbabababababababababababa"));
	}
	  public int minCut(String s) {
	        return DFS(Integer.MAX_VALUE, new ArrayList<String>(), s) -1;
	    }
	    
	    public int DFS(int min, ArrayList<String> temp, String s){
	        if(s.length() == 0){
	            if (temp.size() < min)
	               return temp.size();
	            else 
	               return min;
	        }
	        for(int i = 0; i < s.length(); i++){
	
	            if(isPalidrome(s.substring(0, i+1))){
	                temp.add(s.substring(0, i+1));
	                min = DFS(min, temp, s.substring(i+1));
	                temp.remove(temp.size()-1);
	            }
	        }
	         
	         return min;
	    }
	    
	    public boolean isPalidrome(String s){
	        int i = 0;
	        int j = s.length()-1;
	        while(i < j){
	            if(s.charAt(i) != s.charAt(j)) return false;
	            i++;
	            j--;
	        }
	        return true;
	    }
}
