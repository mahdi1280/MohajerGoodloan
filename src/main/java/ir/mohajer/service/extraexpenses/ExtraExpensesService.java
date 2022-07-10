package ir.mohajer.service.extraexpenses;

import ir.mohajer.model.ExtraExpenses;

import java.util.List;

public interface ExtraExpensesService {

    void save(ExtraExpenses extraExpenses);

    List<ExtraExpenses> findAll();

}
