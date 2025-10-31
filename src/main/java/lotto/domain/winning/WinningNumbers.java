package lotto.domain.winning;

import lotto.domain.rule.LottoRule;

import java.util.LinkedHashSet;
import java.util.List;

public final class WinningNumbers {

    private final List<Integer> winningNumbers;

    private WinningNumbers(List<Integer> numbers) {
        validateCount(numbers);
        validateRange(numbers);
        validateDuplication(numbers);
        this.winningNumbers = List.copyOf(numbers);
    }

    public static WinningNumbers from(List<Integer> numbers) {
        return new WinningNumbers(numbers);
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    private void validateCount(List<Integer> numbers) {
        if (numbers.size() != LottoRule.COUNT) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호 개수는 " + LottoRule.COUNT + "개 입니다.");
        }
    }

    private void validateRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number < LottoRule.MIN || number > LottoRule.MAX) {
                throw new IllegalArgumentException("[ERROR] 당첨 금액 번호의 범위는 " + LottoRule.MIN
                        + " ~ " + LottoRule.MAX + " 입니다.");
            }
        }
    }

    private void validateDuplication(List<Integer> numbers) {
        LinkedHashSet<Integer> duplicatedNumbers = new LinkedHashSet<>(numbers);
        if (numbers.size() != duplicatedNumbers.size()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호가 중복됩니다.");
        }
    }

}
