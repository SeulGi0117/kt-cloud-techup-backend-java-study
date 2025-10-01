package study.seulgi.java.ch01.streamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Problem03_QuantityByStatus {
	// 기존코드를 stream api로 변경하기.
	// 조건: 아래 Order 목록에서 상태별(Status) 총 수량을 Map<Status, Integer>로 반환하기.

	enum Status {NEW, PROCESSING, COMPLETED, CANCELED}

	static class Order {
		final Status status;
		final int  quantity;

		Order(Status status, int quantity){
			this.status = status;
			this.quantity = quantity;
		}
	}

	// 아래의 메서드 로직을 Stream으로 수정할 것
	static Map<Status, Integer> quantityByStatus(List<Order> orders){
		// Map<Status, Integer> acc = new HashMap<>();
		// for(Order o: orders){
		// 	acc.put(o.status, acc.getOrDefault(o.status, 0) + o.quantity);
		// 	// getOrDefalut 키에 해당하는 값이 있으면 그 값을 반환하고, 없으면 기본값(defaultValue)을 반환하는 기능
		// }
		// return acc;

		// 로직부터. 생각해보자.
		// for 문을 돌면서 accumulator에 quantity가 추가된다.
		// status 별로 값을 묶는다.
		// 수량을 더한다.

		Map<Status, Integer> acc = orders.stream()
			.collect(Collectors.groupingBy(
				o -> o.status,
				Collectors.summingInt(o-> o.quantity)
			));
		return acc;
	}

	public static void main(String [] args){
		List<Order> orders = Arrays.asList(
			new Order(Status.NEW,3),
			new Order(Status.COMPLETED, 5),
			new Order(Status.NEW, 2),
			new Order(Status.CANCELED, 1),
			new Order(Status.COMPLETED, 7)
		);
		System.out.println(quantityByStatus(orders));
		// 기대: {NEW=5, COMPLETED=12, CANCELED=1, PROCESSING=0(없으면 키 없음)}
	}
}
