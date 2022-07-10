package ir.mohajer.service.extraexpenses;

import ir.mohajer.model.ExtraExpenses;
import ir.mohajer.repository.ExtraExpensesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtraExpensesServiceImpl implements ExtraExpensesService {

    private final ExtraExpensesRepository extraExpensesRepository;

    public ExtraExpensesServiceImpl(ExtraExpensesRepository extraExpensesRepository) {
        this.extraExpensesRepository = extraExpensesRepository;
    }

    @Override
    public void save(ExtraExpenses extraExpenses) {
        extraExpensesRepository.save(extraExpenses);
    }

    @Override
    public List<ExtraExpenses> findAll() {
        return extraExpensesRepository.findAll();
    }

    @Override
    public int getAllPrice() {
        Integer allPrice = extraExpensesRepository.getAllPrice();
        return allPrice ==null ? 0 : allPrice;
    }
}
