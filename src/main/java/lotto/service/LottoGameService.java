package lotto.service;

import lotto.domain.LottoIssuer;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.Lottos;
import lotto.domain.winning.WinningNumbers;
import lotto.dto.LottoIssueResult;
import lotto.dto.LottoGameResult;

import java.util.EnumMap;
import java.util.List;

public class LottoGameService {

    private final LottoIssuer lottoIssuer;

    public LottoGameService(LottoIssuer lottoIssuer) {
        this.lottoIssuer = lottoIssuer;
    }

    public LottoIssueResult issue(Money purchaseAmount) {
        Lottos issuedLottos = lottoIssuer.issue(purchaseAmount);
        return new LottoIssueResult(issuedLottos, purchaseAmount);
    }

    public WinningNumbers createWinningNumbers(List<Integer> parseNumbers) {
        return WinningNumbers.fromWinningNumbers(parseNumbers);
    }

    public WinningNumbers createBonusNumber(int parseNumber, WinningNumbers winningNumbers) {
        return winningNumbers.withBonus(parseNumber);
    }

    public LottoGameResult matchResult(LottoIssueResult lottoIssueResult, WinningNumbers winningNumbers) {
        EnumMap<Rank, Integer> matchResult = match(lottoIssueResult.getIssuedLottos(), winningNumbers);
        double lottoYield = calculateYield(matchResult, lottoIssueResult.getPurchaseMoney());
        return new LottoGameResult(matchResult, lottoYield);
    }

    private EnumMap<Rank, Integer> match(Lottos issuedLottos, WinningNumbers winningNumbers) {
        return issuedLottos.matchResult(winningNumbers);
    }

    private double calculateYield(EnumMap<Rank, Integer> matchResult, Money money) {
        double totalPrizeAmount = matchResult.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();

        double yield = (totalPrizeAmount / money.getMoney()) * 100;

        return Math.round(yield * 10.0) / 10.0;
    }
}
