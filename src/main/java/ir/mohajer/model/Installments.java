package ir.mohajer.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = Schema.SCHEMA_NAME)
public class Installments extends BaseEntity {

    private int amount;
    private LocalDate creationDate;
    private UserLoan userLoan;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @CreatedDate
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
}
