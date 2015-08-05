class Solution:
    # @param {integer[]} nums
    # @return {integer}
    def firstMissingPositive(self, nums):
        if not nums:
            return 1
        i = 0
        while i < len(nums): #for loop in python will always go foward, so use while loop instead
            if nums[i] != i + 1:
                if nums[i] <= 0 or nums[i] > len(nums) or nums[i] == nums[nums[i]-1]:
                    i += 1
                    continue
                else:
                    temp = nums[i] - 1
                    nums[i], nums[temp] = nums[temp], nums[i] #Notice how swap is easily done in python
                    i -= 1
            i += 1
        
        for i in range(len(nums)):
            if nums[i] != i + 1:
                return i+1
        return len(nums) + 1

sol = Solution()
print(sol.firstMissingPositive([3, 4, -1, 1]))
