public class MaxBoth {
		
		public static void main(String[] args) {
				int[] a1 = {2,4,6,8,10,12,14};
				int[] b1 = {7,7,5,4};

				// checks both orders of arguments since order doesn't matter
				// || means "or"
				if (max_both(a1,b1) != 4 || max_both(b1,a1) != 4) {
						System.out.println("FAILED TEST 1");
						System.exit(1);
				}
				
				int[] a2 = {2,4,6,8};
				int[] b2 = {3,5,7};
				if (max_both(a2,b2) != -1 || max_both(b2,a2) != -1) {
						System.out.println("FAILED TEST 2");
						System.exit(1);
				}
				
				int[] a3 = {7,6,22,4,4};
				int[] b3 = {4,5,5,25,6};
				if (max_both(a3,b3) !=  6 || max_both(b3,a3) != 6) {
						System.out.println("FAILED TEST 3");
						System.exit(1);
				}
				
				int[] a4 = {10,20,30,40,50,60,70};
				int[] b4 = {50,40,70,60,10};
				if (max_both(a4,b4) != 70 || max_both(b4,a4) != 70) {
						System.out.println("FAILED TEST 4");
						System.exit(1);
				}

				/* We will test your code on additional test
				cases, so make sure it really works. (e.g., you can
				add more of your own test cases, just copy one of the
				above ones and modify it).
				*/
				
				System.out.println("Tests passed");
		}
		
		public static int max_both(int[] x, int[] y) {
				int baseMax = -1;
                                
                                
                for(int a = 0; a<x.length; a++){
                    for(int b = 0; b<y.length; b++){
                        if (x[a] == y[b]){
                            if (x[a] > baseMax){
                                baseMax = x[a];
                               
                            }    
                                       }
                                   }
                                }
		return baseMax;		
		}
}
