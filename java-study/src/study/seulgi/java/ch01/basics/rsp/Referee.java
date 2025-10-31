// package study.seulgi.java.ch01.basics.rsp;
//
// public class Referee {
// 	public void judge(int playerHand, int computerPock){
// 		// 비기고, 이기고, 지고를 판별함
// 		// ㄱ가위(1)은 - 보(3)을 이김 = -2
// 		// 바위(2)는 - 가위(1)를 이김= 1
// 		// 보(3)는 - 바위(2)를 이김= 1
//
// 		// 가위(1)는 바위(2)에게 짐 = -1
// 		// 바위(2) - 보 (3) = -1
// 		// 3 - 1 =2
//
// 		boolean isPlayerWin(RSPWinCounter.Move player, RSPWinCounter.Move com) {
// 			return (player == RSPWinCounter.Move.SCISSOR && com == RSPWinCounter.Move.PAPER)
// 				|| (player == RSPWinCounter.Move.ROCK && com == RSPWinCounter.Move.SCISSOR)
// 				|| (player == RSPWinCounter.Move.PAPER && com == RSPWinCounter.Move.ROCK);
// 	}
// }
