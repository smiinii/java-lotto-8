package lotto.domain.winning;

import lotto.domain.rule.LottoRule;

import java.util.List;

public final class WinningNumbers {

    private final List<Integer> winningNumbers;

    private WinningNumbers(List<Integer> numbers) {
        validateCount(numbers);
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

}
