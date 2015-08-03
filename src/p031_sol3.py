class Solution:
    # @param {integer[]} nums
    # @return {void} Do not return anything, modify nums in-place instead.
    def nextPermutation(self, nums):
        i = len(nums)-1
        while i > 0 and nums[i-1] >= nums[i]:
            i-=1
        #print(i)
        if i > 0: #incase we only have 2 nums
            self.swap(nums, i-1)
        
        #print(nums, i)
        self.reverse(nums, i)
        return
    
    def swap (self, nums, i):#found first num that is larger than nums[i-1]
        for j in range(len(nums)-1, i, -1):
            if nums[j] > nums[i]:
                nums[j] += nums[i]
                nums[i] = nums[j] - nums[i]
                nums[j] -= nums[i]
                break
            
    def reverse(self, nums, i): #reverse nums after i
        start = i
        end = len(nums) - 1
        while start < end:
            nums[start] += nums[end]
            nums[end] = nums[start] - nums[end]
            nums[start] -= nums[end]
            start +=1
            end -=1
        

sol = Solution()
x = [3,2,1]
sol.nextPermutation(x)
print(x)