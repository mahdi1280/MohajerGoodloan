package ir.mohajer.service.userloan;

import ir.mohajer.model.Loan;
import ir.mohajer.model.UserLoan;
import ir.mohajer.model.Users;
import ir.mohajer.repository.UserLoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserLoanServiceImpl implements UserLoanService {

    private final UserLoanRepository userLoanRepository;

    public UserLoanServiceImpl(UserLoanRepository userLoanRepository) {
        this.userLoanRepository = userLoanRepository;
    }

    @Override
    public List<UserLoan> findByUser(Users user) {
        return userLoanRepository.findByUsers(user);
    }

    @Override
    public Optional<UserLoan> findById(long id) {
        return userLoanRepository.findById(id);
    }

    @Override
    public int getCountJoinLoan(Loan loan) {
        return userLoanRepository.getCountJoinLoan(loan.getId());
    }

    @Override
    public List<UserLoan> findByLoan(Loan loan) {
        return userLoanRepository.findByLoan(loan);
    }

    @Override
    public List<UserLoan> findByLoanAndWinnerFalse(Loan loan) {
        return userLoanRepository.findByLoanAndWinnerFalse(loan);
    }

    @Override
    public void save(UserLoan userLoan) {
        userLoanRepository.save(userLoan);
    }

    @Override
    public Optional<UserLoan> findByUserAndLoan(Users users, Loan loan) {
        return userLoanRepository.findByUsersAndLoan(users,loan);
    }
}
