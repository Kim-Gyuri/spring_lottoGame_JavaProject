package LottoGame.javalottogame.view;

import LottoGame.javalottogame.domain.Buyer;
import LottoGame.javalottogame.domain.lotto.Lottery;
import LottoGame.javalottogame.domain.rating.Rank;
import LottoGame.javalottogame.domain.rating.Statistic;

import java.util.Map;
import java.util.SortedMap;
import java.util.stream.Collectors;

public class OutputView {
    private static final String ENTER = System.lineSeparator();
    private static final String BUY_INFO_MESSAGE = ENTER + "수동으로 %d장, 자동으로 %d장을 구매했습니다." + ENTER;
    private static final String WINNING_DETAIL_HEADER = ENTER + "당첨 통계" + ENTER + "---------";
    private static final String PRINT_FORMAT = "%d개 일치 (%d원) - %d개" + ENTER;
   // private static final String WINNING_RESULT_PRINT_FORMAT = "%d개 일치, 보너스 볼 일치 (%d원) - %d개" + ENTER;
    private static final String TOTAL_EARNING_RATE_MESSAGE = "총 수익률은 %.2f입니다.";
    private static final String NUMBER_DELIMITER = ",";
    private static final String LOTTO_RESULT_OPEN = "[";
    private static final String LOTTO_RESULT_CLOSE = "]";

    public static void getMessage(final String message) {
        System.out.println(message);
    }

    public static void BuyInfoView(final int manualCount, final int autoCount) {
        System.out.printf(BUY_INFO_MESSAGE, manualCount, autoCount);
    }

    public static void InfoMessage(final Buyer buyer) {
        final StringBuilder log = new StringBuilder();
        for (final Lottery lotto : buyer.getMyLotto()) {
            MessageBody(log, lotto);
        }
        log.append(ENTER);
        System.out.print(log.toString());
    }

    private static void MessageBody(final StringBuilder log, final Lottery lotto) {
        log.append(LOTTO_RESULT_OPEN);
        String body = lotto.getNumbers().stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(NUMBER_DELIMITER));

        log.append(body);
        log.append(LOTTO_RESULT_CLOSE).append(ENTER);
    }

    public static void lottoResultView(Statistic statistic) {
        System.out.println(WINNING_DETAIL_HEADER);
        lottoStatisticView(statistic);
        lottoPercentView(statistic);

    }

    public static void lottoStatisticView(Statistic statistic) {
        for (Map.Entry<Rank, Integer> elem : statistic.getStatistic().entrySet()) {
            System.out.printf(PRINT_FORMAT, elem.getKey().getCountOfMatch(), elem.getKey().getWinningMoney(), elem.getValue());
        }
    }

    public static void lottoPercentView(Statistic statistic) {
        int totalCount = 0;
        for (SortedMap.Entry<Rank, Integer> elem : statistic.getStatistic().entrySet())
            totalCount += elem.getValue();
        System.out.printf(TOTAL_EARNING_RATE_MESSAGE,(float) (totalCount - statistic.getStatistic().get(Rank.MISS)) / totalCount);
    }

}
