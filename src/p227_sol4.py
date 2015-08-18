class Solution:
    # @param {string} s
    # @return {integer}
    def calculate(self, s):
        num_s = []
        op_s = []
        i = len(s) - 1
        while i >=0:
            if s[i] >= '0' and s[i] <= '9':
                num = ""
                while i >= 0 and s[i] >= '0' and s[i] <= '9':
                    num = s[i] + num
                    i -= 1
                i+=1
                num_s.append(int(num))
            elif s[i] == '*' or s[i] == '/':
                op_s.append(s[i])
            elif s[i] == '+' or s[i] == '-':
                while len(op_s) and (op_s[-1] == '*' or op_s[-1] == '/'):
                    self.helper(num_s, op_s)
                op_s.append(s[i])
            i -= 1
        while len(op_s):
            self.helper(num_s, op_s)
        return num_s.pop()
        
    def helper(self, num_s, op_s):
        prev = num_s.pop()
        if not len(num_s):
            num_s.append(prev)
            return
        post = num_s.pop()
        op = op_s.pop()
        if op == '*':
            num_s.append(prev * post)
        elif op == '/':
            num_s.append(prev/post)
        elif op == '-':
            num_s.append(prev - post)
        else:
            num_s.append(prev + post)
               
sol = Solution()
print(sol.calculate("14-3/2"))