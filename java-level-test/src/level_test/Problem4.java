package level_test;
import java.util.*;

public class Problem4 {
    // arr의 값을 짝수/홀수로 분리하고, 각 목록에서 중복 제거 후 오름차순으로 출력
    public static void splitDedupSortAndPrint(int[] arr) {

        List<Integer> even = new ArrayList<>();
        List<Integer> odd = new ArrayList<>();

        for (int i=0; i<arr.length; i++){
            if(arr[i]%2==0){
                even.add(arr[i]);
            }
            else{
                odd.add(arr[i]);
            }
        }
        Set<Integer> evenSet = new TreeSet<>(even);
        Set<Integer> oddSet = new TreeSet<>(odd);

        System.out.print("even: ");
        for (int n : evenSet) {
            System.out.print(n + " ");
        }
        System.out.println();

        System.out.print("odd: ");
        for (int n : oddSet) {
            System.out.print(n + " ");
        }
        System.out.println();

    }
    public static void main(String[] args) {
        int[] A = {5, 2, 3, 2, 8, 5, 1, 8, 0, 7 , 7 , 3, 10, 10, 9};
        splitDedupSortAndPrint(A);
// 기대 출력 예
// even: 0 2 8 10
// odd: 1 3 5 7 9
    }
}
