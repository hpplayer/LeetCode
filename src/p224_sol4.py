class Solution:
    # @param {string} s
    # @return {integer}
    def calculate(self, s):
        stack = []
        num = result = 0
        sign = 1
        for c in s:
            if c <= '9' and c >= '0':
                num = num *10 + int(c)
            elif c == '+':
                result += sign * num
                #reset sign
                sign = 1
                num = 0
            elif c == '-':
                result += sign * num
                 #reset sign
                sign = -1
                num = 0 
            elif c == '(':
                stack.append(result)
                stack.append(sign)
                #start calculation in paranthese
                result = 0
                sign = 1
            elif c == ')':
                result += sign*num
                num = 0
                result *= stack.pop() #add sign
                result += stack.pop() #add result
                
        if num != 0:
            result += sign * num
        return result
                

sol = Solution()
print(sol.calculate("1 + 1"))