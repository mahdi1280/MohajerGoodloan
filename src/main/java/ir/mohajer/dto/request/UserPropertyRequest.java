package ir.mohajer.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserPropertyRequest implements Serializable {

    private final Long userId;
    private final Integer price;

    public UserPropertyRequest(Long userId, Integer price) {
        this.userId = userId;
        this.price = price;
    }

    public static Builder builder() {
        return new Builder();
    }

    @NotNull
    @Min(value = 1)
    public Long getUserId() {
        return userId;
    }

    @NotNull
    @Min(value = 1)
    public Integer getPrice() {
        return price;
    }

    public static class Builder {

        private Long userId;
        private Integer price;

        private Builder() {
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder price(Integer price) {
            this.price = price;
            return this;
        }

        public UserPropertyRequest build() {
            return new UserPropertyRequest(userId, price);
        }
    }
}
