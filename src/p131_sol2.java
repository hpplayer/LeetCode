import java.util.ArrayList;
import java.util.List;

/**
 * This is DP solution to this problem, although it may look very complicated, it can help improve the efficiency by reducing the 
 * time to validate palindrome.
 * Basic idea:
 * Firstly, we need create a boolean matrix of s.length * s.length size, and (x,y) means is the substring from x to y a palindrome?
 * Obviously, the value in the diagonal must be true (a char must be the palindrome). The Y axis of the matrix provides the left part
 * of the string s. The X axis of the matrix provides the right part of the string s. The final matrix should be separated by diagonal
 * into two parts, and we need to fill the right top part matrix. The way we fill the column is we start from a point at the 
 * diagonal, if we want to check the substring of length odd, like c a c, a a b, we can simply check the i-1 and i+1 char of each 
 * node.  If we want to check the substring of length even, like  c a, a a, we can simply check the i -1 and i char of each node
 * if equal, set value = true. Finally, with this table, we can do DFS with looking up this table. 
 * If we found a palindrome in the table from i to j, then we search palindrome after j, this part is almost same to the normal 
 * DFS approach, except we need this palindrome lookup table and an index to indicate where to start our lookup
 * 
 * Remark:
 * We are updating the matrix by diagonals, so when found palindrome, we should update the matrix unit like matrix[left--][right]++
 * (filling the diagonals)
 * @author hpPlayer
 * @date Mar 18, 2015 8:52:49 PM
 */

public class p131_sol2 {
	public static void main(String[] args){
		//String s = "seeslaveidemonstrateyetartsnomedievalsees";
		String s = "abcbcc";
		p131_sol2 test = new p131_sol2();
		boolean[][] table = test.table(s);
		boolean[][] table2 = test.table2(s);
		System.out.println(table == table2);
		for(int i = 0; i < table.length; i++){
			for(int j = 0; j < table[0].length; j++){
				System.out.print(table[i][j] + "         ");
			}
			System.out.println();
		}
		
		System.out.println();
		for(int i = 0; i < table2.length; i++){
			for(int j = 0; j < table2[0].length; j++){
				System.out.print(table2[i][j] + "         ");
			}
			System.out.println();
		}
	//List<List<String>> result= test.partition(s);
		//System.out.println(test.partition(s));
		//for(List<String> ls : result){
		//	System.out.println(ls);
	//	}
	}
	   public List<List<String>> partition(String s) {
	        if(s == null || s.length() == 0) return null;
	        List<String> temp = new ArrayList<String>();
	        List<List<String>> result = new ArrayList<List<String>>();
	        boolean[][] table = table(s);
	        //result.add(temp);
	        DFS(result, temp, 0, s, table);
	        return result;
	    }
	   
	   public void DFS(List<List<String>>  result, List<String> temp, int index, String s, boolean[][] table){
		   //index: left
		   if (index == s.length()) result.add(new ArrayList<String>(temp));
		   for(int i = index; i < s.length(); i++){
			   if(table[index][i]){//if Substring from index to i is palidrome 
				   temp.add(s.substring(index, i+1));
				   DFS(result, temp,i+1, s, table);//we use variable index to control the real index in string, so no need to pass substring 
				   temp.remove(temp.size()-1);
			   }
		   }
	   }
	   public boolean[][] table2(String s){
		   //left part = rows, right part = columns
		   boolean[][] table = new boolean[s.length()][s.length()];
		   for(int i = 0; i < s.length(); i++){
			  // table[i][i] = true;//we only look at one char itself
			   //TLE error, why?? I am also filling this table..
			   //incorrect, abcbcc, when look at (2,5), char == char, both are C, but cbcc is not palidrome...
			   for(int j = i; j < s.length(); j++){
				   if(s.charAt(i) == s.charAt(j)){
					   table[i][j] = true;
				   }
			   }

			
			   
		   }
		   return table;
		   
	   }
	   public boolean[][] table(String s){
		   //left part = rows, right part = columns
		   boolean[][] table = new boolean[s.length()][s.length()];
		   for(int i = 0; i < s.length(); i++){
			   table[i][i] = true;//we only look at one char itself
			   /*TLE error, why?? I am also filling this table..
			   for(int j = i; j < s.length(); j++){
				   if(s.charAt(i) == s.charAt(j)){
					   table[i][j] = true;
				   }
			   }
			   */
			   //even
			   int left = i -1;
			   int right = i;
			  
			   while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
				   table[left--][right++] = true;
			   }
			   
			   left = i-1;
			   right = i+1;
			   
			   while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
				   table[left--][right++] = true;
			   }
			   
		   }
		   return table;
		   
	   }
}
