public class calculate {
    public long fact(long n)
    {
        if(n==0 || n==1)
            return 1;
        long res = 1;
        for (int i = 2; i <= n; i++)
            res = res * i;
        return res;
    }

    public <T> Long combination(T in, T ir) {
            if(in instanceof Double||ir instanceof Double||in instanceof Float||ir instanceof Float){
                throw new IllegalArgumentException("Invalid input: n and r must be non-negative and Integer value.");
            }
            long n= (int)in;
            long r=(int)ir;
            if (n <= 0 || n < r || n>10000) {
                throw new IllegalArgumentException("Invalid input: n and r must be non-negative and n must be greater than or equal to r.");
            }
            return fact(n) / (fact(r) * fact(n - r));
    }

}
