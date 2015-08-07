import re
class Solution:
    # @param {string} input
    # @return {integer[]}
    def diffWaysToCompute(self, input):
        opr = re.split('\d+', input)
        num = re.split('\+|\-|\*',input)
        opr = opr[1:-1] #escape head and tail space
        d = len(num)
        dp = [[[] for i in range(d)] for j in range(d)]
        print(dp)
        op = {'+': lambda x,y: x+y, '-': lambda x,y: x-y, '*': lambda x,y: x*y}
        for i in range(d):
            dp[i][i].append(int(num[i]))
        for l in range(2, d+1):
            for j in range(d+1-l):
                    dp[j][j+l-1]=[op[opr[j+k-1]](x,y)
                    for k in range(1,l)
                    for x in dp[j][j+k-1]
                    for y in dp[j+k][j+l-1]]
                        
        return dp[0][d-1]
                         
test = Solution()
print(test.diffWaysToCompute("2-1-1"))