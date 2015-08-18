import math
class Solution:
    # @param {string} s
    # @return {integer}
    def calculate(self, s):
        s = s.replace(" ", "") #we can remove " " by calling replace()
        prev = i = result = 0
        sign = '+'
        while i < len(s):
            curr = 0
            while i < len(s) and s[i].isdigit():
                curr = curr * 10 + int(s[i])
                i+= 1
            if sign == '+':
                result += prev
                prev = curr
            elif sign == '-':
                result += prev
                prev = -curr
            elif sign == '*':
                prev = prev * curr
            elif sign == '/':
                if prev*curr < 0:
                    prev = -int(math.fabs(prev)/math.fabs(curr))
                else:
                    prev = prev / curr
            else:
                a = s / num
                stack.append(a)
                
                
                
            if i < len(s):
                sign = s[i]
                i+= 1
        result += prev
        return result
    
sol = Solution()
print(sol.calculate("14-3/2"))