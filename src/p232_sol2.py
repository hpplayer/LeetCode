class Queue:
    """There is no stack class in python, as we can easily achieve same goal with list []"""
    # initialize your data structure here.
    def __init__(self):
        self.forward = []
        self.backward = []
    # @param x, an integer
    # @return nothing
    def push(self, x):
        while len(self.backward):
            self.forward.append(self.backward.pop())
        self.forward.append(x)
    # @return nothing
    def pop(self):
        while len(self.forward):
            self.backward.append(self.forward.pop())
        self.backward.pop()
    # @return an integer
    def peek(self):
        while len(self.forward):
            self.backward.append(self.forward.pop())
        return self.backward[-1] #since we append value from forward to backward, the top value in the queue will become last

    # @return an boolean
    def empty(self):
        return len(self.forward) == 0 and len(self.backward) == 0
    

que = Queue()
print(que.empty()) 
que.push(1)
print(que.empty()) 
que.push(2)
print(que.peek())
print(que.peek())  