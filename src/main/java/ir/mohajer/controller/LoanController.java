package ir.mohajer.controller;

import ir.mohajer.dto.response.LoanResponse;
import ir.mohajer.dto.response.UserResponse;
import ir.mohajer.exception.ErrorMessage;
import ir.mohajer.exception.RuleException;
import ir.mohajer.model.Loan;
import ir.mohajer.model.UserLoan;
import ir.mohajer.model.Users;
import ir.mohajer.service.installment.InstallmentsService;
import ir.mohajer.service.loan.LoanService;
import ir.mohajer.service.userloan.UserLoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;
    private final UserLoanService userLoanService;
    private final InstallmentsService installmentsService;

    public LoanController(LoanService loanService, UserLoanService userLoanService, InstallmentsService installmentsService) {
        this.loanService = loanService;
        this.userLoanService = userLoanService;
        this.installmentsService = installmentsService;
    }

    @GetMapping
    public String getAll(Model model) {
        List<LoanResponse> loanResponse = createLoanResponse(loanService.findAll());
        model.addAttribute("loans", loanResponse);
        return "loan";
    }

    @GetMapping("/user/{loanId}")
    public String getUserLoan(@PathVariable long loanId,Model model){
        Loan loan = loanService.findById(loanId).orElseThrow(() -> new RuleException(ErrorMessage.error("loan.not.found")));
        List<UserLoan> byLoan = userLoanService.findByLoan(loan);
        List<UserResponse> users = createUsers(byLoan);
        model.addAttribute("users",users);
        return "index";
    }

    private List<UserResponse> createUsers(List<UserLoan> byLoan) {
        return byLoan.stream().map(loan->
                UserResponse.builder()
                        .setId(loan.getUsers().getId())
                        .setNationalCode(loan.getUsers().getNationalCode())
                        .setName(loan.getUsers().getName())
                        .build())
                .collect(Collectors.toList());
    }

    private List<LoanResponse> createLoanResponse(List<Loan> all) {
       return all.stream().map(loan ->
                        LoanResponse.builder()
                                .setId(loan.getId())
                                .setAllAmount(installmentsService.getAllAmountByLoan(loan))
                                .setJoinCount(userLoanService.getCountJoinLoan(loan))
                                .setName(loan.getName())
                                .setAmount(loan.getAmount())
                                .build())
                .collect(Collectors.toList());
    }
}
