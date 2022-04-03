package LottoGame.javalottogame.domain.controller;

import LottoGame.javalottogame.domain.Buyer;
import LottoGame.javalottogame.domain.BuyerStorage;
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
        BuyerStorage ticket = new BuyerStorage(money, InputManualBuyQuantity());

        generateLotto(buyer, ticket);
        OutputView.BuyInfoView(ticket.getManualBuyQuantity(), ticket.getAutoBuyQuantity());
        OutputView.InfoMessage(buyer);


        WinningLottery winningLotto = InputManualWinningLotto();
        OutputView.lottoResultView(buyer.playTheLottery_V2(winningLotto));
    }

    private Money InputMoney() {
        try {
            return ImportInputMoney();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return InputMoney();
        }
    }

    private Money ImportInputMoney() {
        int money = InputView.getInputMoney();
        return new Money(money);
    }

    private int InputManualBuyQuantity() {
        try {
            return ImportManualBuyQuantity();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return InputManualBuyQuantity();
        }
    }

    private int ImportManualBuyQuantity() {
        return InputView.getManualBuyQuantity();
    }

    private void generateLotto(final Buyer buyer, final BuyerStorage ticket) {
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
        return Lottery.from(InputView.getInputManualLottoNumber());
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

        Lottery lotto = Lottery.from(InputView.getWinningNumber());
        LottoNumber bonusNumber = LottoNumber.from(InputView.getBonusBall());
        return new WinningLottery(lotto, bonusNumber);
    }

}