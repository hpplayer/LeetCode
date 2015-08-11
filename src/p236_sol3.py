# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param {TreeNode} root
    # @param {TreeNode} p
    # @param {TreeNode} q
    # @return {TreeNode}
    def lowestCommonAncestor(self, root, p, q):
        if root in (None, p, q):
            return root
        left = self.lowestCommonAncestor(root.left, p, q)
        right = self.lowestCommonAncestor(root.right, p, q)
        
        #if we have valid value from both left and right subtree that means current node is parent 
        if left and right:
            return root
        #if we have valid value return from left subtree     
        if left:
            return left
        #if we have valid value return from right subtree
        if right:
            return right
        #this subtree does not contain target node
        return None