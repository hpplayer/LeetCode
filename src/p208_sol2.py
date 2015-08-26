class TrieNode(object):
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.hs = {}
        self.isWord = False
        

class Trie(object):

    def __init__(self):
        self.root = TrieNode()

    def insert(self, word):
        """
        Inserts a word into the trie.
        :type word: str
        :rtype: void
        """
        curr = self.root
        for c in word:
            if c not in curr.hs:
                curr.hs[c] = TrieNode()
            curr = curr.hs[c]
        curr.isWord = True
        

    def search(self, word):
        """
        Returns if the word is in the trie.
        :type word: str
        :rtype: bool
        """
        return self.retrieve(word, True)
        

    def startsWith(self, prefix):
        """
        Returns if there is any word in the trie
        that starts with the given prefix.
        :type prefix: str
        :rtype: bool
        """
        return self.retrieve(prefix, False)
    
    def retrieve(self, word, isWord):
        curr = self.root
        for c in word:
            if c not in curr.hs:
                return False;
            curr = curr.hs[c]
        return curr.isWord if isWord else True

# Your Trie object will be instantiated and called as such:
# trie = Trie()
# trie.insert("somestring")
# trie.search("key")