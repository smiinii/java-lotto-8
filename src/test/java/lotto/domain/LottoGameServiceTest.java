package lotto.domain;

import lotto.domain.generator.FixedLottoNumberGenerator;
import lotto.domain.generator.LottoNumberGenerator;
import lotto.domain.issuance.LottoIssuer;
import lotto.domain.purchase.Money;
import lotto.domain.winning.BonusNumber;
import lotto.domain.winning.WinningNumbers;
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

        WinningNumbers winningNumbers = service.createWinningNumbers(List.of(1,2,3,7,8,9));
        BonusNumber bonusNumber = service.createBonusNumber(10, winningNumbers);

        // when
        LottoGameResult gameResult = service.matchResult(issueResult, winningNumbers, bonusNumber);

        // then
        assertThat(gameResult.getLottoYield()).isEqualTo(500);
    }
}
