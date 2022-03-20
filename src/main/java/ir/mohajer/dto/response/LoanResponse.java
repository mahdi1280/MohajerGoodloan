package ir.mohajer.dto.response;

import java.io.Serializable;

public class LoanResponse implements Serializable {

    private final long id;
    private final String name;
    private final Integer joinCount;
    private final Integer amount;
    private final Integer allAmount;

    public LoanResponse(long id,String name, Integer joinCount, Integer amount, Integer allAmount) {
        this.id=id;
        this.name = name;
        this.joinCount = joinCount;
        this.amount = amount;
        this.allAmount = allAmount;
    }

    public static Builder builder(){
        return new Builder();
    }

    public long getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getJoinCount() {
        return joinCount;
    }

    public Integer getAmount() {
        return amount;
    }

    public Integer getAllAmount() {
        return allAmount;
    }

    public static class Builder{

        private long id;
        private String name;
        private Integer joinCount;
        private Integer amount;
        private Integer allAmount;

        private Builder(){}

        public Builder setId(long id){
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setJoinCount(Integer joinCount) {
            this.joinCount = joinCount;
            return this;
        }

        public Builder setAmount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public Builder setAllAmount(Integer allAmount) {
            this.allAmount = allAmount;
            return this;
        }

        public LoanResponse build(){
            return new LoanResponse(id,name,joinCount,amount,allAmount);
        }
    }
}