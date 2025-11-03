package lotto;

import lotto.controller.LottoGameController;
import lotto.domain.LottoNumberGenerator;
import lotto.domain.UniqueRandomNumberGenerator;
import lotto.domain.LottoIssuer;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        LottoNumberGenerator lottoNumberGenerator = new UniqueRandomNumberGenerator();
        LottoIssuer lottoIssuer = new LottoIssuer(lottoNumberGenerator);

        LottoGameController lottoGameController = new LottoGameController(inputView, outputView, lottoIssuer);
        lottoGameController.run();
    }
}
