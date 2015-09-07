import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * The basic idea of this solution is that we will try to convert each substring to an integer representation
 * Then put it into the hashMap, which later can tell us if we have visited this substring before
 *  
 * This basic idea is based on the fact that we only have 4 characters in input string, so we don't need many bits to represent 
 * each substring. We can use 2 bits to represent 4 different items. So here we use 0-3 to replace ACGT
 * 
 * Each time, we read a character from substring, convert it to 2 bits representation and add it to the last 2 bits of the code that
 * we are building. We use left shift to get the last 2 bits then use or '|' to assign the value
 * 
 * Each character now only takes 2 bits, our substring has 10 characters, and now they only takes 20 bits, which is smaller than an integer
 * Originally, in string, each character takes 2 bytes, and each substring would take 160 bits. So the size is compressed
 * 
 * After we convert the code, thing become much easier.
 * We simply create a HashMap to put substrings we have visited
 * The reason that we use hashMap is because we only need to record repeat substring once. It is possible a repeat substring may 
 * occur many times, and we only need it into the result in our second visit. A hashMap can help us get that. if the key is contained
 * in the hashMap, it means we have visited this substring before. If the value indicates not visited second time, then we add it
 * into our result list, otherwise, we just skip it
 * 
 * Remark:
 * Values stored in HashMap is boolean. Comparing with stored num of occurrences which is integer, this further saved space
 * Each boolean is java can be packed into a byte, which each integer needs 4 bytes
 * 
 * @author hpPlayer
 * @date Sep 6, 2015 8:46:26 PM
 */

public class p187_sol2 {
    
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
    
    public int getCode(String s){
        int code = 0;
        for(int i = 0; i < s.length(); i++){
            code <<= 2;
            code |= Char2Int(s.charAt(i));
        }
        return code;
    }
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<String>();
        //why not use hashSet? Because we may have a match strings appear more than 2 times, we only need it once
        HashMap<Integer, Boolean> hs = new HashMap<Integer, Boolean>();
        if(s == null || s.length() < 10) return result;
        
        //i is the index, so index + 9 will help us get the 10th char in substring of length 10
        for(int i = 0; i + 9 < s.length(); i++){
            String sub = s.substring(i, i+10);//end of substring is exclusive
            int code = getCode(sub);
            if(hs.containsKey(code)){
                if(!hs.get(code)){//if it is the second time visit this code
                    result.add(sub);
                    hs.put(code, true);//set to true to prevent further visit
                }
            }else{
                hs.put(code, false);
            }
        }
        
        return result;
    }
}
