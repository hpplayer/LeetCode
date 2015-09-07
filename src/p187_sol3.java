import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * This main idea of this solution is very similar to sol1, but here we modify the code on fly, instead of generating code 
 * when we get a new substring, so it supposed to run faster.
 * 
 * To avoid using getCode() function, we have to firstly use a loop to build a code containing first 9 characters in the string.
 * 
 * why not picking 10? Because, we can thus inserting first substring of length 10 in the main loop, which would keep consistency.
 * Not doing so would cost indexing problem when take the substring
 * 
 * After we get the code with length 9, then we do the same thing as getCode(), i.e. we left shift the code by 2 bits, then assign 
 * the bits representation of char into the last of code. Thus we get a new code for first 10 characters in input string. In later 
 * loops, we do the same thing, pop the first 2 bits out of the code and add new 2 bits to the last.
 * 
 * Remark:
 * Because we only care about last 20 bits in the integer, so after we left shift the first 2 bits out of the code, we have to use
 * a marker to clear them. Here we use "& 0xFFFFF", which will set bits other than last 20 bits to 0s
 * 
 * 
 * @author hpPlayer
 * @date Sep 6, 2015 9:42:30 PM
 */

public class p187_sol3 {
	public static void main(String[] args){
		String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";//"AAAAACCCCCAAAAACCCCC + C + AAAAAGGGTTT" 
		for(String temp : new p187_sol3().findRepeatedDnaSequences(s)){
			System.out.println(temp);
		}
	}
	
    public int Char2Int(Character c){
        //ATCG can be distinguished by 2 bits
        switch (c){
            case 'A':
               return 0;
            case 'T':
                return 1;
            case 'C':
                return 2;
            case 'G':
                return 3;
        }
        
        return -1;
    }
    
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<String>();
        HashMap<Integer, Boolean> hs = new HashMap<Integer, Boolean>();
        
        if(s == null || s.length() < 10) return result;
        
        int i = 0, code = 0;
        
        //build the code for first 9 characters, so we can put 10th bit in following loop
        //otherwise we may have problem with getting substring 
        for(i = 0; i < 9; i++){
            code = ((code << 2) | Char2Int(s.charAt(i)));
        }
        for(; i< s.length(); i++){
        	//0xfffff will build a binary with last digits to all 1s, digits before them are all 0s
        	//we only change the last 20 digits of bits 
        	//so & 0xfffff can help us focus only on last 20 bits
            code = (((code << 2)& 0xfffff) | Char2Int(s.charAt(i)));
            if(hs.containsKey(code)){
                if(!hs.get(code)){
                    hs.put(code, true);
                    result.add(s.substring(i-9, i+1));
                }
            }else{
                hs.put(code, false);
            }
        }
        
        return result;
    }
}
