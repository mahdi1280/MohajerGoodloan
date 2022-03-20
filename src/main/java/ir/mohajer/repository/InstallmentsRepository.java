package ir.mohajer.repository;

import ir.mohajer.model.Installments;
import ir.mohajer.model.UserLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstallmentsRepository extends JpaRepository<Installments,Long> {

    List<Installments> findByUserLoan(UserLoan userLoan);

    @Query("select sum(i.amount) from Installments i where i.userLoan.id=:id")
    Integer getAmountByUserLoan(long id);

    @Query("select sum(i.amount) from Installments i where i.userLoan.loan.id=:loanId")
    Integer getAllAmountByLoan(long loanId);
}
