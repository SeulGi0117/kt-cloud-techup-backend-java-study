package study.seulgi.java.ch01.basics.problem;

// 자동 줄 정리 단축기 op cmd l
public class Problem02_GradeEvaluator {
	enum Grade {A, B, C, D, F}

	public static Grade getGradeIfElse(int score) {
		if (score > 100 || score < 0) {
			return Grade.F;
		}

		if (score >= 90)
			return Grade.A;
		else if (score >= 80)
			return Grade.B;
		else if (score >= 70)
			return Grade.C;
		else if (score >= 60)
			return Grade.D;
		else
			return Grade.F;
	}

	public static Grade getGradeSwitch(int score) {
		switch (score / 10) {
			case 10:
			case 9:
				return Grade.A;
			case 8:
				return Grade.B;
			case 7:
				return Grade.C;
			case 6:
				return Grade.D;
			default:
				return Grade.F;
		}
	}

	public static void main(String[] args) {
		int score = 78;

		Grade res1 = getGradeIfElse(score);
		Grade res2 = getGradeSwitch(score);

		System.out.println("원 점수: " + score);
		System.out.println("If-else 문만 사용한 점수 계산: " + res1);
		System.out.println("Switch를 사용한 점수 계산: " + res2);
	}
}
