class Solution(object):
    class Node(object):
        def __init__(self, sum, k):
            self.sum = sum
            self.k = k
            self.path = []
    def combinationSum3(self, k, n):
        """
        :type k: int
        :type n: int
        :rtype: List[List[int]]
        """
        stack = []
        newNode = self.Node(0, 0)
        stack.append(newNode)
        result = []
        while len(stack):
            temp = stack.pop()
            if temp.k == k and temp.sum == n:
                result.append(temp.path)
                continue
            if temp.k > k or temp.sum > n:
                continue
            end = 0 if not len(temp.path) else temp.path[-1]
            for num in range(end + 1, 10):
                newNode = self.Node(temp.sum + num, temp.k + 1)
                newNode.path = temp.path[:]
                newNode.path.append(num)
                stack.append(newNode)
        return result
    
sol = Solution()
print(sol.combinationSum3(2, 6))