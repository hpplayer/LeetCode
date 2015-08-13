from heapq import *
class Solution:
    # @param {integer[][]} buildings
    # @return {integer[][]}

    def getSkyline(self, buildings):
        result = []
        i, n = 0, len(buildings) #i is current building
        liveBuildings = [] #it is a heap, python default heap is minheap, so we need make height negative before inserting into heap
        while i < n or liveBuildings:
            #if current building'x is <= largest height's end point x
            # means we start a new building before current highest building ends, then we add it into the heap
            if not liveBuildings or i < n and buildings[i][0] <= -liveBuildings[0][1]:
                x = buildings[i][0]
                while i < n and buildings[i][0] == x:
                    #our heap firstly sorted on height then on end point
                    #add all building start at x into the heap
                    heappush(liveBuildings, (-buildings[i][2], -buildings[i][1]))
                    i += 1#current building ++
            else:#means our new building start after current highest building ends
                #get largest height's x
                x = -liveBuildings[0][1]
                # pop out all edges that have x smaller than x
                #pop all buildings that ends before current highest building
                while liveBuildings and -liveBuildings[0][1] <= x:
                    heappop(liveBuildings)
            #if len(liveBuildings) is false(<=0), then it will be height's value and second expression will not be evaluated
            #height = 0 will be used when we reach a gap or reach the tail
            height = len(liveBuildings) and -(liveBuildings[0][0])
            if not result or height != result[-1][1]:
                result += [x, height], 
        return result
    
sol = Solution()
print(sol.getSkyline(buildings))          