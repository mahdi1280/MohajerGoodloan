package ir.mohajer.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = Schema.SCHEMA_NAME)
public class UserLoan extends BaseEntity{

    private LocalDateTime creationDate;
    private Users users;
    private Loan loan;
    private boolean winner=false;

    @CreatedDate
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    @Column(columnDefinition = "boolean default false")
    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }
}
