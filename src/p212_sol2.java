import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
/**
 * Yes! This is an amazing AC iterative solution, and I havn't see any iterative solution on LeetCode yet.
 * DFS can be iterative implemented use stack. At some points, I thought queue would be a better structure to this solution, but then I found 
 * we want find a path starts from current node first, not include each neighbor of current node first. So what we are looking for is still DFS
 * The basic idea of iterative solution is similar to recursive solution. We visit current node, and add its neighbor if the neighbor and current node 
 * will compose a prefix existed in trie, then we add this neighbor into the stack
 * 
 * Here is the most important part in this recursive solution. How can we direct current node not visit the node that already in the path? We cannot simply
 * change the char value on board to "\0" since we are doing iterative call, we don't know when to change the char value back. For the same reason we cannot
 * use a global visited map. The correct solution is inserting the visited map into each node, like we did to other iterative solutions that we add the path
 * into each node.
 * 
 * Remark:
 * 1) When inserting neighbor candidate into the stack, we have to copy the current node's visited map to this neighbor candidate. Of course, we need a deep
 * copy not just the copy of reference. That's why I use a grid() to make a deep copy, the shortage is this step is time-consuming
 * 2) The best running time for my iterative solution is still very slow and will have the possibility to get LTE error. It is from deep copy of visited map
 * in inserting a new node into stack. But it is worthy, because this is an AC iterative solution!
 * 
 * @author hpPlayer
 * @date Aug 29, 2015 10:59:01 AM
 */
public class p212_sol2 {
	
	public static void main(String[] args){
		/*
		char[] a = {'a', 'b'};
		char[] b = {'a', 'a'};
		char[][] board = {a,b};
		String[] words = {"aba","baa","bab","aaab","aaa","aaaa","aaba"};
		*/
		
		//TrieNode t = new TrieNode();
		//System.out.println(Arrays.toString(t.children));
		//
		
		
		char[] a = {'a', 'a', 'a', 'a'};
		char[] b = {'a', 'a', 'a', 'a'};
		char[] c = {'a', 'a', 'a', 'a'};
		char[][] board = {a,b, c};
		String[] words = {"aaaaaaaaaaaa","aaaaaaaaaaaaa","aaaaaaaaaaab"};
		
		
		//TEST 0 (normal test)
		/*
		char[] a = {'a', 'b'};
		char[] b = {'c', 'd'};
		char[][] board = {a,b};
		String[] words = {"ab","cb","ad","bd","ac","ca","da","bc","db","adcb","dabc","abb","acb"};
		*/
		//String[] words = {"aba","baa","bab","aaab","aaa","aaaa","aaba"};
		
		
		//TEST CASE 1 (test words with same starting alphabet)
		/*
		char[] a = {'a', 'b'};
		char[] b = {'c', 'd'};
		char[][] board = {a,b};
		String[] words = {"ab","cb","ad","bd","ac","ca","da","bc","db","adcb","dabc","abb","acb"};
		*/
		
		/* TEST CASE 1 (given sample test)
		char[] a = {'o','a','a','n'};
		char[] b = {'e','t','a','e'};
		char[] c = {'i','h','k','r'};
		char[] d = {'i','f','l','v'};
		char[][] board = {a,b,c,d};
		String[] words = {"oath","pea","eat","rain"};
		*/
		
		// TEST CASE 2  (test duplicate words)
		/*
		char[] a = {'a', 'a'};
		char[][] board = {a};
		String[] words = {"a"};
		*/
		for(String s : new p212_sol2().findWords(board, words)){
			System.out.println(s);
		}
	}
	
	public class Pair{
		int x;
		int y;
		public Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public class Node{
		Pair pair;
		String str;
		char oldC;
		boolean [][] visited;//directions
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
        List<String> result = new ArrayList<String>();
        Stack<Node> stack = new Stack<Node>();
    	int[] xHelper = {0, 0, 1, -1};
    	int[] yHelper = {1, -1, 0, 0};
    	
        //insert words into Trie
        Trie trie = new Trie();
        for (String s : words){
        	trie.insert(s);
        }
        for(int i = 0; i < board.length; i++){
        	for(int j = 0; j < board[0].length; j++){
        		if (trie.searchPrefix(board[i][j] + "")){
	    			if(trie.searchWord(board[i][j] + "")){
	    				result.add(board[i][j] + "");
	    			}
            		Node root = new Node();
            		root.pair = new Pair(i, j);
            		root.str = "" + board[i][j];
            		root.visited = new boolean[board.length][board[0].length];
            		root.visited[i][j] = true;
            		stack.push(root); 
            		
            		while(!stack.isEmpty()){
            			Node temp = stack.pop();
            	    	
            	    	for(int l = 0; l < 4; l++){
            	    		int newX = temp.pair.x + xHelper[l];
            	    		int newY = temp.pair.y + yHelper[l];
	            	    	if(newX < board.length && newX >= 0 && newY <board[0].length && newY >= 0 && !temp.visited[newX][newY]){
	            	    		if (trie.searchPrefix(temp.str + board[newX][newY])){
	            	    			if(trie.searchWord(temp.str + board[newX][newY])){
	            	    				result.add(temp.str + board[newX][newY]);
	            	    			}
	                        		Node curr = new Node();
	                        		curr.pair = new Pair(newX, newY);
	                        		curr.str = temp.str + board[newX][newY];
	                        		curr.visited = grid(temp.visited);
	                        		curr.visited[newX][newY] = true;
	                        		stack.push(curr);
	            	    		}
	            	    	}
            	    	}
            		}
        		}
        	
        	}
        }
    	
    	return result;
    }
    
    public boolean[][] grid(boolean[][] visited){
    	boolean[][] result = new boolean[visited.length][visited[0].length];
    	for(int i = 0; i < result.length;i ++){
    		for(int j =0; j < result[0].length; j++){
    			result[i][j] = visited[i][j];
    		}
    	}
    	
    	return result;
    }
    
	
	
}
