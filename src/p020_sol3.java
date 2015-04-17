import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class p020_sol3 {
	private static final Map<Character, Character> hs = new HashMap<Character, Character>(){
		{put('(', ')');
		 put('{', '}');
		 put('[', ']');
		}
	};
	public static boolean isValid(String s) {
		 Stack<Character> stack = new Stack<>();
		 for(char c: s.toCharArray()){
			 if(hs.containsKey(c)){//left
				 stack.push(c);
			 }else if (stack.isEmpty() || hs.get(stack.pop()) != c){
				 return false;
			 }
		 }
		 return stack.isEmpty();
	 }
}
