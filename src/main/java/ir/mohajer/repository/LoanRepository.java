package ir.mohajer.repository;

import ir.mohajer.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan,Long> {

    Optional<Loan> findByName(String name);
}
