package lotto.domain;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class LottoNumber {
    private static final int MIN = 1;
    private static final int MAX = 45;
    private static final int COUNT = 6;

    private final List<Integer> numbers;

    private LottoNumber(List<Integer> numbers) {
        validateLottoCount(numbers);
        validateLottoRange(numbers);
        validateLottoDuplication(numbers);
        this.numbers = numbers.stream().sorted().toList();
    }

    public static LottoNumber from(List<Integer> numbers) {
        return new LottoNumber(numbers);
    }

    static int minRange() {
        return MIN;
    }

    static int maxRange() {
        return MAX;
    }

    static int count() {
        return COUNT;
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers);
    }

    public boolean contains(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public boolean isInRange(int bonusNumber) {
        return bonusNumber >= MIN && bonusNumber <= MAX;
    }

    public int matchCountWith(LottoNumber issuedLotto) {
        List<Integer> copy = new ArrayList<>(issuedLotto.numbers);
        copy.retainAll(this.numbers);
        return copy.size();
    }

    private void validateLottoCount(List<Integer> numbers) {
        if (numbers.size() != COUNT) {
            throw new IllegalArgumentException("[ERROR] 로또 번호 개수는 " + COUNT + "개 입니다.");
        }
    }

    private void validateLottoRange(List<Integer> numbers) {
        for (int number : numbers) {
            if ((number < MIN) || (number > MAX)) {
                throw new IllegalArgumentException("[ERROR] 로또 번호의 범위는 " + MIN
                        + " ~ " + MAX + " 입니다.");
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
