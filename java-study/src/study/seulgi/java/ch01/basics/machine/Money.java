package study.seulgi.java.ch01.basics.machine;

import java.util.Objects;

public class Money {
	private final int amount;

	public Money(int amount) {
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public boolean isLessThan(Money other) {
		// 이 돈이 지갑보다 작음? ㅇㅇ 작음. = ture
		return this.amount < other.amount;
	}

	public boolean isGreaterTeanOrEqual(Money other) {
		return this.amount > other.amount;
	}

	public boolean isZero() {
		// 현재 잔액이 0이면 true
		return this.amount == 0;
	}

	public Money add(Money other) {
		return new Money(this.amount + other.amount);
	}

	public Money subtract(Money other) {
		return new Money(this.amount - other.amount);
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		Money money = (Money)o;
		return amount == money.amount;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(amount);
	}
}