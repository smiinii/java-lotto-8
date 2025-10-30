package lotto.domain.winning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningNumbersTest {

    @Test
    @DisplayName("개수가 6개가 아니면 예외처리")
    void validateCountTest() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7);
        // when & then
        assertThatThrownBy(() -> WinningNumbers.from(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("당첨 번호 개수는");
    }

    @Test
    @DisplayName("로또 번호 범위를 넘어가면 예외처리")
    void validateRangeTest() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 46);
        // when & then
        assertThatThrownBy(() -> WinningNumbers.from(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("당첨 금액 번호의 범위는");
    }
}
