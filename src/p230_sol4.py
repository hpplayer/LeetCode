# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param {TreeNode} root
    # @param {integer} k
    # @return {integer}
    def kthSmallest(self, root, k):
        self.k = k #notice how we define object variable
        return self.DFS(root)
    def DFS(self, root):
        if not root:
            return 0
        result = self.DFS(root.left)
        if self.k == 0:
            return result
        self.k -= 1
        if self.k == 0:
            return root.val
        return self.DFS(root.right)