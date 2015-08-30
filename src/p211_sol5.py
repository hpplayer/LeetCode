class TrieNode(object):
    def __init__(self):
        self.children = [None] * 26
        self.isWord = False
        
class Node(object):
    def __init__(self, node, index):
        self.node = node
        self.index = index
        
class WordDictionary(object):
    def __init__(self):
        """
        initialize your data structure here.
        """
        self.root = TrieNode()

    def addWord(self, word):
        """
        Adds a word into the data structure.
        :type word: str
        :rtype: void
        """
        curr = self.root
        for c in word:
            if not curr.children[ord(c) - ord('a')]: #ord() to convert char to int
                curr.children[ord(c) - ord('a')] = TrieNode()
            curr = curr.children[ord(c) - ord('a')]
        curr.isWord = True

    def search(self, word):
        """
        Returns if the word is in the data structure. A word could
        contain the dot character '.' to represent any one letter.
        :type word: str
        :rtype: bool
        """
        stack = []
        stack.append(Node(self.root, -1))
        
        while len(stack) > 0:
            temp = stack.pop()
            index = temp.index
            node = temp.node
            if index == len(word) - 1 and node.isWord:
                return True
            
            if index < len(word) - 1:
                c = word[index+1]
                if c != '.':
                    if node.children[ord(c) - ord('a')]:
                        stack.append(Node(node.children[ord(c) - ord('a')], index + 1))
                else:
                    for cc in node.children:
                        if cc:
                            stack.append(Node(cc, index+1))
        return False
                        
            

# Your WordDictionary object will be instantiated and called as such:
# wordDictionary = WordDictionary()
# wordDictionary.addWord("word")
# wordDictionary.search("pattern")

sol = WordDictionary()
sol.addWord("a")
print(sol.search("a"))