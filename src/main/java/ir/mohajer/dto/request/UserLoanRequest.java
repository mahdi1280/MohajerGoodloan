package ir.mohajer.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserLoanRequest implements Serializable {

    private final Integer userId;
    private final String name;
    private final Integer amount;

    @JsonCreator
    public UserLoanRequest(Integer userId,String name,Integer amount) {
        this.userId=userId;
        this.name = name;
        this.amount = amount;
    }

    @NotNull
    public Integer getUserId() {
        return userId;
    }

    @NotNull
    @NotBlank
    public String getName() {
        return name;
    }

    @NotNull
    public Integer getAmount() {
        return amount;
    }
}
