# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
"""
In python, none is redefined as None
and it is singleton, so we can use "is None" to check if it is null
"""
class Solution:
    # @param {TreeNode} root
    # @return {TreeNode}
    def helper(self, node):
        if node is None:
            return
        temp = node.left
        node.left = node.right
        node.right = temp
        self.helper(node.left)
        self.helper(node.right)
        
    def invertTree(self, root):
        self.helper(root)
        return root