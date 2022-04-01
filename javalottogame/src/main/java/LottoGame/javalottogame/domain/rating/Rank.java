package LottoGame.javalottogame.domain.rating;

import java.util.Comparator;

public enum Rank implements Comparator<Rank> {

    WIN_FIRST_PLACE(6, 2_000_000_000),
    WIN_SECOND_PLACE(5,  30_000_000),
    WIN_THIRD_PLACE(5, 1_500_000),
    WIN_FOURTH_PLACE(4, 50_000),
    WIN_FIFTH_PLACE(3, 5_000),
    MISS(0, 0);

    private int countOfMatch;
    private int winningMoney;

    private Rank(int countOfMatch, int winningMoney) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public static Rank valueOf(int countOfMatch, boolean matchBonus) {
        if (countOfMatch == Rank.WIN_FIRST_PLACE.countOfMatch)
            return WIN_FIRST_PLACE;
        if (countOfMatch == Rank.WIN_SECOND_PLACE.countOfMatch && matchBonus == true)
            return WIN_SECOND_PLACE;
        if (countOfMatch == Rank.WIN_SECOND_PLACE.countOfMatch || (countOfMatch == Rank.WIN_THIRD_PLACE.countOfMatch && matchBonus == true))
            return WIN_THIRD_PLACE;
        if (countOfMatch == Rank.WIN_THIRD_PLACE.countOfMatch || (countOfMatch == Rank.WIN_FOURTH_PLACE.countOfMatch && matchBonus == true))
            return WIN_FOURTH_PLACE;
        if (countOfMatch == Rank.WIN_FOURTH_PLACE.countOfMatch || (countOfMatch == WIN_FIFTH_PLACE.countOfMatch && matchBonus == true))
            return WIN_FOURTH_PLACE;
        return MISS;
    }


    @Override
    public int compare(Rank r1, Rank r2) {
        return r1.getWinningMoney() - r2.getWinningMoney();
    }
}
