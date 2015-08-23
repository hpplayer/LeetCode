class Solution(object):
    def combinationSum3(self, k, n):
        """
        :type k: int
        :type n: int
        :rtype: List[List[int]]
        """
        temp = []
        result = []
        self.DFS(k, n, result, temp)
        return result
        
    def DFS(self, k, n, result, temp):
        if k == 0 and n == 0:
            """ In python, we can make a copy of list by using:
            new_list = old_list[:]"""
            result.append(temp[:])
        if k < 0 or n < 0:
            return
        end = 0 if not len(temp) else temp[-1]
        for num in range(end +1, 10):
            temp.append(num)
            self.DFS(k-1, n - num, result, temp)
            temp.pop()
        