import java.util.ArrayList;
import java.util.List;
/**
 * My one pass solution !
 * The algorithm is still using DFS. We give a count for left and right 
 * if (right >= left) we can continue, otherwise we need return
 * Each node has two children "(" and ")"
 * 
 * We firstly recursively visiting "(" child, and add "(" as long as we have right >= left
 * After we reach the end, we need visit the ")" child, then go to up layer
 * 
 * After we reach the head(actually our head is always "("), we replace "(" by ")", i.e. start search right node
 * 
 * Remark: 
 * 1) head is always "(", so our DFS begin with (left -1, right)
 * 2) we use a StringBuffer here, in which when we want to delete current Node, we need use 
 * command "deleteCharAt()"
 * 3) my output should be ((())) => (( ) ())=> (( )) ()
 * @author hpPlayer
 * @date Mar 6, 2015 11:45:46 AM
 */

public class p022_01 {
	public static void main(String[] args){
		List<String> list = generateParenthesis(3);
	System.out.println(list);
	}
	
    public static List<String> generateParenthesis(int n) {
    	if(n <= 0) return null;
        List<String> list = new ArrayList<String>();
        StringBuffer sb = new StringBuffer();
        sb.append("(");
        generateP(n-1, n, list, sb);
        return list;
    }
    
    public static void generateP(int left, int right, List<String> result, StringBuffer sb){
        if(right <0 || left < 0 || right < left) {
        	return;
        }
        if(left == 0 && right == 0){
        	System.out.println(sb.toString());
            result.add(sb.toString());
            return; 
        }
   
        if(right >= left){
         	sb.append("(");
         	//System.out.println("left before" + left);
            //left--;
           // System.out.println("left after" + left);
            generateP(left-1, right, result, sb);
           // System.out.println("before " + sb.toString());
            sb.deleteCharAt(sb.length()-1);
           // System.out.println("after " + sb.toString());
           // left ++;
          //  System.out.println(sb.toString());
            
            
            sb.append(")");
            //right --;
            generateP(left, right-1, result, sb);
            sb.deleteCharAt(sb.length()-1);
            //right --;
        }
       
       
        }

}
