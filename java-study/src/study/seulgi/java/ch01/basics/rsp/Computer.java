package study.seulgi.java.ch01.basics.rsp;
import java.util.*;

public class Computer {
	public int pick(){
		return new Random().nextInt(3)+1;
	}
}
