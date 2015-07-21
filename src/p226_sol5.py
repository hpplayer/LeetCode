# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param {TreeNode} root
    # @return {TreeNode}
    """
    python is awesome
    In java, when define left, I need use temp to hold left before it changes
    In python, we can directly swap left and right without a temp variable
    
    Remark:
    1)we also need call invertTree on self, so python will know we are doing methods under same 
    self instance
    2)"self" is inmportant when we are calling functions inside a class
    """
    def invertTree(self, root):
        if root is None:
            return root
        root.left, root.right = self.invertTree(root.right), self.invertTree(root.left)
        return root
        

    

        