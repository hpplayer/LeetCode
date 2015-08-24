class Solution(object):
    def findKthLargest(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: int
        """
        left, right = 0, len(nums)-1
        while True:
            temp = self.partition(nums, left, right)
            if temp == k - 1:
                return nums[temp]
            elif temp > k - 1:#search left part
                right = temp - 1
            elif temp < k - 1: #search right part
                left = temp + 1
            
                
    def partition(self, nums, left, right):
        #pick leftmost element as pivotal
        pivotal = nums[left]
        temp = left
        left += 1
        
        while left <= right:
            if nums[left] < pivotal and nums[right] > pivotal:
                nums[left], nums[right] = nums[right], nums[left] #Notice how to swap elements in array in Python!!!
                left += 1
                right -= 1
            if nums[left] >= pivotal:
                left += 1
            if nums[right] <= pivotal:
                right -= 1
        
        #finally swap the last element in left part(assume index i)  with pivotal, so the pivotal will be the ith largest number
        nums[temp], nums[right] = nums[right], nums[temp]
        
        return right
            