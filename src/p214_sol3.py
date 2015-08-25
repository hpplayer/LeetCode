class Solution(object):
    def shortestPalindrome(self, s):
        """
        :type s: str
        :rtype: str
        """
        left= 0
        
        #for right in range(len(s)-1, -1, -1):
        for right in reversed(range(len(s))):
            if s[right] == s[left]:
                left += 1
        
        if left == len(s):
            return s
        """s[left:] will get the right part of string, [::-1] will reverse that """
        return s[left:][::-1] + self.shortestPalindrome(s[:left]) + s[left:]
        
sol = Solution()
print(sol.shortestPalindrome("abb"))