"""The direct translate from sol1 to python will get Memory Exceed Limit Error
So here I modify the code
Basically, we disgard the TrieNode class
"""
class WordDictionary(object):
    def __init__(self):
        """
        initialize your data structure here.
        """
   
        """
        replace TrieNode class with {} and indicator #
        """
        self.trie = {}
        

    def addWord(self, word):
        """
        Adds a word into the data structure.
        :type word: str
        :rtype: void
        """
        curr = self.trie
        for c in word:
            if c not in curr:
                curr[c] = {}
            curr = curr[c]
        curr['#'] = '#'

    def search(self, word, node=None):
        """
        Returns if the word is in the data structure. A word could
        contain the dot character '.' to represent any one letter.
        :type word: str
        :rtype: bool
        """
        #initial case, set node to root 
        if node == None:
            node = self.trie
        #reach the end of word
        if not word:
            if "#" in node:
                return True
            else:
                return False
        c = word[0]
        #search each child cc
        if c == '.':
            for cc in node:
                if cc != '#' and self.search(word[1:], node[cc]):
                    return True
        elif c in node: #normal char, and valid children
            return self.search(word[1:], node[c])
        return False #other cases
# Your WordDictionary object will be instantiated and called as such:
# wordDictionary = WordDictionary()
# wordDictionary.addWord("word")
# wordDictionary.search("pattern")

sol = WordDictionary()
sol.addWord("a")
print(sol.search("."))