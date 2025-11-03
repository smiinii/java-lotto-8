package lotto.domain.result;

import lotto.domain.Money;
import lotto.domain.Lottos;

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
}
