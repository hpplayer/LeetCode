class Solution:
    # @param {string} s
    # @param {string[]} words
    # @return {integer[]}
    def findSubstring(self, s, words):
        step = len(words[0])
        size = len(words)
        result = []
        #create a fixed hashMap "expect"
        expect = {}
        for word in words:
            if word not in expect.keys():
                expect[word] = 1
            else:
                expect[word] += 1            
           # expect.setdefault(word, 0) #important
           # expect[word] += 1;
            
        for i in range(step): #check 1..Length - 1 possibilities
            #print(i)
            winLeft = i
             #create a change hashMap "actual"
            actual = {}
            #actual.setdefault(word, 0)
            count = 0
            for winRight in range(i,len(s), step):
                word = s[winRight : (winRight + step)]
                if (winRight + step) > len(s):
                    break
                """if word not in expect dict, then let winleft skip this """
                if word not in expect.keys():
                    count = 0
                    actual.clear() #if use actual = {} then other variable points to actual will not be cleared
                    winLeft = winRight + step
                    continue
                
                if word not in actual.keys():
                    actual[word] = 1
                else:
                    actual[word] += 1
                    
                count += 1
                
                """if word count exceed the limit, move winleft until we skip the first appearance of this word"""
                while actual[word] > expect[word]:
                    tmp = s[winLeft : (winLeft + step)]
                    actual[tmp] -= 1
                    count -= 1
                    winLeft += step
                
                """if count match our target number, then add to result """
                if count == size:
                    result.append(winLeft)
                    temp = s[winLeft : (winLeft + step)]
                    actual[temp] -= 1
                    winLeft += step
                    count -= 1
                    #print(winLeft)
                    #print(winRight)
        return result
                

sol = Solution
print(sol.findSubstring(sol, "aaa", ["a", "a"]))
