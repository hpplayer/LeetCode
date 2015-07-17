class Solution:
    # @param {string} s
    # @param {integer} numRows
    # @return {string}
    def convert(s, numRows):
        if numRows <= 1 or numRows >= len(s):
             return s
        r = [""]*numRows;
        row = 0;
        step = 1;
        for i in range(len(s)):
            if row == numRows -1:
                step = -1
            if row == 0:
                step = 1
            r[row] += s[i]
            print(r[row])
            row += step
        result = ""
        
        for i in range(numRows):
            result += r[i]
        return str(result)
    
    print(convert("ABC", 2))
        