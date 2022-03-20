package ir.mohajer.service.loan;

import ir.mohajer.model.Loan;
import ir.mohajer.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService{

    private final LoanRepository loanRepository;

    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }


    @Override
    public List<Loan> findAll() {
        return loanRepository.findAll();
    }
}
