package study.seulgi.java.ch01.streamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Problem07_Top3CompletedProducts {
	enum Status {NEW, PROCESSING, COMPLETED, CANCELED}

	public static class Order {
		final String product;  // 상품명
		final int quantity;  // 수량
		final Status status;  // 상태

		Order(String product, int quantity, Status status) {
			this.product = product;
			this.quantity = quantity;
			this.status = status;
		}
	}

	// 판매량 기준 Top3
	// 아래 로직을 Stream API로 변경해 보세요
	static List<String> top3ProductsByQuantity(List<Order> orders) {
		var result = orders.stream()
			.filter(o -> o.status == Status.COMPLETED)
			.collect(Collectors.groupingBy(o -> o.product,
				Collectors.summingInt(o -> o.quantity)))
			.entrySet().stream()

			// 여기서부터는 어려운것 같아요.
			.sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
			.limit(3)
			.map(Map.Entry::getKey)
			.toList();
		return result;
	}

	public static void main(String[] args) {
		List<Order> orders = Arrays.asList(
			new Order("Apple", 10, Status.COMPLETED),    // 10
			new Order("Banana", 20, Status.COMPLETED),  // 20
			new Order("Apple", 5, Status.COMPLETED),    // +5 = 15
			new Order("Kiwi", 5, Status.PROCESSING),    // 제외
			new Order("Kiwi", 7, Status.COMPLETED)      // 7
		);

		System.out.println(top3ProductsByQuantity(orders));
		// 기대: [Banana, Apple, Kiwi]
	}
}
