import collections
class Stack:
    # initialize your data structure here.
    #everything declared here are object variable not class variable
    #class variable all defined above __init__
    def __init__(self):
        self.q = collections.deque()

    # @param x, an integer
    # @return nothing
    """ In deque of python, new elements are always added to the tail"""
    def push(self, x):
        self.q.append(x) #append value to the deque
        for _ in range(len(self.q) - 1):
            self.q.append(self.q.popleft())

    # @return nothing
    def pop(self):
        self.q.popleft()

    # @return an integer
    def top(self):
        return self.q[0]

    # @return an boolean
    def empty(self):
        return not len(self.q)
    


deq = collections.deque()
deq.append(1)
deq.append(2)
print(deq.pop())

self = Stack()
print(self.empty())