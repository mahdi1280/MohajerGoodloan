package ir.mohajer.controller;

import ir.mohajer.dto.request.UserLoanRequest;
import ir.mohajer.dto.response.DetailsResponse;
import ir.mohajer.dto.response.LoanResponse;
import ir.mohajer.dto.response.UserLoanResponse;
import ir.mohajer.dto.response.UserResponse;
import ir.mohajer.exception.ErrorMessage;
import ir.mohajer.exception.RuleException;
import ir.mohajer.model.Loan;
import ir.mohajer.model.UserLoan;
import ir.mohajer.model.Users;
import ir.mohajer.service.installment.InstallmentsService;
import ir.mohajer.service.loan.LoanService;
import ir.mohajer.service.userloan.UserLoanService;
import ir.mohajer.service.userservice.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/userLoans")
public class UserLoanController {

    private final UsersService usersService;
    private final UserLoanService userLoanService;
    private final LoanService loanService;
    private final InstallmentsService installmentsService;

    public UserLoanController(UsersService usersService, UserLoanService userLoanService, LoanService loanService, InstallmentsService installmentsService) {
        this.usersService = usersService;
        this.userLoanService = userLoanService;
        this.loanService = loanService;
        this.installmentsService = installmentsService;
    }

    @PostMapping
    public String save(@ModelAttribute UserLoanRequest userLoanRequest, Model model, BindingResult bindingResult){
        Users users = usersService.findById(userLoanRequest.getUserId()).orElseThrow(() -> new RuleException(ErrorMessage.error("user.not.found")));
        Loan loan = loanService.fidByName(userLoanRequest.getName()).orElseThrow(() -> new RuleException(ErrorMessage.error("load.not.found")));
        Optional<UserLoan> byUserAndLoan = userLoanService.findByUserAndLoan(users, loan);
        if(byUserAndLoan.isPresent())
            bindingResult.rejectValue("name","errors.user","is exist");
        if(!bindingResult.hasFieldErrors()) {
            UserLoan userLoan = createUserLoan(users, loan);
            userLoanService.save(userLoan);
        }
        List<UserLoan> byUser = userLoanService.findByUser(users);
        DetailsResponse detailsResponse = createDetailsResponse(users, byUser);
        model.addAttribute("userLoan", detailsResponse);
        return "userLoan";
    }

    private UserLoan createUserLoan(Users users, Loan loan) {
        return UserLoan.builder()
                .setUsers(users)
                .setLoan(loan)
                .build();
    }

    private DetailsResponse createDetailsResponse(Users user, List<UserLoan> byUser) {
        return DetailsResponse.builder()
                .setUserResponse(createUserResponse(user))
                .setUserLoans(createUserLoanResponse(byUser))
                .setLoanResponses(createLoanResponse())
                .build();
    }
    private UserResponse createUserResponse(Users user) {
        return UserResponse.builder()
                .setName(user.getName())
                .setId(user.getId())
                .setNationalCode(user.getNationalCode())
                .build();
    }
    private List<UserLoanResponse> createUserLoanResponse(List<UserLoan> byUser) {
        return byUser.stream().map( u-> UserLoanResponse.builder()
                        .setAmount(installmentsService.getAmountByLoanUser(u))
                        .setLoan(u.getLoan())
                        .setId(u.getId())
                        .setWinner(u.isWinner())
                        .build())
                .collect(Collectors.toList());
    }

    private List<LoanResponse> createLoanResponse() {
        List<Loan> all = loanService.findAll();
        return all.stream().map(loan -> LoanResponse.builder()
                        .setName(loan.getName())
                        .setAmount(loan.getAmount())
                        .build())
                .collect(Collectors.toList());
    }
}
