import collections
class TrieNode(object):
    def __init__(self):
        self.hs = collections.defaultdict(TrieNode)
        self.isWord = False   

class Solution(object):
    def __init__(self):
        self.root = TrieNode()
        self.result = []
        
    def insert(self, word):
        curr = self.root
        for c in word:
            curr = curr.hs[c]
        curr.isWord = True
        
    def findWords(self, board, words):
        """
        :type board: List[List[str]]
        :type words: List[str]
        :rtype: List[str]
        """
        for word in words:
            self.insert(word)
        
        for i in range(len(board)):
            for j in range(len(board[0])):
                    self.DFS(board, i, j, self.root)
                    
        return self.result
        
    def DFS(self, board, x, y, node, word=''):# default value for parameter word
        if node.isWord:
            self.result.append(word)
            node.isWord = False
        
        xHelper = [0,0,1,-1]
        yHelper = [1,-1,0,0]
        
        for i in range(4):
            newX = x + xHelper[i]
            newY = y + yHelper[i]
            if 0<= newX < len(board) and 0 <= newY < len(board[0]):        
                    if 0<= newX < len(board) and 0 <= newY < len(board[0]):
                        c = board[newX][newY]
                        child = node.hs.get(c)
                        if child is not None:
                            word += c
                            board[newX][newY] = None
                            self.DFS(board, newX, newY, child, word)
                            board[newX][newY] = c
        
        """
        if 0<= x < len(board) and 0 <= y < len(board[0]):  
            c = board[x][y]
            child = node.hs.get(c)
            if child is not None:
                board[x][y] = None
                self.DFS(board, x+1, y, child, word + c)
                self.DFS(board, x-1, y, child, word + c)
                self.DFS(board, x, y+1, child, word + c)
                self.DFS(board, x, y-1, child, word + c)
                board[x][y] = c
        """      
board = [["a"], ["a"]]
sol = Solution()
print(sol.findWords(board, ["a"]))