package lotto.domain.result;

import lotto.domain.*;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoIssueResult {

    private final Lottos issuedLottos;
    private final Money purchaseMoney;

    public LottoIssueResult(Lottos issuedLottos, Money purchaseMoney) {
        this.issuedLottos = issuedLottos;
        this.purchaseMoney = purchaseMoney;
    }

    public Money getPurchaseMoney() {
        return purchaseMoney;
    }

    public List<List<Integer>> issuedLottosView() {
        return issuedLottos.getLottos().stream()
                .map(Lotto::values)
                .toList();
    }

    public Map<Rank, Integer> match(WinningNumbers winningNumbers) {
        EnumMap<Rank,Integer> matchResult = issuedLottos.matchResult(winningNumbers);
        return Map.copyOf(matchResult);
    }
}
