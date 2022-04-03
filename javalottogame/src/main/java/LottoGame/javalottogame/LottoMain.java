package LottoGame.javalottogame;

import LottoGame.javalottogame.domain.controller.LottoController;

public class LottoMain {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController();
        lottoController.start();
    }
}
