package level_test;

public class Problem3 {
    public static void analyze(String s) {

        if (s==null || s.trim().isEmpty()) {
            System.out.println("count=0., longest=");
            return;
        }

        String[] word = s.trim().split("\\s+");

        int count = word.length;
        String longest = word[0];

        for (int i=0; i<word.length; i++){
            String w = word[i];
            if(word.length > longest.length()){
                longest=w;

            }
        }
        System.out.println("count=" + count + ", longest=" + longest);
    }
    public static void main(String[] args) {
        String s1 = " Java is a good language ";
        String s2 = "";
        analyze(s1); // count=5, longest=general-purpose
        analyze(s2); // count=0, longest=
    }
}
