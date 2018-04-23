package lab4;

interface Queue {
    /* put d at the back of this Queue */
    public void add(Object d);
    
    /* remove the element at the front of this Queue and return it */
    public Object pop();
    
    /* returns true if this Queue is empty */
    public boolean isEmpty();
}