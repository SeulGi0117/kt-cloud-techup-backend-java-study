package level_test;

public class Problem1 {
    public static String gradeOf(int s){
        if(s<0 || s>100) return "INVALID";
        if(s>=90) return "A";
        if(s>=80) return "B";
        if(s>=70) return "C";
        if(s>=60) return "D";
        return "F";
    }

    public static void main(String[] args) {
        int[] samples = {70, 90, 59, 101, -3, 100, 0, 89, 79, 69};

        for (int i=0; i<samples.length; i++){
            int s = samples[i];
            System.out.println(s + " => "+gradeOf(s));
        }
    }
}
