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
        Money money = InputMoney();
        LotteryException ticket = new LotteryException(money, InputManualBuyQuantity());

        generateLotto(buyer, ticket);
        OutputView.BuyInfoView(ticket.getManualBuyQuantity(), ticket.getAutoBuyQuantity());
        OutputView.InfoMessage(buyer);


        WinningLottery winningLotto = InputManualWinningLotto();
        OutputView.lottoResultView(buyer.playTheLottery(winningLotto.getLottoNum(), winningLotto.getBonusNum()));
    }

    private Money InputMoney() {
        try {
            return tryInputMoney();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return InputMoney();
        }
    }

    private Money tryInputMoney() {
        int money = InputView.inputTotalPrice();
        return new Money(money);
    }

    private int InputManualBuyQuantity() {
        try {
            return ManualBuyQuantity();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return InputManualBuyQuantity();
        }
    }

    private int ManualBuyQuantity() {
        return InputView.InputManualLottery();
    }

    private void generateLotto(final Buyer buyer, final LotteryException ticket) {
        generateManualLottoNumbers(ticket.getManualBuyQuantity(), buyer);
        buyer.buyAutoLotto(ticket.getAutoBuyQuantity(), new AutoLottoMachine());
    }

    private void generateManualLottoNumbers(final int count, final Buyer buyer) {
        OutputView.getMessage(InputView.INPUT_MANUAL_BUY_NUMBERS_MESSAGE);
        for (int i = 0; i < count; i++) {
            Lottery lotto = InputManualBuyLotto();
            buyer.buyManualLotto(lotto);
        }
    }

    private Lottery InputManualBuyLotto() {
        try {
            return ImportManualLottoNumber();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return InputManualBuyLotto();
        }
    }

    private Lottery ImportManualLottoNumber() {
        return Lottery.from(InputView.inputManualLotteryNumber());
    }

    private WinningLottery InputManualWinningLotto() {
        try {
            return ImportBoughtWinningLotto();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return InputManualWinningLotto();
        }
    }

    private WinningLottery ImportBoughtWinningLotto() {

        Lottery lotto = Lottery.from(InputView.InputWinningNumber());
        LottoNumber bonusNumber = LottoNumber.from(InputView.InputBonusBall());
        return new WinningLottery(lotto, bonusNumber);
    }

}