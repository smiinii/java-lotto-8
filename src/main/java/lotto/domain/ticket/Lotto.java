package lotto.domain.ticket;

import java.util.LinkedHashSet;
import java.util.List;

public class Lotto {

    public static final int LOTTO_NUMBER_MIN = 1;
    public static final int LOTTO_NUMBER_MAX = 45;
    public static final int LOTTO_NUMBER_COUNT = 6;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateLottoCount(numbers);
        validateLottoRange(numbers);
        validateLottoDuplication(numbers);
        this.numbers = numbers;
    }

    private void validateLottoCount(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void validateLottoRange(List<Integer> numbers) {
        for (int number : numbers) {
            if ((number < LOTTO_NUMBER_MIN) || (number > LOTTO_NUMBER_MAX)) {
                throw new IllegalArgumentException("[ERROR] 로또 번호의 범위는 " + LOTTO_NUMBER_MIN
                        + " ~ " + LOTTO_NUMBER_MAX + " 입니다.");
            }
        }
    }

    private void validateLottoDuplication(List<Integer> numbers) {
        LinkedHashSet<Integer> duplicatedNumbers = new LinkedHashSet<>(numbers);
        if (numbers.size() != duplicatedNumbers.size()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호가 중복됩니다.");
        }
    }
}
