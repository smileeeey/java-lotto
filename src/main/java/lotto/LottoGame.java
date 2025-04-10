package lotto;

public class LottoGame {
    private LottoGenerator lottoGenerator = new LottoGenerator();
    private InputView inputView = new InputView();
    private OutputView outputView = new OutputView();

    void play() {
        int budget = inputView.getLottoBudget();
        LottoResult lottoResult = new LottoResult(budget);

        int manualLottoCount = inputView.getManualLottoNumber();
        ManualLotto manualLottos = new ManualLotto(inputView.getManualLotto(manualLottoCount), manualLottoCount);

        int autoLottoCount = lottoResult.getPurchasedLottoCount() - manualLottoCount;
        outputView.printLottoCount(autoLottoCount, manualLottoCount);

        AutoLotto autoLottos = new AutoLotto(lottoResult.getPurchasedLottoCount());
        System.out.println(autoLottos);

        WinningLotto winningLotto = new WinningLotto(inputView.getLottoWinningNumber(), inputView.getBouns());
        winningLotto.validate();

        processLottoResult(lottoResult, autoLottos, winningLotto);
        processLottoResult(lottoResult, manualLottos, winningLotto);

        outputView.printLottoResult(lottoResult);
    }

    private void processLottoResult(LottoResult lottoResult, LottoGroup lottoGroup, WinningLotto winningNumber) {
        lottoGroup.validate(lottoResult.getPurchasedLottoCount());
        lottoGroup.getLottos()
                  .forEach( lotto -> lottoResult.addMatchCount(winningNumber.getLottoPrize(lotto)));
    }

    public static void main(String[] args) {
        LottoGame lottoGame = new LottoGame();
        lottoGame.play();
    }
}
