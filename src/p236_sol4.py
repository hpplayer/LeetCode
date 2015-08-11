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
        left = self.DFS(root, p)
        right = self.DFS(root, q)
        ans = root
        while left and right:
            ans = left.popleft()
            right.popleft()
            if left and right and left[0] != right[0]:
                break
        return ans
    
    def DFS(self, root, n):
        stack = []
        path = collections.deque()
        stack.append(root)
        
        while(stack):
            temp = stack.pop()
            #if we have not found the node in the subtree then remove the root of this subtree from path
            if path and temp == path[-1]:
                path.pop() #pop removes rightmost element
            else:
                path.append(temp)
                if temp == n:
                    break
                #add node to stack as the post-search order
                stack.append(temp)
                if temp.right:
                    stack.append(temp.right)
                if temp.left:
                    stack.append(temp.left)
        return path
 