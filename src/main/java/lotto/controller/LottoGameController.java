package lotto.controller;

import lotto.domain.Money;
import lotto.domain.winning.BonusNumber;
import lotto.domain.winning.WinningNumbers;
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
        LottoIssueResult lottoIssueResult = askLottoIssue();
        outputView.printIssuedLotto(lottoIssueResult.getIssuedLottos());

        WinningNumbers winningNumbers = askWinningNumbers();

        BonusNumber bonusNumber = askBonusNumber(winningNumbers);

        LottoGameResult lottoGameResult = lottoGameService.matchResult(lottoIssueResult, winningNumbers, bonusNumber);
        outputView.printMatchResult(lottoGameResult.getMatchResult());
        outputView.printTotalPrizeAmount(lottoGameResult.getLottoYield());
    }

    private LottoIssueResult askLottoIssue() {
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

    private WinningNumbers askWinningNumbers() {
        while (true) {
            try{
                String input = inputView.readWinningNumbers();
                List<Integer> parseNumbers = InputParser.parseWinningNumbers(input);
                return lottoGameService.createWinningNumbers(parseNumbers);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private BonusNumber askBonusNumber(WinningNumbers winningNumbers) {
        while (true) {
            try{
                String input = inputView.readBonusNumber();
                int parseNumber = InputParser.parseNumber(input);
                return lottoGameService.createBonusNumber(parseNumber, winningNumbers);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
