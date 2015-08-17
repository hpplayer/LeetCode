class Solution:
    # @param {string} s
    # @return {integer}
    def calculate(self, s):
        op_s = []
        int_s = []
        i = len(s)-1
        while i >= 0:
            c = s[i]
            if c == '+' or c == '-' or c == ')':
                op_s.append(c)
            elif c >= '0' and c <= '9':
                temp = ""
                while i >= 0 and s[i] >= '0' and s[i] <= '9':
                    c = s[i]
                    temp = c + temp
                    i -= 1
                int_s.append(int(temp))
                i+=1
            elif c == '(':
                while op_s[-1] != ')':
                    self.helper(op_s, int_s)
                op_s.pop()
            i -= 1
        while len(op_s):
            self.helper(op_s, int_s)
        return int_s.pop()
            
    def helper(self, op_s, int_s):
        prev = int_s.pop()
        post = int_s.pop()
        sign = op_s.pop()
        if sign == '-':
            post = -post
        int_s.append(prev + post)
    
sol = Solution()
print(sol.calculate("2147483647"))