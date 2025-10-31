// package com.kt.pracitce.immutable.thread.immutable;
//
// public class ThreadSafe {
// 	public static void main(String[] args) {
// 		Point immutablePoint = new Point(1, 2);
//
// 		Thread thread1 = new Thread(() -> {
// 			for (int i = 0; i < 100; i++) {
// 				String msg = immutablePoint.x + "," + immutablePoint.y;
// 				System.out.println("일꾼1 " + msg);
// 			}
//
// 		}).start();
// 		;
// 		Thread thread2 = new Thread(() -> {
// 			for (int i = 0; i < 100; i++) {
// 				String msg = immutablePoint.x + "," + immutablePoint.y;
// 				System.out.println("일꾼2 " + msg);
// 			}
//
// 		}).start();
// 		;
// 		Thread thread3 = new Thread(() -> {
// 			for (int i = 0; i < 100; i++) {
// 				String msg = immutablePoint.x + "," + immutablePoint.y;
// 				System.out.println("일꾼3 " + msg);
// 			}
// 		}).start();
// 		;
//
// 	}
// }
