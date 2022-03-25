package ir.mohajer.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class DetailsRequest implements Serializable {

    private final Long loanId;
    private final Integer amount;

    public DetailsRequest(Long loanId, Integer amount) {
        this.loanId = loanId;
        this.amount = amount;
    }

    @NotNull
    public Long getLoanId() {
        return loanId;
    }

    @NotNull
    @Min(0)
    public Integer getAmount() {
        return amount;
    }
}
