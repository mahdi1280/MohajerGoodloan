package ir.mohajer.dto.response;


import java.io.Serializable;

public class LotteryFindResponse implements Serializable {

    private final long id;
    private final String nationalCode;
    private final String username;
    private final boolean winner;
    private final Integer amount;

    public LotteryFindResponse(long id, String nationalCode, String username, boolean winner, Integer amount) {
        this.id = id;
        this.nationalCode = nationalCode;
        this.username = username;
        this.winner = winner;
        this.amount = amount;
    }

    public static Builder builder(){
        return new Builder();
    }

    public long getId() {
        return id;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public String getUsername() {
        return username;
    }

    public boolean isWinner() {
        return winner;
    }

    public Integer getAmount() {
        return amount;
    }

    public static class Builder{

        private long id;
        private String nationalCode;
        private String username;
        private boolean winner;
        private Integer amount;

        private Builder(){}

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setNationalCode(String nationalCode) {
            this.nationalCode = nationalCode;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setWinner(boolean winner) {
            this.winner = winner;
            return this;
        }

        public Builder setAmount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public LotteryFindResponse build(){
            return new LotteryFindResponse(id,nationalCode,username,winner,amount);
        }
    }
}
