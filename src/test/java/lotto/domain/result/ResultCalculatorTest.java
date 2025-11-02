package lotto.domain.result;

import lotto.domain.purchase.Money;
import lotto.domain.ticket.Lotto;
import lotto.domain.ticket.Lottos;
import lotto.domain.winning.BonusNumber;
import lotto.domain.winning.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultCalculatorTest {

    @Test
    @DisplayName("결과를 확인해 수익률 계산한다.")
    void calculateYieldTest() {
        // given
        ResultCalculator calculator = new ResultCalculator();
        EnumMap<Rank, Integer> matchResult = new EnumMap<>(Rank.class);
        for (Rank r : Rank.values()) {
            matchResult.put(r, 0);
        }
        matchResult.put(Rank.FIFTH, 1);
        Money money = Money.of(3000);
        // when
        double yield = calculator.calculateYield(matchResult, money);
        // then
        assertThat(yield).isEqualTo(166.7);
    }

    @Test
    @DisplayName("당첨이 없을 경우 수익률은 0%다")
    void calculateYield_zeroPrize() {
        // given
        ResultCalculator calculator = new ResultCalculator();
        EnumMap<Rank, Integer> matchResult = new EnumMap<>(Rank.class);
        for (Rank r : Rank.values()) {
            matchResult.put(r, 0);
        }
        matchResult.put(Rank.NONE, 1);
        Money money = Money.of(1000);
        // when
        double yield = calculator.calculateYield(matchResult, money);
        // then
        assertThat(yield).isEqualTo(0.0);
    }
}
