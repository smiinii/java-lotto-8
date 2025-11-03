package lotto.domain.result;

import lotto.domain.Money;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;

import java.util.EnumMap;

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

    public EnumMap<Rank, Integer> match(WinningNumbers winningNumbers) {
        return issuedLottos.matchResult(winningNumbers);
    }
}
