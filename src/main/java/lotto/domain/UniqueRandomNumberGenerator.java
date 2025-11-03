package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class UniqueRandomNumberGenerator implements LottoNumberGenerator {

    @Override
    public List<Integer> getNumbers() {
        return Randoms.pickUniqueNumbersInRange(
                LottoNumber.minRange(),
                LottoNumber.maxRange(),
                LottoNumber.count()
        );
    }
}
