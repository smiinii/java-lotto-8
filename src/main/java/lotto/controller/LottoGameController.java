package lotto.controller;

import lotto.domain.*;
import lotto.domain.result.LottoIssueResult;
import lotto.domain.result.LottoGameResult;
import lotto.util.InputParser;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Map;

public class LottoGameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoIssuer lottoIssuer;

    public LottoGameController(InputView inputView, OutputView outputView, LottoIssuer lottoIssuer) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoIssuer = lottoIssuer;
    }

    public void run() {
        LottoIssueResult lottoIssueResult = requestLottoIssuanceUntilValid();
        outputView.printIssuedLotto(lottoIssueResult.issuedLottosView());

        WinningNumbers pending = requestWinningNumbersUntilValid();
        WinningNumbers winningNumbers = requestBonusNumberUntilValid(pending);

        Map<Rank, Integer> matchResult = lottoIssueResult.match(winningNumbers);
        double yield = calculateYield(matchResult, lottoIssueResult.getPurchaseMoney());

        LottoGameResult lottoGameResult = new LottoGameResult(matchResult, yield);
        outputView.printMatchResult(lottoGameResult.getMatchResult());
        outputView.printTotalPrizeAmount(lottoGameResult.getLottoYield());
    }

    private LottoIssueResult requestLottoIssuanceUntilValid() {
        while (true) {
            try{
                int parseNumber = InputParser.parseNumber(inputView.readPurchaseAmount());
                Money purchaseAmount = Money.of(parseNumber);
                Lottos issuedLottos = lottoIssuer.issue(purchaseAmount);
                return new LottoIssueResult(issuedLottos, purchaseAmount);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private WinningNumbers requestWinningNumbersUntilValid() {
        while (true) {
            try{
                String input = inputView.readWinningNumbers();
                List<Integer> parseWinningNumbers = InputParser.parseWinningNumbers(input);
                return WinningNumbers.fromWinningNumbers(parseWinningNumbers);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private WinningNumbers requestBonusNumberUntilValid(WinningNumbers winningNumbers) {
        while (true) {
            try{
                String input = inputView.readBonusNumber();
                int parseBonusNumber = InputParser.parseNumber(input);
                return winningNumbers.withBonus(parseBonusNumber);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private double calculateYield(Map<Rank, Integer> matchResult, Money money) {
        double totalPrizeAmount = matchResult.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
        double yield = (totalPrizeAmount / money.getMoney()) * 100;
        return Math.round(yield * 10.0) / 10.0;
    }
}
