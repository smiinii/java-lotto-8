package lotto.domain;

import java.util.List;

public final class WinningNumbers {

    private final LottoNumber winningNumbers;
    private final Integer bonusNumber;

    private WinningNumbers(LottoNumber winningNumbers, Integer bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static WinningNumbers fromWinningNumbers(List<Integer> numbers) {
        return new WinningNumbers(LottoNumber.from(numbers), null);
    }

    public WinningNumbers withBonus(int bonus) {
        if (bonusNumber != null) {
            throw new IllegalStateException("[ERROR] 보너스 번호가 이미 설정되어 있습니다.");
        }
        validateRange(bonus);
        validateBonusNotDuplicated(bonus);
        return new WinningNumbers(winningNumbers, bonus);
    }

    public LottoNumber getWinningNumbers() {
        return winningNumbers;
    }

    public int getBonusNumber() {
        requireBonusNumber();
        return bonusNumber;
    }

    public Rank match(Lotto lotto) {
        requireBonusNumber();
        int matchCount = winningNumbers.matchCountWith(lotto.getNumbers());
        boolean bonusMatch = lotto.getNumbers().contains(bonusNumber);
        return Rank.from(matchCount, bonusMatch);
    }

    private void validateRange(int number) {
        if (!winningNumbers.isInRange(number)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호의 범위는 " + LottoNumber.minRange()
                    + " ~ " + LottoNumber.maxRange() + " 입니다.");
        }
    }

    private void validateBonusNotDuplicated(int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호가 당첨 번호와 중복됩니다.");
        }
    }

    private void requireBonusNumber() {
        if (bonusNumber == null) {
            throw new IllegalStateException("[ERROR] 보너스 번호가 아직 설정되지 않았습니다.");
        }
    }
}
