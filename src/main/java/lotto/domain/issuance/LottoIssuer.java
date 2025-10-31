package lotto.domain.issuance;

import lotto.domain.generator.LottoNumberGenerator;
import lotto.domain.purchase.LottoPrice;
import lotto.domain.purchase.Money;
import lotto.domain.ticket.Lotto;
import lotto.domain.ticket.Lottos;

import java.util.ArrayList;
import java.util.List;

public class LottoIssuer {

    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoIssuer(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public Lottos issue(Money money) {
        int count = money.getMoney() / LottoPrice.UNIT;
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Lotto lotto = new Lotto(lottoNumberGenerator.getNumbers());
            lottos.add(lotto);
        }
        return new Lottos(lottos);
    }
}
