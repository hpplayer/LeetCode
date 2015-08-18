class Solution:
    # @param {integer} num
    # @return {boolean}
    def isUgly(self, num):
        if num <= 0:
            return False
        if num == 1:
            return True
        while True:
            if num == 2 or num == 3 or num == 5:
                return True
            if not num%2:
                num /= 2 
            elif not num%3:
                num /= 3
            elif not num%5:
                num /= 5
            else:
                return False
            