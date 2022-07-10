package ir.mohajer.dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ExtraExpensesResponse implements Serializable {

    private final int price;
    private final String details;
    private final LocalDateTime createdAt;
    private final int allPrice;

    public ExtraExpensesResponse(int price, String details, LocalDateTime createdAt,int allPrice) {
        this.price = price;
        this.details = details;
        this.createdAt = createdAt;
        this.allPrice=allPrice;
    }

    public static Builder builder(){
        return new Builder();
    }

    public int getPrice() {
        return price;
    }

    public String getDetails() {
        return details;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getAllPrice() {
        return allPrice;
    }

    public static class Builder{

        private int price;
        private String details;
        private LocalDateTime createdAt;
        private int allPrice;

        private Builder(){}

        public Builder price(final int price){
            this.price=price;
            return this;
        }

        public Builder details(final String details){
            this.details=details;
            return this;
        }

        public Builder createdAt(final LocalDateTime createdAt){
            this.createdAt=createdAt;
            return this;
        }

        public Builder allPrice(final int allPrice){
            this.allPrice=allPrice;
            return this;
        }

        public ExtraExpensesResponse build(){
            return new ExtraExpensesResponse(price,details,createdAt,allPrice);
        }
    }
}
