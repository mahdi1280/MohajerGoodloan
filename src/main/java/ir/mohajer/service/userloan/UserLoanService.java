package ir.mohajer.service.userloan;

import ir.mohajer.model.Loan;
import ir.mohajer.model.UserLoan;
import ir.mohajer.model.Users;

import java.util.List;
import java.util.Optional;

public interface UserLoanService {

    List<UserLoan> findByUser(Users user);

    Optional<UserLoan> findById(long id);

    int getCountJoinLoan(Loan loan);

    List<UserLoan> findByLoan(Loan loan);

    void save(UserLoan userLoan);

    Optional<UserLoan> findByUserAndLoan(Users users, Loan loan);
}
