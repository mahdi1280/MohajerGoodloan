package ir.mohajer.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(schema = Schema.SCHEMA_NAME)
public class Installments extends BaseEntity {

    private int amount;
    private LocalDateTime creationDate;
    private UserLoan userLoan;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @CreatedDate

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)

    public UserLoan getUserLoan1() {
        return userLoan;
    }

    public void setUserLoan1(UserLoan userLoan) {
        this.userLoan = userLoan;
    }
}