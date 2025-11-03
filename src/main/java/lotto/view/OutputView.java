package lotto.view;

import lotto.domain.Rank;
import lotto.domain.Lotto;
import lotto.domain.Lottos;

import java.util.Map;

public class OutputView {

    public void printIssuedLotto(Lottos lottos) {
        System.out.println();
        System.out.println(lottos.getLottoCount() + "개를 구매했습니다.");
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.values());
        }
    }

    public void printMatchResult(Map<Rank, Integer> matchReult) {
        printHeader();
        for (Rank rank : Rank.values()) {
            if (rank == Rank.NONE) {
                continue;
            }
            printRankLine(rank, matchReult.get(rank));
        }
    }

    public void printTotalPrizeAmount(double totalPrizeAmount) {
        System.out.println("총 수익률은 " + totalPrizeAmount + "%입니다.");
    }

    public void printError(String message) {
        System.out.println(message);
    }

    private void printHeader() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
    }

    private void printRankLine(Rank rank, int count) {
        String bonusText = "";
        if (rank.isBonusNumber()) {
            bonusText = ", 보너스 볼 일치";
        }
        System.out.printf("%d개 일치%s (%,d원) - %d개%n",
                rank.getWinningNumberCount(),
                bonusText,
                rank.getPrizeMoney(),
                count);
    }
}
