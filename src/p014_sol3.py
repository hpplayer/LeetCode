class Solution:
    # @param {string[]} strs
    # @return {string}
    def longestCommonPrefix(self, strs):
        result = ""
        if len(strs) == 0: return result
        length = len(strs[0])
        for i in range(length):
            c = strs[0][i]
            for str in strs:
                if i >= len(str) or str[i] != c:
                    return str[:i]
        return strs[0]
                    