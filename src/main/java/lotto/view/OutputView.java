package lotto.view;

import lotto.domain.ticket.Lotto;
import lotto.domain.ticket.Lottos;

public class OutputView {

    public void printIssuedLotto(Lottos lottos) {
        System.out.println(lottos.getLottoCount() + "개를 구매했습니다.");
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println("[" + lotto.getNumbers() + "]");
        }
    }
}
