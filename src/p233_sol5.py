class Solution:
    # @param {integer} n
    # @return {integer}
    def countDigitOne(self, n):
        result, k = 0, 1
        while k <= n:
            result += (n/k + 8)/10 * k + (n/k%10 == 1) * (n%k + 1)
            k *= 10
        return result
    
print(len(10))