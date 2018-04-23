package lab4;

interface Stack {
    /* put d on the top of this Stack */
    public void push(Object d);
    
    /* remove the top element of this Stack */
    public Object pop();
    
    /* returns true if this Stack has no elements */
    public boolean isEmpty();
}