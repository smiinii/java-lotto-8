package lotto.domain.ticket;

import lotto.domain.rule.LottoRule;

import java.util.LinkedHashSet;
import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateLottoCount(numbers);
        validateLottoRange(numbers);
        validateLottoDuplication(numbers);
        this.numbers = numbers.stream().sorted().toList();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validateLottoCount(List<Integer> numbers) {
        if (numbers.size() != LottoRule.COUNT) {
            throw new IllegalArgumentException("[ERROR] 로또 번호 개수는 " + LottoRule.COUNT + "개 입니다.");
        }
    }

    private void validateLottoRange(List<Integer> numbers) {
        for (int number : numbers) {
            if ((number < LottoRule.MIN) || (number > LottoRule.MAX)) {
                throw new IllegalArgumentException("[ERROR] 로또 번호의 범위는 " + LottoRule.MIN
                        + " ~ " + LottoRule.MAX + " 입니다.");
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
