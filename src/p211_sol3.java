import java.util.Stack;
/**
 * My AC iterative solution.
 * In my previous trial, I use the HashMap to memorize the children nodes, but it turns out to be TLE error
 * In this version, I changed the HashMap to TrieNode[], then got AC with same basic idea
 * 
 * The main idea:
 * We create a helper class, which can tell us the TrieNode and its corresponding index in the string. This is like the searchHelper() in sol1, which 
 * we pass a Node and substring to speedup the search
 * 
 * If the index reaches the end of word, then we check current Node's isWord parameter to see if we also have a word ending in the trie.
 * If the index is still within the range of word, then we add new Node(s) into the stack based on current char. If current Char is not included in current node's
 * children then we simply skip current while loop and go to next loop. Similarly, when current char is ".", we only push non null TrieNode into the stack
 * For all other cases, we just let the loop continue
 * 
 * @author hpPlayer
 * @date Aug 29, 2015 7:49:18 PM
 */
public class p211_sol3 {
	 public static void main(String[] args){
		  p211_sol3 test = new p211_sol3();
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
	 }

	  
	   public class TrieNode{
		   TrieNode[] children = new TrieNode[26];
	        boolean isWord = false;
	    }
	    
	    public class Node{
	        TrieNode node;
	        int index;
	        public Node(TrieNode node, int index){
	            this.node = node;
	            this.index = index;
	        }
	    } 
		TrieNode root = new TrieNode(); 
		
	    // Adds a word into the data structure.
	    public void addWord(String word) {
	        TrieNode curr = root;
	        for(char c : word.toCharArray()){
	            if (curr.children[c - 'a'] == null){
	                curr.children[c - 'a'] = new TrieNode();
	            }
	            curr = curr.children[c - 'a'];
	        }
	        curr.isWord = true;
	    }

	    // Returns if the word is in the data structure. A word could
	    // contain the dot character '.' to represent any one letter.
	    public boolean search(String word) {
	        Stack<Node> stack = new Stack<Node>();
	        stack.push(new Node(root, -1));//-1 since root itself does not contain char but only children
	        while(!stack.isEmpty()){
	            Node temp = stack.pop();
	            int index = temp.index;
	            TrieNode node = temp.node;
	            //reach the end of word, as well as the path in trie, found match return true;
	            if(index == word.length() -1 && node.isWord){
	                return true;
	            }

	            //add Node into stack only if we have not reach the end of word
	            if(index < word.length() - 1){
	            	char c = word.charAt(index+1);
	                //a valid char
	                if(c != '.'){
	                    if(node.children[c - 'a'] != null){
	                    	stack.push(new Node(node.children[c - 'a'], index+1));
	                    }
	                }else{// current match is "."
	                    for(int i = 0; i < 26; i ++){
	                    	if(node.children[i] != null){
	                    		stack.push(new Node(node.children[i], index+1)); 
	                    	}
	                    }
	                }
	            }
	            
	        }
	        
	        return false;
	    }
}

//Your WordDictionary object will be instantiated and called as such:
//WordDictionary wordDictionary = new WordDictionary();
//wordDictionary.addWord("word");
//wordDictionary.search("pattern");
