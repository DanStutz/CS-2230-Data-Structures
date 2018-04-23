package lab4;

class LinkedStack implements Stack {
	// reference to the top of the Stack
	private ListNode top;
    
    public LinkedStack() {
        top = new ListNode(null);
        
    }
    

    // your methods go here

    
    public void push(Object d) {
        ListNode current = top;
        ListNode temp = new ListNode(d);
        
        temp.next = current;
        top = temp;
        
        
      
    }

    
    public Object pop() {
        ListNode current = top;
        ListNode temp = top;
        
        current = current.next;
        top = current;
        
        return temp.data;
        
    }

   
    public boolean isEmpty() {
        if (top.data != null){
            return false;
        }
        return true;
        
    }
    
    

	// you must use ListNode objects in LinkedStack
	private class ListNode {
			public Object data;
			public ListNode next;

			public ListNode(Object d) {
					this.data = d;
					this.next = null;
			}
	}

}
