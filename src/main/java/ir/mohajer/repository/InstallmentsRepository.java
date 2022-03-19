package ir.mohajer.repository;

import ir.mohajer.model.Installments;
import ir.mohajer.model.UserLoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstallmentsRepository extends JpaRepository<Installments,Long> {

    List<Installments> findByUserLoan(UserLoan userLoan);
}
