package LottoGame.javalottogame.domain.controller;

import LottoGame.javalottogame.domain.Buyer;
import LottoGame.javalottogame.domain.LotteryException;
import LottoGame.javalottogame.domain.Money;
import LottoGame.javalottogame.domain.WinningLottery;
import LottoGame.javalottogame.domain.lotto.Lottery;
import LottoGame.javalottogame.domain.lotto.LottoNumber;
import LottoGame.javalottogame.domain.lottoMachine.AutoLottoMachine;
import LottoGame.javalottogame.view.InputView;
import LottoGame.javalottogame.view.OutputView;

public class LottoController {

    public void start() {
        final Buyer buyer = new Buyer();
        Money money = getMoney();
        LotteryException ticket = new LotteryException(money, manualBuyTicket());

        generateLotto(buyer, ticket);
        OutputView.BuyInfoView(ticket.getCountOfManualPurchase(), ticket.getCountOfAutoPurchase());
        OutputView.InfoMessage(buyer);


        WinningLottery winningLotto = buyWinningLotto();
        OutputView.lottoResultView(buyer.playTheLottery(winningLotto.getLottoNum(), winningLotto.getBonusNum()));
    }

    private Money getMoney() {
        try {
            return tryGetMoney();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return getMoney();
        }
    }

    private Money tryGetMoney() {
        int money = InputView.inputTotalPrice();
        return new Money(money);
    }

    private int manualBuyTicket() {
        try {
            return tryManualBuyTicket();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return manualBuyTicket();
        }
    }

    private int tryManualBuyTicket() {
        return InputView.InputManualLottery();
    }

    private void generateLotto(final Buyer buyer, final LotteryException ticket) {
        generateManualLottoNumbers(ticket.getCountOfManualPurchase(), buyer);
        buyer.buyAutoLotto(ticket.getCountOfAutoPurchase(), new AutoLottoMachine());
    }

    private void generateManualLottoNumbers(final int count, final Buyer buyer) {
        OutputView.getMessage(InputView.INPUT_MANUAL_BUY_NUMBERS_MESSAGE);
        for (int i = 0; i < count; i++) {
            Lottery lotto = manualBuyLotto();
            buyer.buyManualLotto(lotto);
        }
    }

    private Lottery manualBuyLotto() {
        try {
            return tryManualBuyLotto();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return manualBuyLotto();
        }
    }

    private Lottery tryManualBuyLotto() {
        return Lottery.from(InputView.inputManualLotteryNumber());
    }

    private WinningLottery buyWinningLotto() {
        try {
            return tryBuyWinningLotto();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return buyWinningLotto();
        }
    }

    private WinningLottery tryBuyWinningLotto() {

        Lottery lotto = Lottery.from(InputView.InputWinningNumber());
        LottoNumber bonusNumber = LottoNumber.from(InputView.InputBonusBall());
        return new WinningLottery(lotto, bonusNumber);
    }

}