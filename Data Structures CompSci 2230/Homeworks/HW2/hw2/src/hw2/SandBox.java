package hw2;

public class SandBox {
   private final double LENGTH;    // doesn't change after SandBox is constructed   
   private final double WIDTH; // doesn't change after SandBox is constructed
   private final double HEIGHT;// doesn't change after SandBox is constructed   

   // current height of the sand within the box
   private double sandHeight;  
   
    /*
     * Constructor 
     * Initially there is no sand in this SandBox
     */
    public SandBox(double l, double w, double h){
        LENGTH = l;
        WIDTH = w;
        HEIGHT= h;
         
    }
    
    // Constructor
	// Creates a SandBox with all dimensions 1
    // Initially there is no sand in this SandBox
    public SandBox(){
        LENGTH = 1;
        WIDTH = 1;
        HEIGHT = 1;
        
    }
		
	// getLength: returns the length of this SandBox
    public double getLength(){
        return this.LENGTH;
    }

	// getWidth: returns the width of this SandBox
    public double  getWidth(){
        return this.WIDTH;
    }

	// getHeight: returns the height of this SandBox
    public double getHeight() {
        return this.HEIGHT;
    }

	// getVolume: returns the total volume of this SandBox
    public double getVolume(){
        return (this.LENGTH * this.WIDTH * this.HEIGHT);
    }

	// getSandVolume: returns the volume of sand in this SandBox
    public double getSandVolume(){
        return (this.LENGTH * this.WIDTH * this.sandHeight);
    }
    
    // toString: returns a String describing this SandBox
    //   for example, for the SandBox with length=10, width=12, height=2, sandHeight=1
    //   the String will be "SandBox(volume=240, sandVolume=120)"
	@Override
    public String toString() {
        return "SandBox(volume=" + getVolume() +", sandVolume=" + getSandVolume() + ")"; 
    }

    // scoopSandFrom
    // Move sand from other into this SandBox.
    // If you would overflow this SandBox, *only* move the volume
    // of sand that would fill this SandBox to the top.
    public void scoopSandFrom(SandBox other){
        double dif = other.sandHeight - this.sandHeight;
        this.sandHeight = other.sandHeight + this.sandHeight;
        other.sandHeight = dif + other.sandHeight;
        if (this.sandHeight > this.HEIGHT){
            this.sandHeight = this.HEIGHT;
        }

		// Don't remove these lines. They help you find errors more quickly
		// when debugging.
		this.checkState();	
		other.checkState();
                
    }

	private void checkState() {
		if (this.sandHeight > this.HEIGHT) { throw new IllegalArgumentException("SandBox overflowed!"); }
		if (this.sandHeight < 0) { throw new IllegalArgumentException("SandBox contains anti-sand!"); }
	}

    // completely fills this SandBox with sand
    public void fillToTop() {
        this.sandHeight = this.HEIGHT;
    }
}
