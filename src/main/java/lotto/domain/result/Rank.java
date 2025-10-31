package lotto.domain.result;

import lotto.domain.ticket.Lotto;
import lotto.domain.winning.BonusNumber;
import lotto.domain.winning.WinningNumbers;

import java.util.ArrayList;
import java.util.List;

public enum Rank {

    FIRST(6,false, 2000000000),
    SECOND(5, true, 30000000),
    THIRD(5, false, 1500000),
    FOURTH(4, false, 50000),
    FIFTH(3, false, 5000),
    NONE(0, false, 0);

    private final int winningNumberCount;
    private final boolean isBonusNumber;
    private final long prizeMoney;

    Rank(int winningNumberCount, boolean isBonusNumber, long prizeMoney) {
        this.winningNumberCount = winningNumberCount;
        this.isBonusNumber = isBonusNumber;
        this.prizeMoney = prizeMoney;
    }

    public static Rank from(int winningNumbersMatchCount, boolean bonusNumberMatchResult) {
        if (winningNumbersMatchCount == 6) return FIRST;
        if (winningNumbersMatchCount == 5 && bonusNumberMatchResult) return SECOND;
        if (winningNumbersMatchCount == 5) return THIRD;
        if (winningNumbersMatchCount == 4) return FOURTH;
        if (winningNumbersMatchCount == 3) return FIFTH;
        return NONE;
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }
}
