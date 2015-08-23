# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def binaryTreePaths(self, root):
        """
        :type root: TreeNode
        :rtype: List[str]
        """
        if not root:
            return []
        result = []
        self.DFS(root, "", result)
        return result
        
    def DFS(self, root, string, result):
        #leaf node
        string += str(root.val)
        if not root.left and not root.right:
            result.append(string)
            return
        
        if root.left:
            self.DFS(root.left, string + "->", result)
        if root.right:
            self.DFS(root.right, string + "->", result)            
            