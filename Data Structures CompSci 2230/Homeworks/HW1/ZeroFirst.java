import java.util.Arrays;

class ZeroFirst {

		public static void main(String[] args) {
				int[] a1 = {-1,-2,-2,4}; // input
				int[] a1_exp = {-1,-2,-2,4}; // expected contents of a1 after calling zero_first
				zero_first(a1);
				if (!Arrays.equals(a1, a1_exp)) {
						System.out.println("FAILED TEST 1");
						System.exit(1);
				}
				
				int[] a2 = {4,3,2,1,0};
				int[] a2_exp = {0,4,3,2,1};
				zero_first(a2);
				if (!Arrays.equals(a2, a2_exp)) {
						System.out.println("FAILED TEST 2");
						System.exit(1);
				}
				
				int[] a3 =  {0, 5, 0, -1, 10, 11};
				int[] a3_exp = {0,0,5,-1,10,11};
				zero_first(a3);
				if (!Arrays.equals(a3, a3_exp)) {
						System.out.println("FAILED TEST 3");
						System.exit(1);
				}
				
				int[] a4 = {-4, 5, -6, 7, 0, 4, 3, 0, 0};
				int[] a4_exp = {0,0,0,-4,5,-6,7,4,3};
				zero_first(a4);
				if (!Arrays.equals(a4, a4_exp)) {
						System.out.println("FAILED TEST 4");
						System.exit(1);
				}
				
				int[] a5 = {0,0,1};
				int[] a5_exp = {0,0,1};
				zero_first(a5);
				if (!Arrays.equals(a5, a5_exp)) {
						System.out.println("FAILED TEST 5");
						System.exit(1);
				}
				
				/* We will test your code on additional test
				cases, so make sure it really works. (e.g., you can
				add more of your own test cases, just copy one of the
				above ones and modify it).
				*/
				
				System.out.println("Tests passed");
		}
		
		public static void zero_first(int[] arr) {
                    
                    
		int x = arr.length - 1;
                int y = arr.length - 1;	
                while (x >= 0) {
                    if (arr[x] != 0) {
                        arr[y--] = arr[x];
                    }
                    x--;
                }
                while (y >= 0) arr[y--] = 0;
		}
}

