import java.util.Arrays;

/**
 * This is an old question that I have done before 
 * But it has many boundary case to consider.
 * Like we only have " ",
 * we only have one string like "a"
 * 
 * We also need to be cautious about adding " "
 * We can only add " " when we know there are next string, otherwise we don't need add " "(if add it " " without next string, our result will
 * become "a ")
 * 
 * For the case that we only have one string "a", we should process it before the loop, so that we can treat other cases as same
 * 
 * @author hpPlayer
 * @date Apr 7, 2015 12:54:23 AM
 */

public class p151_sol1 {
	public static void main(String[] args){
		System.out.println(reverseWords("  a  b"));
	}
    public static String reverseWords(String s) {
        if(s== null || s.length() == 0) return s;
        String[] strAry = s.split("\\s+");
        if(strAry.length == 0) return "";//only contain " ";
        System.out.println(Arrays.toString(strAry));
        StringBuffer sb = new StringBuffer();
        sb.append(strAry[strAry.length-1]);
        for(int i = strAry.length-2; i >=0; i--){
        	//System.out.println(strAry[i]);
        	//since this is string array, so we can call isEmpty on string
        	if(!strAry[i].isEmpty()){//only add " "when we have next
        	  	sb.append(" ");
                sb.append(strAry[i]);
        	}
      
            
        }
        return sb.toString();
    }
}
