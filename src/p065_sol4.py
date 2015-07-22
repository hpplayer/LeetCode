class Solution:
    # @param {string} s
    # @return {boolean}
    """
    AC Python solution of sol1
    Python can also remove space with:
    s = self.removeSpaces(s)
    """
    def isNumber(self, s):
        dest = len(s)
        pointer = 0
        while pointer < dest and s[pointer] == ' ':
            pointer += 1
            
        if pointer < dest and (s[pointer] == '+' or s[pointer] == '-'):
            pointer += 1
            
        isNumeric = False
        
        while pointer < dest and s[pointer].isdigit():
            pointer += 1
            isNumeric = True
            
        if pointer < dest and s[pointer] == '.':
            pointer += 1
            
        while pointer < dest and s[pointer].isdigit():
            pointer += 1
            isNumeric = True
            
        if isNumeric and pointer < dest and s[pointer] == 'e':
            isNumeric = False
            pointer += 1
            if pointer < dest and (s[pointer] == '+' or s[pointer] == '-') :
                pointer +=1
        
        while pointer < dest and s[pointer].isdigit():
            pointer += 1
            isNumeric = True
            
        while pointer < dest and s[pointer] == ' ':
            pointer += 1
            
        return isNumeric and  pointer == dest