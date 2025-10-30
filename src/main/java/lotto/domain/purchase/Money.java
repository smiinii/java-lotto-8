package lotto.domain.purchase;

public class Money {

    private final int money;

    private Money(int number) {
        validatePositive(number);
        this.money = number;
    }

    public static Money of(int number) {
        return new Money(number);
    }

    public int getMoney() {
        return money;
    }

    private static void validatePositive(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("양의 정수가 아닙니다.");
        }
    }
}
