package ir.mohajer.repository;

import ir.mohajer.model.ExtraExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExtraExpensesRepository extends JpaRepository<ExtraExpenses,Long> {

    @Query("select sum(e.price) from ExtraExpenses e ")
    Integer getAllPrice();

}
