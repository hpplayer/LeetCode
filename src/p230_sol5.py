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
        stack = []
        curr = root
        while stack or curr:
            if curr:
                stack.append(curr)#add current node to last 
                curr = curr.left
            else:
                temp = stack.pop()#pop last node from stack
                k -= 1
                if k == 0:
                    return temp.val
                curr = temp.right
                

a = []
a.append(1)
a.append(2)
print(a)
a.pop()
print(a)