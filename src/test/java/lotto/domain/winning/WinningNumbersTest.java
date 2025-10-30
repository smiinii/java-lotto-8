package lotto.domain.winning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningNumbersTest {

    @Test
    @DisplayName("개수가 6개가 아니면 예외처리")
    void validateCount() {
        // given & when & then
        assertThatThrownBy(() -> WinningNumbers.from(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("당첨 번호 개수는");
    }
}
