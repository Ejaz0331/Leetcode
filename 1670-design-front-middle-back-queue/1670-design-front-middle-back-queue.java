class FrontMiddleBackQueue {
    Deque<Integer> dq;

    public FrontMiddleBackQueue() {
        dq=new ArrayDeque<>();
        
    }
    
    public void pushFront(int val) {
        dq.addFirst(val);
        
    }
    
    public void pushMiddle(int val) {
        List<Integer> li=new ArrayList<>(dq);
        li.add(dq.size()/2,val);
        dq=new ArrayDeque<>(li);
    }
    
    public void pushBack(int val) {
        dq.addLast(val);
    }
    
    public int popFront() {
        if(dq.isEmpty()){
            return -1;
        }
        return dq.pollFirst();
        
    }
    
    public int popMiddle() {
        if(dq.isEmpty()){
            return -1;
        }
        List<Integer> li=new ArrayList<>(dq);
        int ele=0;
        if(dq.size()%2==0){
            ele=li.remove(dq.size()/2-1);
        }
        else{
             ele=li.remove(dq.size()/2);
        }
        dq=new ArrayDeque<>(li);
        return ele;
    }
    
    public int popBack() {
        if(dq.isEmpty()){
            return -1;
        }
        return dq.pollLast();
        
    }
}

