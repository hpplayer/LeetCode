class Solution:
    # @param {integer[]} nums
    # @return {string[]}
    def summaryRanges(self, nums):
        result = []
        for num in nums:
            #need add new start
            if not result or num > result[-1][-1] + 1:
                result += [], #, in the end turn right part to a tuple, result +=[] equals result += [[]]
            result[-1][1:] = num, #[1:] update will update the second (end point) of the pair
            print(result)
        return ['->'.join(map(str, r)) for r in result]
    
sol = Solution()
nums = [0,1,2,4,5,7]

print(sol.summaryRanges(nums))