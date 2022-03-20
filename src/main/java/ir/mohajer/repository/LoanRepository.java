package ir.mohajer.repository;

import ir.mohajer.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan,Loan> {
}
