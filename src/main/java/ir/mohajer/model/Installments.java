package ir.mohajer.model;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(schema = Schema.SCHEMA_NAME)
public class Installments extends BaseEntity {

    private int amount;
    private LocalDate creationDate;
    private UserLoan userLoan;

    public Installments(int amount, UserLoan userLoan) {
        this.amount = amount;
        this.userLoan = userLoan;
    }

    public Installments() {
    }

    public static Builder builder(){
        return new Builder();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @CreationTimestamp
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public UserLoan getUserLoan() {
        return userLoan;
    }

    public void setUserLoan(UserLoan userLoan) {
        this.userLoan = userLoan;
    }

    public static class Builder {

        private int amount;
        private UserLoan userLoan;

        private Builder(){}

        public Builder setAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder setUserLoan(UserLoan userLoan) {
            this.userLoan = userLoan;
            return this;
        }

        public Installments build(){
            return new Installments(amount,userLoan);
        }
    }
}
