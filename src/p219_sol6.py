class Solution:
    # @param {integer[]} nums
    # @param {integer} k
    # @return {boolean}
    def containsNearbyDuplicate(self, nums, k):
        hs, left = set(), 0
        for right, value in enumerate(nums):
            if value in hs:
                return True
            else:
                hs.add(value)                 
                if right - left == k:
                    hs.remove(nums[left])
                    left +=1 
        return False
    
    
sol = Solution()
print(sol.containsNearbyDuplicate([1,2,1], 0))