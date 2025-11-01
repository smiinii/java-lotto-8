package lotto;

import lotto.controller.LottoGameController;
import lotto.domain.generator.LottoNumberGenerator;
import lotto.domain.generator.UniqueRandomNumberGenerator;
import lotto.domain.issuance.LottoIssuer;
import lotto.domain.result.ResultCalculator;
import lotto.service.LottoGameService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        LottoNumberGenerator lottoNumberGenerator = new UniqueRandomNumberGenerator();
        LottoIssuer lottoIssuer = new LottoIssuer(lottoNumberGenerator);

        ResultCalculator resultCalculator = new ResultCalculator();
        LottoGameService lottoGameService = new LottoGameService(lottoIssuer, resultCalculator);

        LottoGameController lottoGameController = new LottoGameController(inputView, outputView, lottoGameService);
        lottoGameController.run();
    }
}
