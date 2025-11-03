package lotto.domain.winning;

import lotto.domain.Rank;
import lotto.domain.rule.LottoRule;
import lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public final class WinningNumbers {

    private final List<Integer> winningNumbers;
    private final Integer bonusNumber;

    private WinningNumbers(List<Integer> winningNumbers, Integer bonusNumber) {
        this.winningNumbers = List.copyOf(winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    public static WinningNumbers fromWinningNumbers(List<Integer> numbers) {
        validateCount(numbers);
        validateRange(numbers);
        validateDuplication(numbers);
        return new WinningNumbers(numbers, null);
    }

    public WinningNumbers withBonus(int bonus) {
        if (bonusNumber != null) {
            throw new IllegalStateException("[ERROR] 보너스 번호가 이미 설정되어 있습니다.");
        }
        validateRange(bonus);
        validateDuplicationWinningNumbers(bonus, winningNumbers);
        return new WinningNumbers(winningNumbers, bonus);
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public int getBonusNumber() {
        requireBonusNumber();
        return bonusNumber;
    }

    public Rank match(Lotto lotto) {
        requireBonusNumber();
        List<Integer> copy = new ArrayList<>(lotto.getNumbers());
        copy.retainAll(winningNumbers);
        int matchCount = copy.size();

        boolean bonusMatch = lotto.getNumbers().contains(bonusNumber);
        return Rank.from(matchCount, bonusMatch);
    }

    private static void validateCount(List<Integer> numbers) {
        if (numbers.size() != LottoRule.COUNT) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호 개수는 " + LottoRule.COUNT + "개 입니다.");
        }
    }

    private static void validateRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number < LottoRule.MIN || number > LottoRule.MAX) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호의 범위는 " + LottoRule.MIN
                        + " ~ " + LottoRule.MAX + " 입니다.");
            }
        }
    }

    private static void validateDuplication(List<Integer> numbers) {
        LinkedHashSet<Integer> duplicatedNumbers = new LinkedHashSet<>(numbers);
        if (numbers.size() != duplicatedNumbers.size()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호가 중복됩니다.");
        }
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
                throw new IllegalArgumentException("[ERROR] 보너스 번호가 당첨 번호와 중복됩니다.");
            }
        }
    }

    private void requireBonusNumber() {
        if (bonusNumber == null) {
            throw new IllegalStateException("[ERROR] 보너스 번호가 아직 설정되지 않았습니다.");
        }
    }
}
