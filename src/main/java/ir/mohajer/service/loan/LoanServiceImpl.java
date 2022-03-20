package ir.mohajer.service.loan;

import ir.mohajer.model.Loan;
import ir.mohajer.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Loan> findById(long loanId) {
        return loanRepository.findById(loanId);
    }

    @Override
    public Optional<Loan> fidByName(String name) {
        return loanRepository.findByName(name);
    }

    @Override
    public void save(Loan loan) {
        loanRepository.save(loan);
    }
}
