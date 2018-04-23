package hw2;

public class ConstructionSite {
    
    private SandBox[] boxes;
    private int count;          //number of boxs saved in the array
    
    public ConstructionSite(int maxSize) {
    	boxes = new SandBox[maxSize];  
       	count = 0;
    } 
    
    // add a new empty SandBox of the given width, length, height to the array
    // 
    // But, if the array is already full then just print "ERROR addSandBox"
    // and don't add a new box
    public void addSandBox(double length, double width, double height) {
        if (boxes[boxes.length - 1] != null) {
            System.out.println("ERROR addSandBox");
        }
        else {
            boxes[count] = new SandBox(length, width, height);
            count += 1;
        }
    }
    
    // if array contains at least one SandBox delete the last one in the array
    // otherwise do nothing
    public void deleteSandBox() {
        if (boxes[0] != null) {
            boxes[count - 1] = null;
            count -= 1;
        }
    }

    // scoop sand from input in order until
    // either all of the sand is gone or all boxes have been filled
    //
    // EXAMPLE #1
    // BEFORE
    // boxes {SandBox(volume=10,sandVolume=0), 
    //           SandBox(volume=4,sandVolume=0), 
    //           SandBox(volume=6,sandVolume=0)} 
    // input is SandBox(volume=13, sandVolume=13)
    // 
    // then AFTER
    // boxes {SandBox(volume=10,sandVolume=10), 
    //           SandBox(volume=4,sandVolume=3), 
    //           SandBox(volume=6,sandVolume=0)} 
    // input is SandBox(volume=13,sandVolume=0)
    //
    // EXAMPLE #2
    // BEFORE
    // boxes {SandBox(volume=10,sandVolume=0), 
    //           SandBox(volume=4,sandVolume=0), 
    //           SandBox(volume=6,sandVolume=0)} 
    // input is SandBox(volume=22, sandVolume=22)
    // 
    // then AFTER
    // boxes {SandBox(volume=10,sandVolume=10), 
    //           SandBox(volume=4,sandVolume=4), 
    //           SandBox(volume=6,sandVolume=6)} 
    // input is SandBox(volume=22,sandVolume=2)
    public void fillBoxesInOrder(SandBox input) {
        for (int x = 0; x < count; x++) {
            boxes[x].scoopSandFrom(input);
        }
    }

    /*
    Helper function to check if two doubles are close enough to being equal.
    (In general, it is bad to compare doubles for exact equality with ==)
    */
    private static void check(double got, double expected) {
            final double EPSILON = 0.00001;
            if (Math.abs(got - expected) >= EPSILON) {
                    throw new RuntimeException("failed test: got " + got + " but expected " + expected);
            }
    }

    /*
        Helper function to run a test case

        site: the ConstructionSite to work with
        input: the SandBox to scoop sand out of
        expectedVols: expected volumes of the site's SandBoxes after the scooping
        expectedSandVols: expected sand volumes of the site's SandBoxes after the scooping
        expectedInputVol: expected volume of the input after the scooping
        expectedInputSandVol: expected sand volume of the input after the scooping
    */
    private static void testCase(ConstructionSite site, SandBox input, 
                    double[] expectedVols, double[] expectedSandVols,
                    double expectedInputVol, double expectedInputSandVol) {
            input.fillToTop();
            site.fillBoxesInOrder(input);
            for (int i=0; i<site.count; i++) {
                    System.out.println(site.boxes[i].toString());
                    check(site.boxes[i].getVolume(), expectedVols[i]); 
                    check(site.boxes[i].getSandVolume(), expectedSandVols[i]);
            }
            System.out.println("source: "+input.toString());
            check(input.getVolume(), expectedInputVol);
            check(input.getSandVolume(), expectedInputSandVol);
    }
	
	/*
	Runs a drain simulation on this ConstructionSite's boxes.
	At time=0, a hole is poked in each SandBox, and once per frame this method
	prints the current elapsed time and the boxes.

	framesPerSecond: frames per second, simulation is updated once per frame
	seconds: time to run the simulation for
	holes: array of rates that sand leaves the boxes.
	    For example if holes = {1,2,3} then boxes[0] drains 1 unit volume/second,
	    boxes[1] drains 2 unit volume/second, and boxes[2] drains 3 unit volume/second
	*/
	public void runDrainSimulation(double framesPerSecond, double seconds, double[] holes) {
            double totalFrames = framesPerSecond * seconds;
            for (int f = 0; f < totalFrames; f++) {
                double frames = 1 / framesPerSecond;
                double newTime = (f * frames) + frames;
                System.out.print("Time=" + newTime + ": ");
                
                for (int x = 0; x < boxes.length; x++) {
                    if (boxes[x].getSandVolume() == 0) {
                        System.out.print(boxes[x].toString() + " ");
                    }
                    else {
                        SandBox fill = new SandBox(holes[x]/2, 1, 1);
                        fill.scoopSandFrom(boxes[x]);
                        System.out.print(boxes[x].toString() + " ");
                    }
                }
                
            System.out.println();
                
                // DO NOT REMOVE THIS CODE; makes the simulation real time
		try {
                    Thread.sleep((long)(1000.0/framesPerSecond));
		} catch (InterruptedException e) { /* ignore this error */ }
            }	
	}

    public static void main(String[] args) {
        //For these tests, the same set of boxs are used for each test. They are not emptied out
        //at the begining of each test
        ConstructionSite site = new ConstructionSite(3);
        site.addSandBox(1, 2, 2);
        site.addSandBox(1, 3, 1);
        site.addSandBox(2, 2, 4);
        site.addSandBox(10, 1, 1);  // will not get added because no room

        System.out.println("First box...");
        SandBox input = new SandBox(4, 1, 2);
        double[] expectedVols = {4,3,16};
        double[] expectedSandVolumes = {4,3,1};
        testCase(site, input, expectedVols, expectedSandVolumes, 8, 0);
        System.out.println("...First test passed\n");

        System.out.println("Second box...");
        SandBox input2 = new SandBox(8, 8, 1.125);
        double[] expectedVols2 = {4,3,16};
        double[] expectedSandVolumes2 = {4,3,16};
        testCase(site, input2, expectedVols2, expectedSandVolumes2, 72, 57);
        System.out.println("...Second test passed\n");

        System.out.println("Third box...");
        site.deleteSandBox(); // delete 3rd Cone
        site.deleteSandBox(); // delete 2nd Cone
        site.addSandBox(9, 2, 1.5);
        SandBox input3 = new SandBox(2, 1, 1);
        double[] expectedVols3 = {4,27};
        double[] expectedSandVolumes3 = {4,2};
        testCase(site, input3, expectedVols3, expectedSandVolumes3, 2, 0);
        System.out.println("...Third test passed\n");

		System.out.println("Poking holes in the SandBoxes!");
		site.addSandBox(10, 10, 10); // yes, this SandBox starts empty
		double[] holes = {2, 0.5, 4.5};
		site.runDrainSimulation(2, 5, holes);
        
		
	/*
         * Optional (but recommended): you can add more tests here
         * for both runDrainSimulation and fillBoxesInOrder
         */
    }
}