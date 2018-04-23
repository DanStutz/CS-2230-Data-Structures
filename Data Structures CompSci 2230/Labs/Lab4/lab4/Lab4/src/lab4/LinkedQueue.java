package lab4;

class LinkedQueue implements Queue {
	// your fields go here
    private ListNode head;
    private ListNode tail;
    private int size;
    
    
    public LinkedQueue() {
        size = 0;
        head = null;
        tail = null;
    }
    

    // your methods go here

    
    public void add(Object d){
        if (this.isEmpty()) {
            ListNode n = new ListNode(d);
            head = n;
            tail = n;
        }
        else {
        ListNode x = tail;
        ListNode n = tail.next;
        tail = n;
        return;
        
        }
        
    }

    
    public Object pop() {
       if (head == null){
           return null;
       }
       else{
            ListNode x = head;
            ListNode y = head.next;
            head = y;
            return x.data;
       }
     
    }

   
    public boolean isEmpty() {
        return size == 0;
        
    }
    
    

	// you must use ListNode objects in LinkedQueue
	private class ListNode {
			public Object data;
			public ListNode next;

			public ListNode(Object d) {
					this.data = d;
					this.next = null;
			}
			
	}

}
