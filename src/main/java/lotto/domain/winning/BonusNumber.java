package lotto.domain.winning;

import lotto.domain.rule.LottoRule;

import java.util.List;

public class BonusNumber {

    private final int bonusNumber;

    private BonusNumber(int number, List<Integer> winningNumbers) {
        validateRange(number);
        validateDuplicationWinningNumbers(number, winningNumbers);
        this.bonusNumber = number;
    }

    public static BonusNumber of(int number, List<Integer> winningNumbers) {
        return new BonusNumber(number, winningNumbers);
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

    private void validateDuplicationWinningNumbers(int number, List<Integer> winningNumbers) {
        for (Integer winningNumber : winningNumbers) {
            if (number == winningNumber) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호와 중복됩니다.");
            }
        }
    }
}
