import java.util.HashMap;
/**
 * My original solution stores string in each TrieNode, then I compare substring one char by one char in each operation.
 * So I got LTE error. Also, my thought about search(String word) is wrong. The problem requires us return true if there exists such word 
 * in trie, which means we must found the exact match of string. EX: if we search ab, and trie has abc in it, then we should return false.
 * 
 * This solution is very clear. The boolean in the TrieNode make the search of prefix and search of word become more easier. We will change 
 * this value when inserting the word, and we will only change the value in the last TrieNode. Also we found the search of prefix and word
 * can be combined into one method but with flag indicating if we are searching the word or flag
 * 
 * Simple solution but very clear and powerful!
 * 
 * Remark:
 * 1)The hashMap structure can be replaced by the array since we know each TrieNode will only have at most 26 children 
 * 2)This is not an algorithm problem, so there are not many solutions to it. Most solutions are very similar
 * 
 * 
 * @author hpPlayer
 * @date Aug 25, 2015 6:36:31 PM
 */

public class p208_sol1 {
	public static void main(String[] args){
		Trie test = new Trie();
		test.insert("ab");
		test.search("a");
		test.startsWith("a");
	}
	
	static class TrieNode {
	    // Initialize your data structure here.
	    boolean isWord = false;//indicates the end of word
	    HashMap<Character, TrieNode> hs = new HashMap<Character, TrieNode>();
	    
	    public TrieNode() {
	 
	    }
	}

	public static class Trie {
	    private TrieNode root;

	    public Trie() {
	        root = new TrieNode();
	    }

	    // Inserts a word into the trie.
	    public void insert(String word) {
	        TrieNode curr = root;
	        //insert the word into trie one char by one char
	        for(int i = 0; i < word.length(); i++){
	            char c = word.charAt(i);
	            //if the new char is new to the path
	            if(!curr.hs.containsKey(c)){
	                curr.hs.put(c, new TrieNode());
	            }
	            
	            curr = curr.hs.get(c);
	        }
	        
	        //now curr should point to the last TrieNode which stores the last char in word and indicates the end 
	        curr.isWord = true;
	        
	    }

	    // Returns if the word is in the trie.
	    public boolean search(String word) {
	        return retrieve(word, true);
	    }

	    // Returns if there is any word in the trie
	    // that starts with the given prefix.
	    public boolean startsWith(String prefix) {
	        return retrieve(prefix, false);
	    }
	    
	    //since prefix and word use similar search method, we combine it together
	    public boolean retrieve(String word, boolean isWord){
	        TrieNode curr = root;
	        for(int i = 0; i < word.length(); i++){
	            char c = word.charAt(i);
	            if (!curr.hs.containsKey(c)){
	                return false;
	            } 
	            curr = curr.hs.get(c);
	        }
	        
	        return isWord? curr.isWord : true;
	    }
	}

	// Your Trie object will be instantiated and called as such:
	// Trie trie = new Trie();
	// trie.insert("somestring");
	// trie.search("key");
}
