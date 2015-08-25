class Solution(object):
    def shortestPalindrome(self, s):
        """
        :type s: str
        :rtype: str
        """
        temp = s + " " + s[::-1]
        table = [0] * len(temp)
        
        for i in range(1, len(table)):
            j = table[i-1]
            while j > 0 and temp[j] != temp[i]:
                j = table[j-1] #get the next char of last left substring
            
            if temp[i] == temp[j]:
                j += 1
            
            table[i] = j
        
        return s[table[-1]:][::-1] + s 
    
sol = Solution()
print(sol.shortestPalindrome("abcd"))
            
    
        