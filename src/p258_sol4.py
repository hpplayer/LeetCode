class Solution:
    # @param {integer} num
    # @return {integer}
    def addDigits(self, num):
        s = str(num)
        while len(s) > 1:
            sum = 0
            for c in s:
                sum += int(c)
            s = str(sum)
        return int(s)