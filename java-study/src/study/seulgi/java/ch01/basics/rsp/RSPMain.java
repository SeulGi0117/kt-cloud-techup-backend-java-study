// package study.seulgi.java.ch01.basics.rsp;
//
// import java.util.Scanner;
//
// public class RSPMain {
// 	public static void main(String[] args) {
// 		System.out.println("1,2,3 하나 선택 가위바위보");
// 		try {
// 			var hand = new Scanner(System.in).nextInt();
// 			if(hand<1||hand>3){
// 				System.out.println("잘못된 입력입니다.(1~3)만 입력해주세요");
// 				return hand();
// 			}
// 		}catch(Exception e){}
// 		var player = new Player();
// 		var computer = new Computer();
// 		var referee = new Referee();
// 		var winCounter =0;
//
// 		var result = referee.judge(player.hand(), computer.pick());
//
//
// 		var playerHand = player.hand();
// 		var computerPick = computer.pick();
//
// 		System.out.println("플레이어 "+playerHand);
// 		System.out.println("컴퓨터 "+computerPick);
// 	}
// 	private static String mean(int num) {
// 		return switch(num){
// 			case 1-> "가위";
// 			case 2->"바위";
// 			case 3->"보";
// 			default -> "잘못";
// 		}
// 			}
// }
