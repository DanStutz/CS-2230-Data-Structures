package hw3;

public class LinkedList implements List {
    private ListNode header;
    
    
    /* your private fields go here.
     * It is recommended that you use a "sentinel node" at the front of the list
     *  to make the methods easier to implement
     */
    
	/* The current number of elements in the list */
    private int size;
    

    /* Part 1:
     * create an empty list
     */
	public LinkedList() {
            header = new ListNode(0);
            
            size = 0;
            
	}
        
    /* DO NOT CHANGE THIS METHOD
     * This method returns the size variable, which 
     * should be updated in other methods.  
     */
        @Override
        public int size(){
            return this.size;
        }

    /* Part 4:
     * remove ith element from the list and return it
     *
     * assume that the ith element exists
     *
     * Example
     * before: [ 100, 200, 300 ]
     * remove(1)
     * after:  [ 100, 300 ]
     * returns 200
     */
        @Override
	
	public Object remove(int i) {
            ListNode current = header.getNext();
            Object temp;
            
            if (i == 0) { //Head
                temp = current.getData();                
                header.setNext(current.getNext());
            }
            else if (i + 1 < this.size) { //Not The Head                   
                ListNode prev = current;

                for (int j = 0; j < i; j++) {
                    prev = current;
                    current = current.getNext();
                }

                temp = current.getData();
                prev.setNext(current.getNext());
            }
            else { //Last Element
                ListNode prev = current;

                for (int j = 0; j < i; j++) {
                    prev = current;
                    current = current.getNext();
                }

                temp = current.getData();
                prev.setNext(null);
            }
            
            this.size--;
            return temp;
	}

    /* Part 1:
     * add d to the end of the list
     *
     * Example
     * before: [ 100, 200 ]
     * add(300)
     * after:  [ 100, 200, 300 ]
     */
        @Override
	public void add(Object d) {
        ListNode temp = new ListNode(d);
        ListNode current = header;
        if (header.getNext() == null) {
            header.setNext(temp);
        }
        else{
            while((current.getNext() != null)){
            current = current.getNext();
            }
            current.setNext(temp);
        }
        
        
        this.size++;
        }
    /* Part 2:
     * return the ith element
     *
     * assume that the ith element exists
     *
     * Example
     * before: [ 100, 200, 300 ]
     * get(2)
     * after:  [ 100, 200, 300 ]
     * returns 300
     */
        @Override
	public Object get(int i) {
        if (i < 0) return null;
        if (header.getNext() != null){
            ListNode current = header;
            //current = current.getNext();
            for (int x = 0; x <= i; x++){
                current = current.getNext();
            }
            return current.getData();
            
            }
	return null;
	}
        
    /* Part 3:
     * replace the ith element with d and return it
     *   
     * assume that the ith element exists 
     * 
     * Example
     * before: [ 100, 200, 300 ]
     * replace(1, 400)
     * after:  [ 100, 400, 300 ]
     * returns 200   
     */
        @Override
        public Object replace(int i, Object d){
            ListNode current = header;
            Object temp;
            current = current.getNext();
            
            for (int x = 0; x < i; x++){
                current = current.getNext();
                
            }
            temp = current.getData();
            current.setData(d);
            return temp;
        }
        
    /* Part 5:
     * move the ith element so that it is now the jth element 
     *
     * assume that the ith and jth elements exist
     *
     * Example
     * before: [ 100, 200, 300 ]
     * move(1, 0)
     * after:  [ 200, 100, 300 ]
     *
     * Example
     * before: [ 100, 200, 300, 400 ]
     * move(1,3)
     * after: [ 100, 300, 400, 200 ]
     */
        @Override
        public void move(int i, int j) {

        while (i != j){
                Object first = this.get(i);
                Object second = this.get(j);
                this.replace(j, first);
                this.replace(i, second);
                i--;
                return;
                }
             
            }
	
	
    /* Part 1:
     * Returns an array containing the elements of the list in order
     * Do not copy the Object data; just copy the Object references.
     * 
     * Example
     * before: [ 100, 200, 300 ]
     * toArray()
     * after: [ 100, 200, 300 ]
     * returns { 100, 200, 300 } 
     */
	
        @Override
        public Object[] toArray() {
            Object[] arr = new Object[this.size];
            if (header.getNext() == null) return arr;
            
            ListNode current = header;
            //arr[0] = current;
            for (int x = 0; x <size; x++){
                arr[x] = current.getNext().getData();
                current = current.getNext();
            }
        return arr;
	}

    // you do not need to modify the ListNode class
    private class ListNode {
		private Object data;
		private ListNode next;

		public ListNode(Object data) {
			this.data = data;
			this.next = null;
		}
		
		public Object getData() {
				return data;
		}

		public void setData(Object data) {
				this.data = data;
		}
		
		public void setNext(ListNode next) {
				this.next = next;
		}
		
		public ListNode getNext() {
				return next;
		}
	}

}
