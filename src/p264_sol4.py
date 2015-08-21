class Solution(object):
    def nthUglyNumber(self, n):
        """
        :type n: int
        :rtype: int
        """
        ugly = [0] * n
        ugly[0] = 1
        index2 = index3 = index5 = 0
        num2, num3, num5 = 2, 3, 5
        
        for i in range(1, n):
            newUgly = min(num2, num3, num5)
            ugly[i] = newUgly
            if newUgly == num2:
                index2 += 1
                num2 = 2 * ugly[index2]
            if newUgly == num3:
                index3 += 1
                num3 = 3 * ugly[index3]
            if newUgly == num5:
                index5 += 1
                num5 = 5 * ugly[index5] 
        return ugly[n-1]

sol = Solution()


print(sol.nthUglyNumber(5))