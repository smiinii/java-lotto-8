package lotto.dto;

import lotto.domain.purchase.Money;
import lotto.domain.ticket.Lottos;

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
