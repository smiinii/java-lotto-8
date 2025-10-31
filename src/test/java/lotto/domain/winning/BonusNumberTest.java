package lotto.domain.winning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BonusNumberTest {

    @Test
    @DisplayName("로또 번호 범위를 넘어가면 예외처리")
    void validateRangeTest() {
        // given
        int numbers = 46;
        // when & then
        assertThatThrownBy(() -> BonusNumber.of(numbers, List.of(1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 번호의 범위는");
    }

    @Test
    @DisplayName("당첨 번호와 중복되면 예외처리")
    void validateDuplicationTest() {
        // given
        WinningNumbers winningNumbers = WinningNumbers.from(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 1;
        // when & then
        assertThatThrownBy(() -> BonusNumber.of(bonusNumber, winningNumbers.getWinningNumbers()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("당첨 번호와 중복됩니다.");
    }

    @Test
    @DisplayName("성공")
    void success() {
        // given
        WinningNumbers winningNumbers = WinningNumbers.from(List.of(1, 2, 3, 4, 5, 6));
        // when
        BonusNumber bonusNumber = BonusNumber.of(7, winningNumbers.getWinningNumbers());
        // then
        assertThat(bonusNumber.getBonusNumber()).isEqualTo(7);
    }
}
