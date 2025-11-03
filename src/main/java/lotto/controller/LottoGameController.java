package lotto.controller;

import lotto.domain.Money;
import lotto.domain.WinningNumbers;
import lotto.dto.LottoIssueResult;
import lotto.dto.LottoGameResult;
import lotto.service.LottoGameService;
import lotto.util.InputParser;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoGameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGameService lottoGameService;

    public LottoGameController(InputView inputView, OutputView outputView, LottoGameService lottoGameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGameService = lottoGameService;
    }

    public void run() {
        LottoIssueResult lottoIssueResult = requestLottoIssuanceUntilValid();
        outputView.printIssuedLotto(lottoIssueResult.getIssuedLottos());

        WinningNumbers pending = requestWinningNumbersUntilValid();

        WinningNumbers winningNumbers = requestBonusNumberUntilValid(pending);

        LottoGameResult lottoGameResult = lottoGameService.matchResult(lottoIssueResult, winningNumbers);
        outputView.printMatchResult(lottoGameResult.getMatchResult());
        outputView.printTotalPrizeAmount(lottoGameResult.getLottoYield());
    }

    private LottoIssueResult requestLottoIssuanceUntilValid() {
        while (true) {
            try{
                int parseNumber = InputParser.parseNumber(inputView.readPurchaseAmount());
                Money purchaseAmount = Money.of(parseNumber);
                return lottoGameService.issue(purchaseAmount);
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
                return lottoGameService.createWinningNumbers(parseWinningNumbers);
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
                return lottoGameService.createBonusNumber(parseBonusNumber, winningNumbers);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
