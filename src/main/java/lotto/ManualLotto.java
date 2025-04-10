package lotto;

import java.util.List;
import java.util.stream.Collectors;

public class ManualLotto extends LottoGroup{
    public ManualLotto(List<String> lottos, int count) {
        this.lottos = lottos.stream()
                            .map(lotto -> new Lotto(lotto))
                            .collect(Collectors.toList());
        this.count = count;
    }
}
