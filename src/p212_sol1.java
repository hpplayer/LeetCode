import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
Word Search II 

* 
* Given a 2D board and a list of words from the dictionary, find all words in the board.
* 
* Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" 
* cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
* 
* For example,
*   Given words = ["oath","pea","eat","rain"] and board = 
*   
*   [
*     ['o','a','a','n'],
*     ['e','t','a','e'],
*     ['i','h','k','r'],
*     ['i','f','l','v']
*   ]
*   
* Return ["eat","oath"].
* 
* Note:
* You may assume that all inputs are consist of lowercase letters a-z.
* 
* click to show hint.
* 
* You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?
* 
* If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. 
* What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not? 
* How about a Trie? If you would like to learn how to implement a basic trie, please work on this problem: 
* Implement Trie (Prefix Tree) first (p208).
*/

/**
 * This is my original AC solution without help, and it is a hard problem!
 * Ok I admit, I do lookup the hints come with the problem, and its simpler version Word Search 1 (p79)
 * 
 * This problem is labeled as a Hard problem due to the input word is changed from a single word to a set of words
 * If we are just searching a single word, our search can start only when current char is matched with the first char in the given word
 * However, if the given input is a set of words, then our search can start when current char is matched with the first char of any given word,
 * which means our search will become much much longer. So the hint of this problem suggests using Trie to smartly search the words. If current
 * candidate is not the prefix of any given word, then we should just stop the search and return back.
 * 
 * The main program is very similar with word search 1, but I add the extra Trie data structure to speed up the search.
 * Here are couple of optimizations I made in the program:
 * 1) To avoid the deadlock and simplify the problem, the problem states that the same cell will not be used twice in each word, so
 * we can smartly change the cell value to space " ", so that our search will not use cells which has been visited during this search
 * 2) Basically, we have four directions in search, so we can either call DFS four times in each recursion with four directions, or we can
 * use a xHelper, yHelper with a for loop to replace this repeat recursive calls.
 * 3) We may have duplicate return word like board = ['a', 'a'] and words = "a". To deal with this case, we can either add a remove() in the 
 * trie class to remove the word from trie or we can simply change the isWord value in the last TrieNode of the word, so there is no longer 
 * such word in the trie but only a never ended word prefix.
 * 
 * Some mistakes I made:
 * 1) The first mistake is that I forget to change the cell value back from space " " to original value when we done the DFS search.
 * This change should be done immediately after we done the DFS call, before we do further operations like return values or continue for loop
 * 2) The second mistake is that we need to be careful with duplicate return words. This problem has been talked above
 * 3) The third mistake is that our DFS may return a bunch of matched words that start with same prefix. So instead of return a single string
 * with each DFS call, we should have a list to contain all returned words
 * 
 * 
 * Maybe I need to introduce the basic idea of my solution
 * 
 * The main program will check each cell on the board, if the char in current cell is contained in any of word in input word set, we will start 
 * our DFS search. DFS search will go with four directions, and skip cells that we marked with space which means we have visited this cell in the DFS call
 * We will check if input char + current char compose a prefix that existed in our trie, if it is, then do the DFS on that cell
 * Finally, if at some points, our prefix has became a valid word in the trie, we will add it into our result. Of course, we need change the 
 * space " " back to its original value when we are done with the DFS search
 * 
 * I will try convert it from recursive version to iterative version, stay tuned!
 * @author hpPlayer
 * @date Aug 26, 2015 5:34:00 PM
 */
public class p212_sol1 {
	public static void main(String[] args){
		//TEST CASE 1 (test words with same starting alphabet)
		char[] a = {'a', 'b'};
		char[] b = {'c', 'd'};
		char[][] board = {a,b};
		String[] words = {"ab","cb","ad","bd","ac","ca","da","bc","db","adcb","dabc","abb","acb"};
		
		/* TEST CASE 1 (given sample test)
		char[] a = {'o','a','a','n'};
		char[] b = {'e','t','a','e'};
		char[] c = {'i','h','k','r'};
		char[] d = {'i','f','l','v'};
		char[][] board = {a,b,c,d};
		String[] words = {"oath","pea","eat","rain"};
		*/
		
		/* TEST CASE 2  (test duplicate words)
		char[] a = {'a', 'a'};
		char[][] board = {a};
		String[] words = {"a"};
		*/
		for(String s : new p212_sol1().findWords(board, words)){
			System.out.println(s);
		}
	}
	
    static class TrieNode{
        boolean isWord = false;
        HashMap<Character, TrieNode> hs = new HashMap<Character, TrieNode>();
        public TrieNode(){
            
        }
    }

	
    public static class Trie{
        TrieNode root;
        public Trie(){
            root = new TrieNode();
        }
        
        public void insert(String word){
            TrieNode curr = root;
            for(char c : word.toCharArray()){
                if (!curr.hs.containsKey(c)){
                    curr.hs.put(c, new TrieNode());
                }
                curr = curr.hs.get(c);
            }
            
            curr.isWord = true;
        }
        
        public boolean searchPrefix(String prefix){
            TrieNode curr = root;
            for(char c : prefix.toCharArray()){
                if(!curr.hs.containsKey(c)){
                    return false;
                }
                curr = curr.hs.get(c);
            }
            
            return true;
        }
        
        public boolean searchWord(String word){
            TrieNode curr = root;
            for(char c : word.toCharArray()){
                if(!curr.hs.containsKey(c)){
                    return false;
                }
                curr = curr.hs.get(c);
            }
            
            if(curr.isWord){
            	curr.isWord = false;//remove this word from trie
            	return true;
            }else{
            	return false;
            }
        }
    }
    
    
    public List<String> findWords(char[][] board, String[] words) {
    	Trie trie = new Trie();
    	for(String s : words){
    		trie.insert(s);
    	}
    	List<String> result = new ArrayList<String>();
    	for(int i = 0; i < board.length; i++){
    		for(int j = 0; j < board[0].length; j++){
				char temp = board[i][j];
    			if(trie.searchPrefix(temp + "")){
    				board[i][j] = '\0';
    				List<String> ls = new ArrayList<String>();
    				DFS(board, i, j, temp + "", trie, ls);
    				board[i][j] = temp;
    				result.addAll(ls);		
    			}
    		}
    	}
    	
    	return result;
    }
    
    public void DFS(char[][] board, int x, int y, String word, Trie trie, List<String> result){
    	/*
    	for(int i = 0; i < board.length; i++){
    		for(int j = 0; j < board[0].length; j++){
    			System.out.print(board[i][j] + " ");
    		}
			System.out.println();
    	}
    	System.out.println();
    	*/
    	
    	if (trie.searchWord(word)){   		
    		result.add(word);
    	}
    	
    	int[] xHelper = {0, 0, 1, -1};
    	int[] yHelper = {1, -1, 0, 0};
    	
    	for(int i = 0; i < 4; i++){
    		int newX = x + xHelper[i];
    		int newY = y + yHelper[i];
    		if(newX < board.length && newX >= 0 && newY <board[0].length && newY >= 0 && board[newX][newY] != '\0'){
    			if (trie.searchPrefix(word + board[newX][newY])){
    				char temp = board[newX][newY];
    				board[newX][newY] = '\0';
    				DFS(board, newX, newY, word + temp, trie, result);
    				board[newX][newY] = temp;	
    			}
    		}
    	}
    }
}
