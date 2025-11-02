package lotto.domain;

public final class Money {

    private static final int UNIT = 1000;
    private static final int ZERO = 0;
    private final int money;

    private Money(int number) {
        validatePositive(number);
        validateDivisibleByLottoPrice(number);
        this.money = number;
    }

    public static Money of(int number) {
        return new Money(number);
    }

    public int getMoney() {
        return money;
    }

    public int purchasableCount() {
        return money / UNIT;
    }

    private void validatePositive(int number) {
        if (number < ZERO) {
            throw new IllegalArgumentException("[ERROR] 양의 정수가 아닙니다.");
        }
    }

    private void validateDivisibleByLottoPrice(int number) {
        if ((number % UNIT) != ZERO) {
            throw new IllegalArgumentException("[ERROR] " + UNIT + "원으로 딱 나누어 떨어져야 합니다.");
        }
    }
}
