package ir.mohajer.service.loan;

import ir.mohajer.model.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanService {

    List<Loan> findAll();

    Optional<Loan> findById(long loanId);

    Optional<Loan> fidByName(String name);

    void save(Loan loan);
}
