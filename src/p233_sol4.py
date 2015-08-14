class Solution:
    # @param {integer} n
    # @return {integer}
    def countDigitOne(self, n):
        if n <= 0:
            return 0
        if n == 1:
            return 1
        base = 10 ** (len(str(n)) -1)
        
        FirstDigit = int(n/base)
        extraOnes = 0
        if FirstDigit == 1:
            extraOnes = n - base + 1
        else:
            extraOnes = base
        
        return self.countDigitOne(base - 1)* FirstDigit + extraOnes + self.countDigitOne(n - base * FirstDigit)
        