package lotto.domain;

public enum Rank {
    FIFTH(3, false, 5000),
    FOURTH(4, false, 50000),
    THIRD(5, false, 1500000),
    SECOND(5, true, 30000000),
    FIRST(6,false, 2000000000),
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

    public int getWinningNumberCount() {
        return winningNumberCount;
    }

    public boolean isBonusNumber() {
        return isBonusNumber;
    }
}
