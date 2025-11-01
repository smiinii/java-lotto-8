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
    @DisplayName("여러 장의 로또를 매칭하여 등수별 개수를 집계한다")
    void allMatchResultTest() {
        // given
        WinningNumbers winningNumbers = WinningNumbers.from(List.of(1,2,3,4,5,6));
        BonusNumber bonusNumber = BonusNumber.of(7, winningNumbers.getWinningNumbers());

        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(1,2,3,4,5,6)),
                new Lotto(List.of(1,2,3,4,5,7)),
                new Lotto(List.of(1,2,3,4,5,8)),
                new Lotto(List.of(1,2,3,4,9,10)),
                new Lotto(List.of(1,2,3,11,12,13)),
                new Lotto(List.of(8,9,10,11,12,13))
        ));

        ResultCalculator resultCalculator = new ResultCalculator();

        // when
        Map<Rank, Integer> result = resultCalculator.matchResult(lottos, winningNumbers, bonusNumber);

        // then
        assertThat(result.get(Rank.FIRST)).isEqualTo(1);
        assertThat(result.get(Rank.SECOND)).isEqualTo(1);
        assertThat(result.get(Rank.THIRD)).isEqualTo(1);
        assertThat(result.get(Rank.FOURTH)).isEqualTo(1);
        assertThat(result.get(Rank.FIFTH)).isEqualTo(1);
        assertThat(result.get(Rank.NONE)).isEqualTo(1);
    }

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
