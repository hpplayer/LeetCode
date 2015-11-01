/**
 * My original AC solution without help.
 * 
 * The tricky part is to count the char that not in correct position
 * Here I use max[] to count the max count of a char in String secret
 * and use count[] to count the offset between the appearance of a char in sector and in guess
 * 
 * So then we scan the count[]. We will only look at the char that has max count > 1 (i.e. at least it appears).
 * If we found the count[] > 0, then it means this char appears more times in string secret, therefore max_count - count[] would be how many
 * chars in guess that can be used to offset the char in secret
 * 
 * If we found the count[] < 0, then it means this char appear more times in guess, and all chars in secret can be offset by chars in guess.
 * So we just add max[i]
 * 
 * @author hpPlayer
 * @date Oct 30, 2015 4:44:58 PM
 */
public class p299_sol1 {
    public String getHint(String secret, String guess) {
        int[] count = new int[10];
        int[] max = new int[10];
        
        int countA = 0;
        for(int i = 0; i < secret.length(); i++){
            int a = secret.charAt(i) - '0', b = guess.charAt(i) - '0';
            if(a == b){
                countA ++;
            }else{
                max[a] ++;
                count[a] ++;
                count[b] --;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(countA).append("A");
        
        int countB = 0;
        for(int i = 0; i < count.length; i++){
        	//only look at digit that appears in secret
            if(max[i] > 0){
                if(count[i] < 0){
                	//digit appears more times in guess, then all appearances in secret are valid cows
                    countB += max[i];
                }else{
                	//digit appears more times in secret, the difference would be the appearances of digit in guess
                    countB +=max[i] - count[i];
                }
            }
        }
        
        sb.append(countB).append("B");
        
        return sb.toString();
    }
}
