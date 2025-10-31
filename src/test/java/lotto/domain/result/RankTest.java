package lotto.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {

    @ParameterizedTest
    @CsvSource({
            "6,false,FIRST",
            "5,true,SECOND",
            "5,false,THIRD",
            "4,false,FOURTH",
            "3,false,FIFTH",
            "2,false,NONE",
            "0,false,NONE"
    })
    @DisplayName("일치 개수와 보너스 여부에 따라 Rank를 올바르게 반환한다")
    void rankMatchTest(int matchCount, boolean bonus, Rank expected) {
        // when
        Rank result = Rank.from(matchCount, bonus);
        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "FIRST,2000000000",
            "SECOND,30000000",
            "THIRD,1500000",
            "FOURTH,50000",
            "FIFTH,5000",
            "NONE,0"
    })
    @DisplayName("각 Rank에 상금 금액이 올바르게 설정되어 있다")
    void rankPrizeMoneyTest(Rank rank, long expectedPrize) {
        // when & then
        assertThat(rank.getPrizeMoney()).isEqualTo(expectedPrize);
    }
}

