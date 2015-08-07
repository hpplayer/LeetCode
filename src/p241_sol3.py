class Solution:
    # @param {string} input
    # @return {integer[]}
    """This is same recursion as sol1, but here we can simplify it to few codes thanks to Python """
    """Because a+b, a-b or a*b are all lists, so when we return them we need "[]" around them """
    """Since we transfer data as list, so even we reach the boundary case (pure ints), we have to use [] to make it a list"""
    def diffWaysToCompute(self, input):
        return [a+b if c == '+' else a-b if c == '-' else a*b
                for i, c in enumerate(input) if c in '+-*' #enumerate help get index of current char
                for a in self.diffWaysToCompute(input[:i]) #a is first part b is second part
                for b in self.diffWaysToCompute(input[i+1:])] or [int(input)] # in case input is pure digits, return it directly

    
    
    """It can be further simplified by using eval() function"""
    def diffWaystoCompute(self, input):
        return[eval('a' + c +'b') 
               for i, c in enumerate(input)if c in "+-*"
               for a in self.diffWaystoCompute(input[:i])
               for b in self.diffWaystoCompute(input[i+1:])] or [int(input)]
                   
test = Solution()
print(test.diffWaysToCompute("2-1-1"))



print([a for a in[1,2,3]])
