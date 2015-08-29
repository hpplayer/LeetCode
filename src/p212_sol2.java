
public class p212_sol2 {
	public class TrieNode{
		boolean isWord;
		TrieNode[] children = new TrieNode[26];
	}
	
	public class Trie{
		TrieNode root;
		public void insert(String s){
			TrieNode curr = root;
			for(char c : s.toCharArray()){
				if (curr.children[c-'0'] == null){
					curr.children[c-'0'] = new TrieNode();
				}
				curr = curr.children[c-'0'];
			}
			curr.isWord = true;
		}
		
	}
}
