package ir.mohajer.repository;

import ir.mohajer.model.UserLoan;
import ir.mohajer.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLoanRepository extends JpaRepository<UserLoan,Long> {

    List<UserLoan> findByUsers(Users users);
}
