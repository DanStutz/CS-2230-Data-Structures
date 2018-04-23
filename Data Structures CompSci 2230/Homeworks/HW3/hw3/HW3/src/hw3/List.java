package hw3;

interface List {
    
	/*
	Returns the number of elements in this List
	*/
    int size();
    
	/*
	Removes the element at index i of this List and returns it
	*/
    Object remove(int i);

	/*
	Adds d to the end of this List
	*/
    void add(Object d);

	/*
	Returns the element at index i of this List
	*/
    Object get(int i);
    
	/*
	Replaces the element at index i of this List with d;
	Returns the element that used to be at index i
	*/
    Object replace(int i, Object d);

	/*
	Moves the element at index i so that it is now at index j
	*/
    void move(int i, int j);

	/*
	Returns an array of Objects with the same contents and order
	as this List
	*/
    Object[] toArray();
}
