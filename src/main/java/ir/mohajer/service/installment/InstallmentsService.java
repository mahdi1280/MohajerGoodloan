package ir.mohajer.service.installment;

import ir.mohajer.model.Installments;
import ir.mohajer.model.Loan;
import ir.mohajer.model.UserLoan;

import java.util.List;

public interface InstallmentsService {

    List<Installments> findByLoanUserId(UserLoan userLoan);

    Integer getAmountByLoanUser(UserLoan userLoan);

    Integer getAllAmountByLoan(Loan loan);

    void save(Installments installments);
}
