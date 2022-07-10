package ir.mohajer.dto.request;

import java.io.Serializable;

public class ExtraRequest implements Serializable {

    private final Integer price;
    private final String details;

    public ExtraRequest(Integer price, String details) {
        this.price = price;
        this.details = details;
    }

    public static Builder builder(){
        return new Builder();
    }

    public Integer getPrice() {
        return price;
    }

    public String getDetails() {
        return details;
    }

    public static class Builder{

        private Integer price;
        private String details;

        private Builder(){}

        public Builder price(Integer price){
            this.price=price;
            return this;
        }

        public Builder details(String details){
            this.details=details;
            return this;
        }

        public ExtraRequest build(){
            return new ExtraRequest(price,details);
        }
    }
}
