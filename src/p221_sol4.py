class Solution:
    # @param {character[][]} matrix
    # @return {integer}
    def maximalSquare(self, matrix):
        if not matrix:
            return 0
        m, n = len(matrix), len(matrix[0])
        dp = [0] * n #remember this step to build known size of array 
        result = 0
        for i in range(m):
            for j in range(n):
                if j == 0:
                    prev = dp[0]
                    dp[0] = int(matrix[i][0])
                    result = max(dp[0], result)
                else:
                    temp = dp[j]
                    if matrix[i][j] == '1':
                        dp[j] = min(dp[j-1], dp[j], prev) + 1
                        result = max(dp[j], result)
                        prev = temp
                    else:
                        dp[j] = 0
                    pre = temp   
        return pow(result, 2) 
    
sol = Solution()
test =[[]]
print(sol.maximalSquare(test))