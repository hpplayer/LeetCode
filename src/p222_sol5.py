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
        """
        :type root: TreeNode
        :rtype: int
        """
        num = 0
        while root:
            left = self.countHeight(root.left)
            right = self.countHeight(root.right)
            if left == right:
                num += 1 << left
                root = root.right
            else:
                num += 1 << right
                root = root.left
        return num
        