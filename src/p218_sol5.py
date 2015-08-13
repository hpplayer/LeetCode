import functools
from heapq import *
class Solution:
    # @param {integer[][]} buildings
    # @return {integer[][]}
    class Node:
        def __init__(self, x, h, isLeft):
            self.x = x
            self.h = h
            self.isLeft = isLeft
        
    def getSkyline(self, buildings):
        #start = time.time()
        if not buildings:
            return []
        edges = []
        for building in buildings:
            leftEdge = self.Node(building[0], building[2], True)
            rightEdge = self.Node(building[1], building[2], False)
            edges.append(leftEdge)
            edges.append(rightEdge)
        
        edges.sort(key = functools.cmp_to_key(self.sortEdge))
    
        """In python, list can be treated as queue by heapify()"""
        pq = []
        result = []
        #end = time.time()
        #print(end - start)
        for Edge in edges:
            #print(Edge.x,Edge.h,Edge.isLeft)
            if Edge.isLeft:
                if len(pq) == 0 or Edge.h > -pq[0]:
                    result.append([Edge.x, Edge.h])
                heappush(pq, -Edge.h)
            else:
                pq.remove(-Edge.h)
                heapify(pq) #need heapify again after using normal list remove()
                #print(pq)
                if not pq:
                    result.append([Edge.x, 0])
                if len(pq) > 0 and Edge.h > -pq[0]:
                    result.append([Edge.x, -pq[0]])
        return result
    
    def sortEdge(self, e1, e2):
        if e1.x != e2.x:
            return e1.x - e2.x;
        else:
            if e1.isLeft and e2.isLeft:
                return e2.h - e1.h
            if not e1.isLeft and not e2.isLeft:
                return e1.h - e2.h
            
            if e1.isLeft:
                return -1
            else:
                return 1
            #return -1 if e1.isLeft else 1
            
    
            
#buildings = [[0,2,3], [2,5,3]]
sol = Solution()
print(sol.getSkyline(buildings))       

"""
## custom sort



def comparator(x, y):
    return x-y

## Initial Array
array = [8, 2, 9, 0, 1, 2, 5];
array.sort(key = functools.cmp_to_key(comparator))
print(array)
"""