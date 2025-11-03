package lotto.domain.result;

import lotto.domain.Rank;

import java.util.Map;

public class LottoGameResult {
    private final Map<Rank, Integer> matchResult;
    private final double lottoYield;

    public LottoGameResult(Map<Rank, Integer> matchResult, double lottoYield) {
        this.matchResult = matchResult;
        this.lottoYield = lottoYield;
    }

    public Map<Rank, Integer> getMatchResult() {
        return matchResult;
    }

    public double getLottoYield() {
        return lottoYield;
    }
}
