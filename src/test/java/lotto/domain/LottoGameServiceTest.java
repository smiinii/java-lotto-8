package lotto.domain;

import lotto.dto.LottoGameResult;
import lotto.dto.LottoIssueResult;
import lotto.service.LottoGameService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGameServiceTest {

    @Test
    @DisplayName("로또 당첨 결과를 통해 수익률을 계산한다.")
    void matchResultYieldTest() {
        // given
        LottoNumberGenerator generator = new FixedLottoNumberGenerator(List.of(1,2,3,4,5,6));
        LottoIssuer lottoIssuer = new LottoIssuer(generator);
        LottoGameService service = new LottoGameService(lottoIssuer);
        Money purchaseMoney = Money.of(1000);

        LottoIssueResult issueResult = service.issue(purchaseMoney);

        WinningNumbers pending = service.createWinningNumbers(List.of(1,2,3,7,8,9));
        WinningNumbers winningNumbers = service.createBonusNumber(10, pending);

        // when
        LottoGameResult gameResult = service.matchResult(issueResult, winningNumbers);

        // then
        assertThat(gameResult.getLottoYield()).isEqualTo(500);
    }
}
