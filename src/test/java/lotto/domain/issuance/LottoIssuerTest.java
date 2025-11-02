package lotto.domain.issuance;

import lotto.domain.LottoIssuer;
import lotto.domain.generator.FixedLottoNumberGenerator;
import lotto.domain.generator.LottoNumberGenerator;
import lotto.domain.Money;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoIssuerTest {

    @Test
    @DisplayName("로또 생성기 테스트")
    void lottoIssuerTest() {
        // given
        LottoNumberGenerator lottoNumberGenerator = new FixedLottoNumberGenerator(List.of(1,2,3,4,5,6));
        LottoIssuer lottoIssuer = new LottoIssuer(lottoNumberGenerator);
        Money money = Money.of(3000);
        // when & then
        Lottos lottos = lottoIssuer.issue(money);
        for (Lotto lotto : lottos.getLottos()) {
            assertThat(lotto.getNumbers()).containsExactly(1,2,3,4,5,6);
        }
    }
}
