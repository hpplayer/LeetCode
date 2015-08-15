class Solution:
    # @param {integer[]} nums
    # @return {integer[]}
    def majorityElement(self, nums):
        candidateA = candidateB = counterA = counterB = 0
        for num in nums:
            if counterA == 0:
                candidateA = num
                counterA = 1
            elif num == candidateA:
                counterA += 1
            elif counterB == 0:
                candidateB = num
                counterB = 1
            elif num == candidateB:
                counterB += 1
            else:
                counterB -= 1
                counterA -= 1
        counterA = counterB = 0
        for num in nums:
            if num == candidateA:
                counterA += 1
            elif num == candidateB:
                counterB += 1
        """ following code can be simply revised as
         return [n for n in set((candidateA, candidateB)) if nums.count(n) > len(nums)//3]
        since this return will not avoid duplicates of candidateA and candidateB, we have to add them into a set
        // in python is the floow division
         """
        result = []
        if counterA > len(nums)/3:
            result.append(candidateA)
        if counterB > len(nums)/3:
            result.append(candidateB)
            
        return result