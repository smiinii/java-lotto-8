package lotto.view;

import lotto.domain.Rank;

import java.util.List;
import java.util.Map;

public class OutputView {

    public void printIssuedLotto(List<List<Integer>> issuedLottos) {
        System.out.println();
        System.out.println(issuedLottos.size() + "개를 구매했습니다.");
        for (List<Integer> lottoNumbers : issuedLottos) {
            System.out.println(lottoNumbers);
        }
    }

    public void printMatchResult(Map<Rank, Integer> matchResult) {
        printHeader();
        for (Rank rank : Rank.values()) {
            if (rank == Rank.NONE) {
                continue;
            }
            printRankLine(rank, matchResult.getOrDefault(rank, 0));
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
