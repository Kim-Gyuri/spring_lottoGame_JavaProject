package LottoGame.javalottogame.domain;

import LottoGame.javalottogame.domain.lotto.Lottery;
import LottoGame.javalottogame.domain.lotto.LottoNumber;

import java.util.List;

public class WinningLottery {

    public static final String DUPLICATE_BONUS_BALL_ERROR_MESSAGE = "중복된 보너스 볼 입력입니다.";
    private final Lottery lotto;
    private final LottoNumber bonusBall;

    public WinningLottery(final Lottery lotto, final LottoNumber bonusBall) {
        validateHasBonusBallInLotto(lotto, bonusBall);
        this.lotto = lotto;
        this.bonusBall = bonusBall;
    }

    public List<Integer> getLottoNum() {
        return lotto.getNumbers();
    }

    public Integer getBonusNum() {
        return bonusBall.getNumber();
    }


    private void validateHasBonusBallInLotto(final Lottery lotto, final LottoNumber bonusBall) {
        if (lotto.containNumber(bonusBall)) {
            throw new IllegalArgumentException(DUPLICATE_BONUS_BALL_ERROR_MESSAGE);
        }
    }

}
