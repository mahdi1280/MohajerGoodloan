package ir.mohajer.dto.response;

import ir.mohajer.model.Loan;

import java.io.Serializable;

public class UserLoanResponse implements Serializable {

    private final long id;
    private final Loan loan;
    private final boolean winner;
    private final int amount;

    public UserLoanResponse(long id, Loan loan, boolean winner, int amount) {
        this.id = id;
        this.loan = loan;
        this.winner = winner;
        this.amount = amount;
    }

    public static Builder builder(){
        return new Builder();
    }

    public long getId() {
        return id;
    }

    public Loan getLoan() {
        return loan;
    }

    public boolean isWinner() {
        return winner;
    }

    public int getAmount() {
        return amount;
    }

    public static class Builder{

        private long id;
        private Loan loan;
        private boolean winner;
        private int amount;

        private Builder(){}

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setLoan(Loan loan) {
            this.loan = loan;
            return this;
        }

        public Builder setWinner(boolean winner) {
            this.winner = winner;
            return this;
        }

        public Builder setAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public UserLoanResponse build(){
            return new UserLoanResponse(id,loan,winner,amount);
        }
    }
}