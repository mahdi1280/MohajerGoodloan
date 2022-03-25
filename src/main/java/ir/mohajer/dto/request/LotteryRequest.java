package ir.mohajer.dto.request;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class LotteryRequest implements Serializable {

    private final String name;

    public LotteryRequest(String name) {
        this.name = name;
    }

    @NotNull
    public String getName() {
        return name;
    }
}
