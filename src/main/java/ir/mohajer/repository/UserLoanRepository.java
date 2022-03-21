package ir.mohajer.repository;

import ir.mohajer.model.Loan;
import ir.mohajer.model.UserLoan;
import ir.mohajer.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserLoanRepository extends JpaRepository<UserLoan,Long> {

    List<UserLoan> findByUsers(Users users);

    @Query("select count(ul) from UserLoan ul where ul.loan.id = :loanId")
    int getCountJoinLoan(long loanId);

    List<UserLoan> findByLoan(Loan loan);
}
