class Solution:
    # @param {integer} num
    # @return {integer}
    def addDigits(self, num):
        if (len(str(num)) == 1):
            return num
        s, sum = str(num), 0
        for c in s:
            sum += int(c)
        return self.addDigits(sum)
    
sol = Solution()
print(sol.addDigits(38))