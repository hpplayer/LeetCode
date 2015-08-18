import math
class Solution:
    """I dont know why, but python 2.x version will return different result for negative divison:
    like -3/2 will return -2 in leetcode, and return -1 in 3.x version, so I have to add extra if-else 
    to return the correct solution"""
    # @param {string} s
    # @return {integer}
    def calculate(self, s):
        num = 0
        sign = "+"
        stack = []
        for i in range(len(s)):
            c = s[i]
            if c >= '0' and c <= '9': #isdigit() for python
                num = num * 10 + int(c)
            elif c == ' ':
                continue
            else:
                self.updateStack(stack, sign, num)
                sign = c
                num = 0
        self.updateStack(stack, sign, num)
        result = 0
        while len(stack):
            result += stack.pop()
        return result
        
    def updateStack(self, stack, sign, num):
        if sign == '*':
            stack.append(stack.pop() * num)
        elif sign == '/':
            s = stack.pop()
            a = 0
            if s*num < 0:
                a = math.fabs(s)/math.fabs(num)
                stack.append(-int(a))
            else:
                a = s / num
                stack.append(a)
        elif sign == '+':
            stack.append(num)
        elif sign == '-':
            stack.append(-num)
    