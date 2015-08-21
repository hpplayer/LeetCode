class Solution(object):
    def nthUglyNumber(self, n):
        """
        :type n: int
        :rtype: int
        """
        ugly = []
        ugly.append(1)
        i2 = i3 = i5 = 0
        while len(ugly) < n:
            #print(ugly)
            #get the last potenital candidate in the list
            while ugly[-1] >= ugly[i2]*2:
                i2 += 1
            while ugly[-1] >= ugly[i3]*3:
                i3 += 1
            while ugly[-1] >= ugly[i5]*5:
                i5 += 1
            #create the new ugly number and insert the smallerst one
            ugly.append(min(ugly[i2]*2, ugly[i3]*3, ugly[i5]*5))
        return ugly[-1] 
sol = Solution()
print(sol.nthUglyNumber(5))