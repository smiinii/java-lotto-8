package lotto.domain.result;

import lotto.domain.Money;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;

import java.util.EnumMap;
import java.util.Map;

public class LottoIssueResult {

    private final Lottos issuedLottos;
    private final Money purchaseMoney;

    public LottoIssueResult(Lottos issuedLottos, Money purchaseMoney) {
        this.issuedLottos = issuedLottos;
        this.purchaseMoney = purchaseMoney;
    }

    public Lottos getIssuedLottos() {
        return issuedLottos;
    }

    public Money getPurchaseMoney() {
        return purchaseMoney;
    }

    public Map<Rank, Integer> match(WinningNumbers winningNumbers) {
        EnumMap<Rank,Integer> matchResult = issuedLottos.matchResult(winningNumbers);
        return Map.copyOf(matchResult);
    }
}
