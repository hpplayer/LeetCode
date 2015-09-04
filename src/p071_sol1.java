import java.util.Stack;

/*
Simplify Path 

Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"

click to show corner cases.

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".
*/	
		

/**
 * This is my original AC solution without help
 * First let my clarify what are those symbols in path
 * We only have 3 path signs "/", "." and ".."
 * All all signs are just filename like "...", "ab.."
 * Of course, we may have some duplicate like "////", just treat them like "/"
 * 
 * Path signs:
 * 1) "/" is the director separator, so the last "/" usually can be omit, because we have not assigned a new directory after
 * it, and thus we don't need separator. Example "cd /a/" can be simplified to "cd /a"
 * 2) "." means stay in current directory, thus "/a/./././././" will still stay in path "/a" or namely "/a/"
 * If we say "./././././" it will still stay in current path
 * 3) ".." means parent path, so if we assign a directory before the "..", the ".." will lead us to go back to that directory
 * 
 * After knowing those background knowledge, let's start with this program.
 * It seems we need a structure to record the path we have visited, thus if we found current path sign is "..", then we need go 
 * back and retrieve this path. Stack is a perfect data structure to do this operation. If current sign is "..", we will pop 
 * previous director in the stack
 * 
 * My algorithm here does the same thing. My main loop goes through the whole string and stops at each "/", then it will search
 * the substring before next "/", if substring is ".", then do nothing, if substring is "..", then pop the previous directory in
 * the stack, if substring is anything else, then just push to the stack. After the scan of string is done, we pop directories from
 * stack, and build our simplified string
 * 
 * My program here also inserts the "/" into the stack, which may not necessary, I will try to modify it to remove insertion of "/"
 * 
 * Remark:
 * Use stringBuilder can speed up the string build
 * 
 * Update:
 * I have modified the code
 * now simplifyPath() is a perfect program whose stack only contains valid directory
 * 
 * I check other's code, their ideas are exactly same as mine but implemented in a little different way
 * Like they split string with "/" and use deque to replace stack, so that when we build result string, we don't need to build
 * backward
 * 
 * My sol1 is already a decent solution, sol2 is also a good solution
 * So both sol1 and sol2 are recommended
 * @author hpPlayer
 * @date Sep 4, 2015 2:46:33 PM
 */

public class p071_sol1 {
	public static void main(String[] args){
		//String path = "/";
		String path = "/a/./b///../c/../././../d/..//../e/./f/./g/././//.//h///././/..///";//should output /e/f/g
		//String path = "/..";
		//String path = "/a/./b/../../c/";  //output "/.."
		//String path = "/abc/...";
		//String path = "/..."; //should output /...
		//System.out.println("/".equals('/'));
		System.out.println(new p071_sol1().simplifyPath(path));
	}
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<String>();//stack will only contain directory
        for(int i = 0; i < path.length(); i++){
        	//System.out.println(stack);
        	if(path.charAt(i) == '/'){//we will stop at each directory seperator
        		StringBuilder temp = new StringBuilder();
        		while(i+1 <= path.length() - 1 && path.charAt(i+1) != '/'){//don't reach tail, and we search string before next "/"
        			i++;//move pointer
        			temp.append(path.charAt(i));
        		}
        		if(temp.length() > 0){//if we have substring between "/" and "/"
        			if(temp.toString().equals("..")){//if it is "..",  then we need pop (go to) last directory
        				//we may have extreme case like /.., it means we go back to root, which does not have
        				//string representation in our stack, so we need check if this is such case
            			if(!stack.isEmpty()) stack.pop();//pop last dir
        			}else if(temp.toString().equals(".")){//stay 
        				continue;
        			}else{//otherwise, insert new directory into stack
        				stack.push(temp.toString());
        			}
        		}
        	}
        }

        StringBuilder result = new StringBuilder();
        //System.out.println(stack);
        while(!stack.isEmpty()){//build simplified string, we will add in the form of "/" + "xxxx" to meet the requirement
        	result.insert(0, "/" + stack.pop());
        }
        
        //if we have nothing in the stack and result string is still 0
        //in such case, we manually add "/" to form the root
        if(result.length() == 0) result.append("/");
        return result.toString();
        
    }
}
