package lotto.domain.generator;

import lotto.domain.rule.LottoRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoNumberGeneratorTest {

    @Test
    @DisplayName("난수 생성기가 로또 번호 범위 내의 숫자만 생성해야 한다")
    void lottoNumberGeneratorRangeTest() {
        // given
        LottoNumberGenerator lottoNumberGenerator = new UniqueRandomNumberGenerator();
        // when & then
        for (int i = 0; i < 1000; i++) {
            List<Integer> randomLottoNumbers = lottoNumberGenerator.getNumbers();
            assertThat(randomLottoNumbers)
                    .allSatisfy(number ->
                            assertThat(number).isBetween(LottoRule.MIN, LottoRule.MAX));
        }
    }
}
