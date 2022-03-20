package ir.mohajer.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class LoanRequest implements Serializable {

    private final String name;
    private final Integer amount;

    @JsonCreator
    public LoanRequest(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }

    @NotNull
    @NotBlank
    public String getName() {
        return name;
    }

    @Min(value = 0)
    @NotNull
    public Integer getAmount() {
        return amount;
    }
}
