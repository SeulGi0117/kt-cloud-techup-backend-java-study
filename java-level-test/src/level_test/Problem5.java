package level_test;
import java.util.*;

public class Problem5 {
    // 5번 문제는 잘 모르겠습니다 ㅠㅠ
    public static int countPrimes(int N) {
        if (N < 2) return 0;

        List<Integer> primes = new ArrayList<>();
        for (int x = 2; x <= N; x++) {
            boolean isPrime = true;

            for (int i = 0; i < primes.size(); i++) {
                int p = primes.get(i);
                if (p * p > x) break;
                if (x % p == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) primes.add(x);
        }
        return primes.size();
    }
    public static void main(String[] args) {
        int[] Ns = {10, 20, 100, 1_000_000};
        for (int i = 0; i < Ns.length; i++) {
            int N = Ns[i];
            System.out.println("N=" + N + " ⇒ primes=" + countPrimes(N));
        }
    }
}
