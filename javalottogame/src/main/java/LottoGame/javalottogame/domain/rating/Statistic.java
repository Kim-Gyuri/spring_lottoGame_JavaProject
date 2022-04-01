package LottoGame.javalottogame.domain.rating;

import java.util.SortedMap;
import java.util.TreeMap;

public class Statistic {

    private SortedMap<Rank, Integer> statistic;

    public Statistic() {
        statistic = new TreeMap<>();
        statistic.put(Rank.MISS, 0);
        statistic.put(Rank.WIN_FIRST_PLACE, 0);
        statistic.put(Rank.WIN_SECOND_PLACE, 0);
        statistic.put(Rank.WIN_THIRD_PLACE, 0);
        statistic.put(Rank.WIN_FOURTH_PLACE, 0);
        statistic.put(Rank.WIN_FIFTH_PLACE, 0);
    }

    public void pushStatistic(Rank rank) {
        statistic.put(rank, statistic.get(rank)+1);
    }

    public SortedMap<Rank, Integer> getStatistic() {
        return statistic;
    }
}
