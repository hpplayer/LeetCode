# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def countHeight(self, root):
        height = 0
        while root:
            root = root.left
            height += 1
        return height
        
    def countNodes(self, root):
        if not root:
            return 0
        """
        :type root: TreeNode
        :rtype: int
        """
        left = self.countHeight(root.left)
        right = self.countHeight(root.right)
        
        if left == right:
            return (1 << left) + self.countNodes(root.right)
        else:
            return (1 << right) + self.countNodes(root.left)
        