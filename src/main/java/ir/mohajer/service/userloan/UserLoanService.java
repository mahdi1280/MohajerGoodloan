package ir.mohajer.service.userloan;

import ir.mohajer.model.UserLoan;
import ir.mohajer.model.Users;

import java.util.List;

public interface UserLoanService {

    List<UserLoan> findByUser(Users user);
}
