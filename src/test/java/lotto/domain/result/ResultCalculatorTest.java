package lotto.domain.result;

import lotto.domain.ticket.Lotto;
import lotto.domain.ticket.Lottos;
import lotto.domain.winning.BonusNumber;
import lotto.domain.winning.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Map<Rank, Integer> result = resultCalculator.matchReult(lottos, winningNumbers, bonusNumber);

        // then
        assertThat(result.get(Rank.FIRST)).isEqualTo(1);
        assertThat(result.get(Rank.SECOND)).isEqualTo(1);
        assertThat(result.get(Rank.THIRD)).isEqualTo(1);
        assertThat(result.get(Rank.FOURTH)).isEqualTo(1);
        assertThat(result.get(Rank.FIFTH)).isEqualTo(1);
        assertThat(result.get(Rank.NONE)).isEqualTo(1);
    }
}
