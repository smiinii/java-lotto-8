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
import lotto.util.InputParser;

import java.util.List;
import java.util.Map;

public class LottoGameService {

    private final LottoIssuer lottoIssuer;
    private final ResultCalculator resultCalculator;

    public LottoGameService(LottoIssuer lottoIssuer, ResultCalculator resultCalculator) {
        this.lottoIssuer = lottoIssuer;
        this.resultCalculator = resultCalculator;
    }

    public LottoIssueResult issue(String input) {
        int parseNumber = InputParser.parseNumber(input);
        Money purchaseMoney = Money.of(parseNumber);
        Lottos issuedLottos = lottoIssuer.issue(purchaseMoney);
        return new LottoIssueResult(issuedLottos, purchaseMoney);
    }

    public WinningNumbers parseWinningNumbers(String input) {
        List<Integer> parseWinningNumbers = InputParser.parseWinningNumbers(input);
        return WinningNumbers.from(parseWinningNumbers);
    }

    public BonusNumber parseBonusNumber(String input, WinningNumbers winningNumbers) {
        int parseBonusNumber = InputParser.parseNumber(input);
        return BonusNumber.of(parseBonusNumber, winningNumbers.getWinningNumbers());
    }

    public LottoGameResult matchResult(LottoIssueResult lottoIssueResult, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        Map<Rank, Integer> matchResult = matchReult(lottoIssueResult.getIssuedLottos(), winningNumbers, bonusNumber);
        double lottoYield = calculateYield(matchResult, lottoIssueResult.getPurchaseMoney());
        return new LottoGameResult(matchResult, lottoYield);
    }

    private Map<Rank, Integer> matchReult(Lottos lottos, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        return resultCalculator.matchReult(lottos, winningNumbers, bonusNumber);
    }

    private double calculateYield(Map<Rank, Integer> matchResult, Money money) {
        return resultCalculator.calculateYield(matchResult, money);
    }
}
