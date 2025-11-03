package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoIssuer {

    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoIssuer(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public Lottos issue(Money money) {
        int count = money.purchasableCount();
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Lotto lotto = new Lotto(lottoNumberGenerator.getNumbers());
            lottos.add(lotto);
        }
        return new Lottos(lottos);
    }
}
