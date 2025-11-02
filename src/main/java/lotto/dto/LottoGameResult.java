package lotto.dto;

import lotto.domain.Rank;

import java.util.EnumMap;

public class LottoGameResult {
    private final EnumMap<Rank, Integer> matchResult;
    private final double lottoYield;

    public LottoGameResult(EnumMap<Rank, Integer> matchResult, double lottoYield) {
        this.matchResult = matchResult;
        this.lottoYield = lottoYield;
    }

    public EnumMap<Rank, Integer> getMatchResult() {
        return matchResult;
    }

    public double getLottoYield() {
        return lottoYield;
    }
}
