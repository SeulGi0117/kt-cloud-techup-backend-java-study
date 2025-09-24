package level_test;

public class Problem2 {
    public static void printStats(int[] a){
        int min=a[0], max=a[0];
        long sum=0;

        for(int i=0; i<a.length; i++){
            if (min>a[i]) {min=a[i];}
            if (max<a[i]) {max=a[i];}
            sum+=a[i];
        }
        double avg = (double) sum / a.length;

        System.out.printf("min=%d max=%d sum=%d avg=%.2f%n", min, max, sum, avg);
    }

    public static void main(String[] args){
        int[] A = {3,7,-2,10,4};
        int[] B = {5};
        printStats(A);  // min =-2 max=10 sum=22 avg=4.40
        printStats(B);  // min=5 max=5 sum=5 avg=5.00
    }
}
