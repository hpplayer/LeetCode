/**
 * This is a similar solution with sol1.
 * The main idea is similar, but it runs faster
 * It may because the helper() will only continue if current Node is non-null. My sol1 will only handle null node in next recursive call
 * 
 * Remark:
 * Notice even though our for loop in helper() will return false as long as current Node is null, we still need to check current Node is null or not 
 * in the final return statement. This is because we may have the case that current input string's length is empty(reach the end of input word) and we need 
 * to check if the corresponding path in trie has reached the end at the same time
 * @author hpPlayer
 * @date Aug 29, 2015 6:47:49 PM
 */
public class p211_sol2 {
    public class TrieNode{
        TrieNode[] children = new TrieNode[26];
        boolean isWord = false;
    }
        
    TrieNode root = new TrieNode();
	    
    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode curr = root;
        for(char c : word.toCharArray()){
            if (curr.children[c - 'a'] ==null){
                curr.children[c-'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
        }
        curr.isWord = true;        
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return searchHelper(word, root);
    }
    
    public boolean searchHelper(String word, TrieNode node){
        TrieNode curr = node;
        for(int i = 0; i < word.length(); i++){
            if(curr != null && word.charAt(i) != '.'){
                curr = curr.children[word.charAt(i) - 'a'];
            }else if (curr != null && word.charAt(i) == '.'){
                TrieNode temp = curr;
                for(int j = 0; j < 26; j++){
                    curr = temp.children[j];
                    if(searchHelper(word.substring(i+1), curr)){
                        return true;
                    }
                }
                
                return false;//if no children return true
            }else{//else means the curr == null, so we need turn back (else if (curr == null) will also give the same AC result)
                return false;
            }
        }
        //why we still to check curr? since we may not go through the for loop if the word's length is 0
        return curr != null && curr.isWord;
    }
}
//Your WordDictionary object will be instantiated and called as such:
//WordDictionary wordDictionary = new WordDictionary();
//wordDictionary.addWord("word");
//wordDictionary.search("pattern");