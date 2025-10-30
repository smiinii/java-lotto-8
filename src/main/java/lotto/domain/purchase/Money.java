package lotto.domain.purchase;

import lotto.domain.ticket.Lotto;

public class Money {

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

    private void validatePositive(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("[ERROR] 양의 정수가 아닙니다.");
        }
    }

    private void validateDivisibleByLottoPrice(int number) {
        if ((number % LottoPrice.UNIT) != ZERO) {
            throw new IllegalArgumentException("[ERROR] " + LottoPrice.UNIT + "원으로 딱 나누어 떨어져야 합니다.");
        }
    }
}
