class Solution:
    # @param {integer} n
    # @return {integer}
    def countDigitOne(self, n):
        result, leftNum, rightNum, rightLen = 0, n, 0, 1
        while leftNum > 0:
            currNum = leftNum % 10
            leftNum = int(leftNum/10)
            
            if currNum is 0: #0->leftNum-1 * 0->999  
                result += rightLen * leftNum
            elif currNum is 1:  #0->leftNum-1 * 0->999  + 1 (1 for leftNum) * rightNum + 1 (1 for boundary case 0)
                result += rightLen * leftNum
                result += 1 * (rightNum+1)
            else:#0->leftNum * 0->999
                result += (leftNum + 1) * rightLen
            
            rightNum += rightLen * currNum
            rightLen *= 10
            
        return result