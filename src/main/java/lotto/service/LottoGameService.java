package lotto.service;

import lotto.domain.issuance.LottoIssuer;
import lotto.domain.purchase.Money;
import lotto.domain.result.Rank;
import lotto.domain.result.ResultCalculator;
import lotto.domain.ticket.Lottos;
import lotto.domain.winning.BonusNumber;
import lotto.domain.winning.WinningNumbers;
import lotto.dto.LottoIssueResult;
import lotto.dto.LottoGameResult;

import java.util.EnumMap;
import java.util.List;

public class LottoGameService {

    private final LottoIssuer lottoIssuer;
    private final ResultCalculator resultCalculator;

    public LottoGameService(LottoIssuer lottoIssuer, ResultCalculator resultCalculator) {
        this.lottoIssuer = lottoIssuer;
        this.resultCalculator = resultCalculator;
    }

    public LottoIssueResult issue(Money purchaseAmount) {
        Lottos issuedLottos = lottoIssuer.issue(purchaseAmount);
        return new LottoIssueResult(issuedLottos, purchaseAmount);
    }

    public WinningNumbers createWinningNumbers(List<Integer> parseNumbers) {
        return WinningNumbers.from(parseNumbers);
    }

    public BonusNumber createBonusNumber(int parseNumber, WinningNumbers winningNumbers) {
        return BonusNumber.of(parseNumber, winningNumbers.getWinningNumbers());
    }

    public LottoGameResult matchResult(LottoIssueResult lottoIssueResult, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        EnumMap<Rank, Integer> matchResult = match(lottoIssueResult.getIssuedLottos(), winningNumbers, bonusNumber);
        double lottoYield = calculateYield(matchResult, lottoIssueResult.getPurchaseMoney());
        return new LottoGameResult(matchResult, lottoYield);
    }

    private EnumMap<Rank, Integer> match(Lottos issuedLottos, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        return issuedLottos.matchResult(winningNumbers, bonusNumber);
    }

    private double calculateYield(EnumMap<Rank, Integer> matchResult, Money money) {
        return resultCalculator.calculateYield(matchResult, money);
    }
}
