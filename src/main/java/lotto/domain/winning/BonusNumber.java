package lotto.domain.winning;

import lotto.domain.rule.LottoRule;

public class BonusNumber {

    private final int bonusNumber;

    private BonusNumber(int number) {
        validateRange(number);
        this.bonusNumber = number;
    }

    public static BonusNumber of(int number) {
        return new BonusNumber(number);
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    private void validateRange(int number) {
        if (number < LottoRule.MIN || number > LottoRule.MAX) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호의 범위는 " + LottoRule.MIN
                    + " ~ " + LottoRule.MAX + " 입니다.");
        }
    }
}
