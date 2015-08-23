# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    class TreeNode(object):
     def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None
        
    def binaryTreePaths(self, root):
        """
        :type root: TreeNode
        :rtype: List[str]
        """
        strs, nodes, result = [], [], []
        if not root:
            return result
        nodes.append(root)
        strs.append(str(root.val))
        
        while len(nodes):
            node = nodes.pop()
            temp = strs.pop()
            if not node.left and not node.right:
                result.append(temp)
                continue
            if node.left:
                strs.append(temp + "->" + str(node.left.val))
                nodes.append(node.left)
            if node.right:
                strs.append(temp + "->" + str(node.right.val))
                nodes.append(node.right)
        return result
                
sol = Solution()
a = sol.TreeNode(1)
b = sol.TreeNode(2)
c = sol.TreeNode(3)
a.left = b
a.right = c

print(sol.binaryTreePaths(a))