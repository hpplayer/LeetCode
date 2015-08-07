import re
class Solution:
    # @param {string} input
    # @return {integer[]}
    """DP solution, I rewrite it and add some comments. The original version is attached below
    The original version uses several advanced techniques in Python, and not easy to understand
    But it is a good material to learn DP and Python."""
    
    """
    I also rewrite this DP solution on my own, see sol4 
    """
    def diffWaysToCompute(self, input):
        opr = re.split('\d+', input) #\d+ match any digit, split based on those match
        num = re.split('\+|\-|\*',input) #/ means "or" here, \match any \+ or \* or \-, split based on those match
        print(num)
        opr = opr[1:-1] #escape head and tail space
        d = len(num)
        #dp matrix, each matrix cell contains one list of possible results
        dp = [[[] for i in range(d)] for j in range(d)]
        print(dp)
        op = {'+': lambda x,y: x+y, '-': lambda x,y: x-y, '*': lambda x,y: x*y}
        for i in range(d):
            dp[i][i].append(int(num[i]))
            
        #from 2 numbers to d numbers, since we already got pure digits, now we will play around signs
        for l in range(2, d+1):
            ##start point from 0 to d-l, since we must have l chars after this start point
            for j in range(d+1-l):
                    for k in range(1,l):
                        #scan all possible values in j : j+k-1, totally k numbers, from j to j + k, first half
                        for x in dp[j][j+k-1]:
                            #scan all possible values in j+k:j+l-1, totally l - k numbers, from j + k to j + l, second half   
                            for y in dp[j+k][j+l-1]:
                                dp[j][j+l-1].append(op[opr[j+k-1]](x,y))     
                        """ or simply write as (list comprehension):
                        dp[j][j+l-1]=[op[opr[j+k-1]](x,y)
                                      for k in range(1,l) for x in dp[j][j+k-1] for y in dp[j+k][j+l-1]]
                        """
        return dp[0][d-1]


"""

import re
class Solution:
    # @param {string} input
    # @return {integer[]}
    def diffWaysToCompute(self, input):
        num=re.split('\+|-|\*',input)
        opr=re.split('\d+',input)
        opr=opr[1:-1]
        d=len(num)
        dp=[[[]for i in range(d)] for j in range(d)]
        op={'+':lambda x,y:x+y, '-':lambda x,y:x-y, '*':lambda x,y:x*y}
        for i in range(d):
            dp[i][i].append(int(num[i]))
        for l in range(2,d+1):
            for j in range(d+1-l):
                    dp[j][j+l-1]=[op[opr[j+k-1]](x,y)
                                      for k in range(1,l) for x in dp[j][j+k-1] for y in dp[j+k][j+l-1]]
        return dp[0][d-1]
        
"""
                       
test = Solution()
print(test.diffWaysToCompute("0"))
S = {x**2 for x in range(10)}
print(S)

for i in range(2, 8):
    for j in range(i*2, 50, i):
        j
        
g = lambda x : x**2

print(g(8))