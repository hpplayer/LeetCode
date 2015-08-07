import re
class Solution:
    # @param {string} input
    # @return {integer[]}
    def diffWaysToCompute(self, input):
        nums = re.split('\+|\-|\*', input) #/ means "or" here, \match any \+ or \* or \-, split based on those match
        signs = re.split('\d+', input) #\d+ match any digit, split based on those match
        signs = signs[1:-1] #escape heading and tailing spaces
        num_len = len(nums)
        #create dp matrix, dp[i][j] is a list which contains all possible values calculated between i and j
        #inital value in each matrix cell is an empty array, the matrix size is num_len * num_len
        dp = [[[] for i in range(num_len)] for j in range(num_len)]     
        #create a sign hashMap
        op = {"+": lambda x,y: x+y, "-": lambda x,y: x-y, "*": lambda x,y: x*y}
        #fill the dp[i][i], which is num it self with one number
        for i in range(num_len):
            dp[i][i].append(int(nums[i])) #remember convert it into int instead of original input string
            
        #we begin fill the matrix, still with 2 numbers 
        for num_count in range(2, num_len+1):
            #loop through all possible start points from 0 to num_len - num_count
            for start_index in range(0, num_len - num_count + 1):
                #start split inside the range, the split point can be from 1 to num_count-1, which always left one                 #num on the leftmost or on the rightmost
                for split_index in range(1, num_count):
                    for x in dp[start_index][start_index+split_index-1]:
                        for y in dp[start_index+split_index][start_index+num_count-1]:
                        #sign should in exactlly the split index
                            dp[start_index][start_index+num_count-1].append(op[signs[start_index + split_index-1]](x,y))
        #return the longest distance in the input, so we should contain all possible values
        return dp[0][num_len-1]

"""ORIGINAL VERSION"""
class Solution2:
    # @param {string} input
    # @return {integer[]}
    def diffWaysToCompute(self, input):
        num=re.split('\+|-|\*',input)
        opr=re.split('\d+',input)
        opr=opr[1:-1]
        d=len(num)
        dp=[[[]for i in range(d)] for j in range(d)]
        op={'+':lambda x,y:x+y, '-':lambda x,y:x-y, '*':lambda x,y:x*y}
        for i in range(d):
            dp[i][i].append(int(num[i]))
        for l in range(2,d+1):
            for j in range(d+1-l):
                    dp[j][j+l-1]=[op[opr[j+k-1]](x,y)
                                      for k in range(1,l) for x in dp[j][j+k-1] for y in dp[j+k][j+l-1]]
        return dp[0][d-1]   
    
test = Solution()
print(test.diffWaysToCompute("2-4")) 
    