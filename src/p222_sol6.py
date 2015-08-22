# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def countNodes(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        if not root:
             return 0
        left = right = root
        height = 0
        while right:
            left = left.left
            right = right.right
            height += 1
        if not left:
            return (1 << height) -1
        return self.countNodes(root.left) + 1 + self.countNodes(root.right)