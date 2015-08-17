class Solution:
    # @param {string} s
    # @return {integer}
    def __init__(self):
        self.i = 0
    #recursive version
    def calculate(self, s):
        isNeg = False
        result = 0
        while self.i < len(s) and s[self.i] != ')':
            c = s[self.i]
            if c == ' ' or c == '+':
                self.i += 1
            elif c == '-':
                self.i += 1
                isNeg = True
            elif c == '(':
                self.i+= 1
                result += -self.calculate(s) if isNeg else self.calculate(s)
                isNeg = False #reset
            else:
                num = 0
                while self.i < len(s) and s[self.i] >= '0' and s[self.i] <= '9':
                    num = 10*num + (int(s[self.i]) - int('0'))
                    self.i += 1
                result += -num if isNeg else num
                isNeg = False #reset               
        self.i += 1
        return result

sol = Solution()
print(sol.calculate("2-1 + 2 "))