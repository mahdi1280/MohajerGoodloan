package ir.mohajer.service.userloan;

import ir.mohajer.model.UserLoan;
import ir.mohajer.model.Users;
import ir.mohajer.repository.UserLoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
