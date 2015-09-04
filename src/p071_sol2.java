import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * This solution is very similar to my solution sol1
 * But it implements in a little different way. Like split string based on "/+"
 * So after the split, the //// will become empty string, and we will put other path signs into every cells
 * We also use deque here, so when we poll string from the deq backward, we can also then build the string forward
 * 
 * But generally speaking, the main idea is same to my sol1, no significant improvement
 * @author hpPlayer
 * @date Sep 4, 2015 3:35:18 PM
 */


public class p071_sol2 {
	public static void main(String[] args){
		String path = "//////abcd";
		System.out.println(new p071_sol2().simplifyPath(path));
	}
    public String simplifyPath(String path) {
        Deque<String> deq = new LinkedList<String>();
        String[] paths = path.split("/+");//split from / to // to //// and etc
        System.out.println(Arrays.toString(paths));
        for(String s : paths){
            if (s.equals("..")){//pop (back to) previous dir in deq
                if(!deq.isEmpty()) deq.pollLast();
            }else if (s.equals(".") || s.length() == 0){//skip empty or "." 
                continue;
            }else{
                deq.offer(s);//otherwise it is a valid directoy, push to deq
            }
        }
        
        StringBuilder sb = new StringBuilder();
        if(deq.isEmpty()) return "/";//if deq is empty, it means we are in root, just return "/"
        while(!deq.isEmpty()){
            sb.append("/").append(deq.poll());
        }
        
        return sb.toString();
    }
}
