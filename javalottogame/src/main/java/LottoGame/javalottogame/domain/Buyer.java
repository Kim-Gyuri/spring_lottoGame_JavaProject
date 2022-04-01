package LottoGame.javalottogame.domain;

import LottoGame.javalottogame.domain.lotto.Lottery;
import LottoGame.javalottogame.domain.lottoMachine.AutoLottoMachine;
import LottoGame.javalottogame.domain.rating.Rank;
import LottoGame.javalottogame.domain.rating.Statistic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Buyer {

    private List<Lottery> myLotto;
    private int countOfMatch;

    public Buyer() {
        myLotto = new ArrayList<>();
    }


    public void buyAutoLotto(final int ticketCount, final AutoLottoMachine lottoMachine) {
        for (int i=0; i<ticketCount; i++) {
            myLotto.add(Lottery.from(lottoMachine.generate()));
        }
    }

    public void buyManualLotto(final Lottery lottery) {
        myLotto.add(lottery);
    }

    public Statistic playTheLottery(List<Integer> lottoNumber, Integer bonusNumber) {
        Statistic statistic = new Statistic();

        for (Lottery lotto : myLotto) {
            getMatchedCount(lottoNumber, lotto);

            statistic.pushStatistic(Rank.valueOf(countOfMatch, lotto.isContain(bonusNumber)));
        }
        return statistic;
    }

    private void getMatchedCount(List<Integer> lottoNum, Lottery lotto) {
        countOfMatch = (int) lotto.getNumbers()
                .stream()
                .filter(purchasedOne -> lottoNum
                        .contains(purchasedOne))
                .count();
    }

    public List<Lottery> getMyLotto() {
        return Collections.unmodifiableList(myLotto);
    }

}
