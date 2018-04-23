package list;

/*
An implementation of a linked list
*/
public class ListNode {
		private int data;
		private ListNode next;
		public ListNode(int d) {
				data = d;
				next = null;
		}

		/* 
		Returns true if the element exists in the list
		and false otherwise

		example: if p is [100,200]
		then p.contains(200) is true, p.contains(100) is true,
		p.contains(77) is false
		*/
		public boolean contains(int element) {
                    ListNode current = this;
                    
                    while (current != null) {
                        if (current.data == element) return true;
                        
                        current = current.next;
                    }
                    
                    return false;
		}

		/* 
		Returns the element at index i in this list	

		example: if p is [100,200], then p.get(1) is 200, p.get(0) is 100
		*/
		public int get(int i) {
                    ListNode current = this;
                    
                    while (i != 0) {
                        current = current.next;
                        i--;
                    }
                    
                    return current.data;
                }

		/*
		Adds the element to the end of this list

		example: if p is [100,200] then after p.add(300), p will be [100,200,300]
		*/
		public void add(int element) {
			ListNode current = this;
			while (current.next != null) {
				current = current.next;
			}
			current.next = new ListNode(element);
		}

		public void removeLast() {
			// leave this line in. It checks for bad input
			if (this.length() == 1) { throw new IllegalStateException("cannot call remove last on a list of length 1"); }
			// solution goes here
                        ListNode current = null;
                        
                        for (current = this; current.next.next != null; current = current.next) {
                            
                        }
                        
                        current.next = null;
		}

		/* return the number of elements in the list

		example: if p is [16, 7] then p.length() is 2
		*/
		public int length() {
			ListNode current = this;
			int cur = 1;
			while (current.next!=null) {
				current = current.next;
				cur+=1;
			}
			return cur;
		}
}