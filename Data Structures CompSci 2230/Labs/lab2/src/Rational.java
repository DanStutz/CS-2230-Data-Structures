

/******************************************************************************

 ******************************************************************************/

public class Rational  {
    private static Rational zero = new Rational(0, 1);

    private int num;   // the numerator
    private int den;   // the denominator

    // create and initialize a new Rational object
    public Rational(int numerator, int denominator) {

        // reduce fraction
        int g = gcd(numerator, denominator);
        num = numerator   / g;
        den = denominator / g;

        // only needed for negative numbers
        if (den < 0) { den = -den; num = -num; }
    }

    // return the numerator (this)
    public int numerator()   { 
        return this.num;
     }
    // return the denominator of (this)
    public int denominator() {
        return this.den;
       
     }

    // return double precision representation of (this)
    public double toDouble() {
        return (double) num / den;
    }

    // return string representation of (this)
    // print as num/den, or if den is 1 then print just num
    public String toString() { 
        if (den == 1) return num + "";
        else return num + "/" + den;
    }

    // return -1 if a<b or  0 if a=b or 1 if a>b 
    public int compareTo(Rational b) {
        Rational a = this;
        int lhs = a.num * b.den;
        int rhs = a.den * b.num;
        if (lhs < rhs) return -1;
        if (lhs > rhs) return +1;
        return 0;
    }

    // is this Rational object equal to y?
    public boolean equals(Object y) {
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Rational b = (Rational) y;
        return compareTo(b) == 0;
    }


    // create and return a new rational. The mediant of two rationals is
    // the sum of their numerators divided by the sum of their denominators.
    public static Rational mediant(Rational r, Rational s) {
        
        return new Rational ((r.num + s.num),(r.den + s.den));

    }

    // return gcd(|m|, |n|)
    private static int gcd(int m, int n) {
        if (m < 0) m = -m;
        if (n < 0) n = -n;
        if (0 == n) return m;
        else return gcd(n, m % n);
    }

	// return least common multiple: lcm(|m|, |n|)
    private static int lcm(int m, int n) {
        if (m < 0) m = -m;
        if (n < 0) n = -n;
        return m * (n / gcd(m, n));    // parentheses important to avoid overflow
    }
    
    // return a * b,
    public Rational times(Rational b) {
        Rational a = this;

        // reduce p1/q2 and p2/q1, then multiply, where a = p1/q1 and b = p2/q2
        Rational c = new Rational(a.num, b.den);
        Rational d = new Rational(b.num, a.den);
        return new Rational ((c.num * d.den) , (d.num * c.den));
    }


    // return a + b
    public Rational plus(Rational b) {
        Rational a = this;
        
        // special cases
        
        // Find gcd of numerators and denominators
       

        // add cross-product terms for numerator--You may use lcm()
      
        // multiply back in

        return new Rational((this.num * b.den)+(b.num * this.den), (this.den*b.den));
    }

    // return -a
    public Rational negate() {
        return new Rational(-num, den);
    }

    // return |a|
    public Rational abs() {
       return new Rational(Math.abs(this.num), Math.abs(this.den));
    }

    // return a - b
    public Rational minus(Rational b) {
        return new Rational(((this.num * b.den) - (b.num * this.den)), (b.den *this.den));
        
			
    }


    // if (this) is num/den then return a new Rational with den/num
    public Rational reciprocal() { 
        return new Rational(this.den, this.num);

    }

    // return a / b
    public Rational divides(Rational b) {
        Rational a = this;
        return a.times(b.reciprocal());
    }


    // test client
    public static void main(String[] args) {
        Rational x, y, z;

        // 1/2 + 1/3 = 5/6
        x = new Rational(1, 2);
        y = new Rational(1, 3);
        z = x.plus(y);
        System.out.println(z);

        // 8/9 + 1/9 = 1
        x = new Rational(8, 9);
        y = new Rational(1, 9);
        z = x.plus(y);
        System.out.println(z);

        // 1/200000000 + 1/300000000 = 1/120000000
        x = new Rational(1, 200000000);
        y = new Rational(1, 300000000);
        z = x.plus(y);
       System.out.println(z);

        // 1073741789/20 + 1073741789/30 = 1073741789/12
        x = new Rational(1073741789, 20);
        y = new Rational(1073741789, 30);
        z = x.plus(y);
       System.out.println(z);

        //  4/17 * 17/4 = 1
        x = new Rational(4, 17);
        y = new Rational(17, 4);
        z = x.times(y);
        System.out.println(z);

        // 3037141/3247033 * 3037547/3246599 = 841/961 
        x = new Rational(3037141, 3247033);
        y = new Rational(3037547, 3246599);
        z = x.times(y);
     	System.out.println(z);

        // 1/6 - -4/-8 = -1/3
        x = new Rational( 1,  6);
        y = new Rational(-4, -8);
        z = x.minus(y);
        System.out.println(z);
    }

}     