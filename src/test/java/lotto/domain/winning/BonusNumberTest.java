package lotto.domain.winning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BonusNumberTest {

    @Test
    @DisplayName("로또 번호 범위를 넘어가면 예외처리")
    void validateRangeTest() {
        // given
        int numbers = 46;
        // when & then
        assertThatThrownBy(() -> BonusNumber.of(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 번호의 범위는");
    }
}
