import java.util.ArrayList;
import java.util.List;
/**
 * OK, this algorithm works in a neat way and don't need extra method to append spaces
 * The basic idea is that:
 * 1) we firstly calculate how many words in input string[] can be inserted into one line
 * We record the start index and end index of i and j-1, (j is the new start of next line, so j-1 is the index of last word in curr line)
 * 2) When we calculate the num of words can be inserted, we also include the additional one space that must be inserted after each word to
 * form a correct sentence. Notice we don't need to add such space after last word
 * 3) We record the total length that composed of words and spaces from subarray, then do calculation
 * 4) The number of spaces that needed to be inserted after each word is simply: num of spaces need to be insert / spaces we have
 * and the number of spaces that we treated as extra and only can be inserted at left is : num of spaces need to be insert % spaces we have
 * 5) Then we begin build the output line based on those information.
 * 6) For the last line, we are required add all extra spaces after sentence end. For last line, we don't calculate extra spaces we need, instead
 * we firstly build an output line that has one space after each word. Then we check the output line's length with given maxWidth. All other lines
 * should have same value (except the single char per line, which can be treated as same as the last line), but last line should has a different
 * value. Then we add spaces after the sentence based on this different value
 * 
 * That's basic flow of sol2.
 * 
 * Remark:
 * 1) the calculation of extra spaces here is very smart, so we get rid of data structure to add spaces
 * 2) Be careful with boundary cases, like single char per line
 * 3) more details are described in following code, check it out
 * 
 * @author hpPlayer
 * @date Sep 5, 2015 6:32:01 PM
 */

public class p068_sol2 {
	public static void main(String[] args){
		//String words[] = {"This", "is", "an", "example", "of", "text", "justification."};
		String words[] = {"a", "b"};
		//String words[] = {"Listen","to","many,","speak","to","a","few."};
		//String[] words = {"a","b","c","d","e"};
		//String[] words = {"ab", "cd", "ef", "abcde"};
		//String[] words = {""};
		for(String s : fullJustify(words, 2)){
			System.out.println(s);
		}
	}
	
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<String>();
        //j is the index of new start
        for(int i = 0, j; i < words.length; i = j){
            int len = -1;//we will append space after each word, -1 is to handle the last word in line
            //calculate how many words we can squeeze into a line (the length include the space after it)
            for(j = i; j < words.length && len + words[j].length() + 1<= maxWidth; j++){
                len += words[j].length() + 1;
            }
            
            StringBuilder sb = new StringBuilder();
            
            //spaces are the spaces needed to be inserted after each word
            int spaces = 1;//in case we reach last line
            //extra are the spaces needed to be inserted after words in the left part
            int extra = 0;
            
            //only two cases that we don't calculate the spaces and extra: 1) single char, in which we don't have spaces among words 2) last line
            // of input, in which, we don't add extra spaces among words
            //if j - 1 == i, that means j is the next word after i, that means our current line only contains
            //one single char, then we won't have spaces between a single char and we may only have spaces after it
            //so we need check j- 1 != i, to avoid the check of spaces between a single char
            if(j-1 != i && j != words.length){     //now j should be index just after the last word in line, so we use j - 1 here
                
                spaces = (maxWidth - len) / (j-1 - i) + 1;// + 1 for the space after each word
                
                //we will add only one extra space after each word in left part, otherwise, the spaces 
                //can be included in parameter spaces which applies to all words
                extra = (maxWidth - len) % (j-1 - i);
                                
            }
            
            //like I mentioned above, j is the index of new start, j- 1 is the last word in line
            //we won't add space after the last word, our loop contains word and spaces after it
            //so we access loop,but won't add space if current index is the last word
            for(int l = i; l < j; l++){
                sb.append(words[l]);
                /*
                 * or write as 
                 * if(l != j-1){
                 * 	for(int m = 0; m < spaces; m++) sb.append(" ");
                 * }
                 * 
                 */
                for(int m = 0; m < spaces && l != j-1; m++){
                    sb.append(" ");
                }
                if(extra-- > 0){
                    sb.append(" ");
                }
            }
            //if not last line, the remaining is supposed to be 0
            //if not 0, then we must reach the last line
            //we already insert 
            int remaining = maxWidth - sb.length();
            while(remaining-- > 0) sb.append(" ");
            result.add(sb.toString());

            
        }
        
        return result;
    }

   
}
