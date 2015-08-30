import java.util.HashMap;

/*
 * 
 * Design a data structure that supports the following two operations:
 * 
 * void addWord(word)
 * bool search(word)
 * 
 * search(word) can search a literal word or a regular expression string containing only letters `a-z` or `.`
 * A `.` means it can represent any one letter.
 * 
 * For example:
 * 
 *   addWord("bad")
 *   addWord("dad")
 *   addWord("mad")
 *   search("pad") -> false
 *   search("bad") -> true
 *   search(".ad") -> true
 *   search("b..") -> true
 * 
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 * 
 * click to show hint.
 * 
 * You should be familiar with how a Trie works. If not, please work on this problem: Implement Trie (Prefix Tree, p208) first.
 * 
 *               
 */

/**
 * This is my original AC solution
 * This problem is not hard if we understand the Trie data structure
 * The basic idea is that we firstly build a trie and our addWord() method is to insert word into this trie
 * For the search, if current char is non "." char, we do similar procedure as searchWord in trie. However, if current char is ".", then we will 
 * search all children of current Node. To speed the search, we will use a helper function that can take the start Node, so we can skip visited nodes
 * This is the basic idea, but there are several details in implementation
 * 
 * For example, when we search all children of current Node, we will have null nodes if we are using Array to store children nodes. Also, when we recursively
 * search the part of the string, we may finally have the empty string, for those cases, we need to check current word's "isWord" parameter to see if 
 * we have reached the end of a word in the trie. Besides, when we search the all children of current Node, if none of children call return true, it means 
 * current "." and its suffix does not exist in the Trie, we can safely return false
 *  
 *  
 * My original AC solution is not fast, sol2 is a similar solution but with a faster speed. In my point, sol2 is fast because its helper() will only process
 * valid Node and valid char, all other cases will lead to return false.
 * 
 * Update:
 * Based on my sol3, I found the key difference between sol1 and sol2 is the implementation of children nodes data struture.
 * Create HashMap for each node is slow while array is much faster!!!!!
 * 
 * Sol1 is my original AC recursive solution using HashMap
 * Sol2 is another AC recursive solution using array and is much faster
 * Sol3 is my original AC iterative solution using array(using HashMap will get LTE error)
 * Sol4 is the python version of Sol1 but with modifications
 * Sol5 is the python version of Sol3 (exact same body)
 * 
 * Based on the speed, sol2 and sol3 are recommended
 * @author hpPlayer
 * @date Aug 29, 2015 5:57:13 PM
 */
public class p211_sol1 {
	
      public static void main(String[] args){
    	  p211_sol1 test = new p211_sol1();
    	  //test.addWord("a");
    	  //System.out.println(test.search("."));
    	  
    	  test.addWord("at");
    	  test.addWord("and");
    	  test.addWord("an");
    	  test.addWord("add");
    	  System.out.println(test.search("a"));
    	  System.out.println(test.search(".at"));
    	  test.addWord("bat");
    	  System.out.println(test.search(".at"));
    	  System.out.println(test.search("an."));
    	  System.out.println(test.search("a.d."));
    	  System.out.println(test.search("b."));
    	  System.out.println(test.search("a.d"));
    	  System.out.println(test.search("."));
    	  
    	  /*
    	  test.addWord("bad");
    	  test.addWord("dad");
    	  test.addWord("mad");
    	  System.out.println(test.search("pad"));
    	  System.out.println(test.search("bad"));
    	  System.out.println(test.search(".ad"));
    	  System.out.println(test.search("b.."));
    	  System.out.println(test.search("..."));
    	  */
      }
	
	   public class TrieNode{
	        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
	        boolean isWord = false;
	    }
	    
	    TrieNode root = new TrieNode();
	    // Adds a word into the data structure.
	    public void addWord(String word) {
	        TrieNode curr = root;
	        for(char c : word.toCharArray()){
	            if (!curr.children.containsKey(c)){
	                curr.children.put(c, new TrieNode());
	            }
	            curr = curr.children.get(c);
	        }
	        curr.isWord = true;
	    }

	    // Returns if the word is in the data structure. A word could
	    // contain the dot character '.' to represent any one letter.
	    public boolean search(String word) {
	        return searchHelper(word, root);
	    } 
	    
	    public boolean searchHelper(String word, TrieNode node) {
	    	if(word.length() == 0 && node != null){
	    		return node.isWord;
	    	}
	        if(word == null || word.length() == 0 || node == null){
	        	return false;
	        }
	        TrieNode curr = node;
	        for(int i = 0; i < word.length(); i++){
	            if (word.charAt(i) == '.'){
	            	if(curr.children.keySet().size() == 0){
	            		return false;
	            	}
	                for(Character ch : curr.children.keySet()){
	                	//System.out.println(word.substring(0, i)+ ch + word.substring(i+1));
	                    boolean result = searchHelper(word.substring(i+1), curr.children.get(ch));
	                    if(result) return true;
	                }
	                return false;
	            }else{	            	
	                if(!curr.children.containsKey(word.charAt(i))){
	                    return false;
	                }
	            }
	            curr = curr.children.get(word.charAt(i));
	        } 
	        return curr.isWord;
	    } 
}
