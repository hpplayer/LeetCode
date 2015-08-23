class Solution(object):
    def combinationSum3(self, k, n):
        """
        :type k: int
        :type n: int
        :rtype: List[List[int]]
        """
        
        """ a couple of improvements made here:
        1) instead of inner class, here we uses a tuple to bundle k, n, path
        2) we will not insert new node into the stack, if new node.num + sum already > n
        
        So this iterative solution is more elegant than my sol4
        """
        result = []
        stack = [(0, 1, [])]
        while stack:
            sum, end, path = stack.pop()
            if sum == n and len(path) == k:
                result.append(path)
                continue
            if len(path) >= k:
                continue
            for num in range(end, 10):
                temp = sum + num
                if temp > n:
                    break
                stack.append((temp, num+1, path + [num]))
        return result
                
                    