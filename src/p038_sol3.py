class Solution:
    # @param {integer} n
    # @return {string}
    def countAndSay(self, n):
        return self.DFS(str(1), n)
        
    def DFS(self, s, n):
        if n == 1:
            return s
        temp, prev, count  = "", s[0], 1 #remark how I do multiple assignments in one line
        for i in range(1, len(s)):
            if s[i] == prev:
                count += 1
            else:
                temp += str(count)
                temp += prev
                count = 1
            prev = s[i]
        #dont forget the last set of chars
        temp += str(count)
        temp += prev
        return self.DFS(temp, n-1)
    