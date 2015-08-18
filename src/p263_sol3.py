class Solution:
    # @param {integer} num
    # @return {boolean}
    def isUgly(self, num):
        if num <= 0:
            return False
        if num == 1:
            return True
        """ notice: num%2 =0 will be treated as false, so we should check not num%2 instead"""
        if not num%2 and self.isUgly(num/2):
            return True
        elif not num%3 and self.isUgly(num/3):
            return True
        elif not num%5 and self.isUgly(num/5):
            return True
            
        return False