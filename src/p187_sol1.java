import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
Repeated DNA Sequences 

All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].
*/


/**
 * This is another string search problem
 * I thought I could solve it with KMP, but I didn't
 * In KMP, we use the pattern itself to speed up the search.
 * But in this problem, each substring of length 10 can be a pattern, so my code got LTE error
 * 
 * Then we can try brute force solution with this problem, i.e. we can check each substring of length 10 to see the repeat
 * We put each visited substring of length 10 into a HashSet or Map, then continue
 * However this solution will return "Memory Limit Exceed" error, which comes the key point of this problem
 * 
 * How can we compress the size of memory that we need occupy. In Java, each character is 2 bytes large, then 10 characters would be
 * 10 * 2 * 8 = 160 bits. If we can use some technique to compress the size, then we will get our solution. We know each integer in
 * java is 32 bits large, so if we can convert strings to integer, then we can lower the size. Fortunately, the problem suggests 
 * we will only have 4 characters in string, so it is possible. We need find a way to get 4 unique integers from A/C/G/T. 
 * Sol2 suggest a such algorithm, which is very easy-understanding and smart
 * 
 * 
 * Sol1 is my code that tries to solve the problem with KMP, but failed 
 * Sol2 is an easy-understanding solution with bit manipulation and hashMap, but use an additional function() to generate code
 * Sol3 is an solution that uses same idea with sol2 but without extra function(), and it generates code on fly, and faster
 * 
 * So sol3 is recommended
 * 
 * Remark:
 * There is one of the online solution that can further simplifies the code into a shorter one. But it requires you know the 
 * hex representation of A/C/G/T first by lookup ASCII table, so it is not pratical in real interview and thus not list here
 * @author hpPlayer
 * @date Sep 6, 2015 8:26:51 PM
 */

public class p187_sol1 {
	public static void main(String[] args){
		String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";//"AAAAACCCCCAAAAACCCCC + C + AAAAAGGGTTT" 
		for(String temp : new p187_sol1().findRepeatedDnaSequences(s)){
			System.out.println(temp);
		}
	}
    public List<String> findRepeatedDnaSequences(String s) {
        if(s == null || s.length() < 10) return new ArrayList<String>();
        int i= 0;
        List<String> result = new ArrayList<String>();
        HashSet<String> hs = new HashSet<String>();
        while (i  + 20 < s.length()){
        	//System.out.println("i: " + i + " i+10");
        	
            String sub = s.substring(i, i+ 10);
            int[] ary = returnTable(sub);
            //System.out.println(Arrays.toString(ary));
            if(!hs.contains(sub)){
                for(int j = i + 10; j+10 < s.length(); j ++){
                	if(sub.equals(s.substring(j, j + 10))){
                		result.add(sub);
                		hs.add(sub);
                		break;
                	}
                }
            }

            //s.substring(i+10, i+20);
            /*
            if(sub.equals(sub2)){
            	//System.out.println("here");
                result.add(sub);
            }
            */
          // System.out.println("sub: " + sub);
            //System.out.println("sub2: " + sub2);
            int nextI = 1;//Math.max(1, ary[ary.length-1] + 1);
           
            for(int j = ary.length -1; j>=0; j--){
                if(ary[j] != 0){
                    nextI = ary[j] + 1;
                    //System.out.println(nextI);
                    break;
                } 
            }
           
            i += nextI;
           
        }
        
        return result;
    }
    
    public int[] returnTable(String s){
        int[] result = new int[s.length()];
        
        for(int i = 1; i < s.length(); i++){
            int j = result[i-1];
            while(j > 0 && s.charAt(i) != s.charAt(j)){
                j = result[j-1];
            }
            
            if(s.charAt(i) == s.charAt(j)) j++;
            
            result[i] = j;
        }
        
        return result;
    }
}
