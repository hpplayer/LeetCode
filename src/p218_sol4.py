class Solution:
    # @param {integer[][]} buildings
    # @return {integer[][]}
    def getSkyline(self, buildings):
        if not buildings:
            return [];
        return self.mergeSort(buildings, 0, len(buildings)-1)
    def mergeSort(self, buildings, left, right):
        if left < right:
            mid = int((left + right)/2)
            return self.merge(self.mergeSort(buildings, left, mid), self.mergeSort(buildings, mid+1, right))
        else:
            temp = []
            temp.append([buildings[left][0], buildings[left][2]])#use append to add a list into a list to create a matrix
            temp.append([buildings[left][1], 0])
            return temp
            
    def merge(self, s1, s2):
        result = []
        h1 = h2 = 0
        while s1 and s2:
            newX = newH = 0
            #if s1 has smaller x
            if s1[0][0] < s2[0][0]:
                newX = s1[0][0]
                h1 = s1[0][1]
                newH = max(h1, h2)
                s1.pop(0)
            #if s2 has smaller x
            elif s1[0][0] > s2[0][0]:
                newX = s2[0][0]
                h2 = s2[0][1]
                newH = max(h1, h2)
                s2.pop(0)
            else:
                newX = s1[0][0]
                h1 = s1[0][1]
                h2 = s2[0][1]
                newH = max(h1,h2)
                s1.pop(0)
                s2.pop(0)
            if len(result) == 0 or result[-1][1] != newH:
                result.append([newX, newH])
                
        result += s1
        result += s2
        
        return result
     
    

sol = Solution()
print(sol.getSkyline(buildings))    