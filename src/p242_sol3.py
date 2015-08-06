class Solution:
    # @param {string} s
    # @param {string} t
    # @return {boolean}
    """ similar idea as to sol2, but here we use a hashmap instead of array to store count of alphabet """
    """ Actually we can only use one line code to solve this problem in python: return sorted(s) == sorted(t)"""
    def isAnagram(self, s, t):
        if len(s) != len(t):
            return False
        dic = {}
        for item in s:
            """
            dict.get(key, default=None)
            key -- This is the Key to be searched in the dictionary.
            default -- This is the Value to be returned in case key does not exist.
            """
            dic[item] = dic.get(item, 0) + 1 
        for item in t:
            dic[item] = dic.get(item, 0) - 1
        
        for _ in dic.values():
            if _ != 0:
                return False
        
        return True


sol = Solution()

print(sol.isAnagram("aa", "bb"))