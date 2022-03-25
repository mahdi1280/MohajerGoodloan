package ir.mohajer.controller;

import ir.mohajer.dto.request.UserLoanRequest;
import ir.mohajer.dto.request.UserRequest;
import ir.mohajer.dto.response.DetailsResponse;
import ir.mohajer.dto.response.LoanResponse;
import ir.mohajer.dto.response.UserLoanResponse;
import ir.mohajer.dto.response.UserResponse;
import ir.mohajer.exception.ErrorMessage;
import ir.mohajer.exception.RuleException;
import ir.mohajer.model.Installments;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserLoanService userLoanService;
    private final UsersService usersService;
    private final InstallmentsService installmentsService;
    private final LoanService loanService;

    public UsersController(UserLoanService userLoanService, UsersService usersService, InstallmentsService installmentsService, LoanService loanService) {
        this.userLoanService = userLoanService;
        this.usersService = usersService;
        this.installmentsService = installmentsService;
        this.loanService = loanService;
    }

    @GetMapping("/{id}")
    public String showLoan(@PathVariable long id, UserLoanRequest userLoanRequest, Model model) {
        Users user = usersService.findById(id).orElseThrow(() -> new RuleException(ErrorMessage.error("user.not.found")));
        List<UserLoan> byUser = userLoanService.findByUser(user);
        DetailsResponse detailsResponse = createDetailsResponse(user, byUser);
        model.addAttribute("userLoan", detailsResponse);
        return "userLoan";
    }

    @PostMapping
    public String save(@ModelAttribute UserRequest userRequest, Model model, BindingResult bindingResult){
        if(!userRequest.getPassword().equals(userRequest.getRePassword()))
            bindingResult.rejectValue("rePassword","error.users","rePassword by password not same!");
        Optional<Users> byUserName = usersService.findByUserName(userRequest.getName());
        if(byUserName.isPresent())
            bindingResult.rejectValue("username","error.users","username is exist");
        if(!bindingResult.hasFieldErrors()){
            Users users=createUsers(userRequest);
            usersService.save(users);
        }
        List<Users> all = usersService.findAll();
        List<UserResponse> userResponses = createUserResponse(all);
        model.addAttribute("users",userResponses);
        return "index";
    }

    private Users createUsers(UserRequest userRequest) {
        return Users.builder()
                .setUsername(userRequest.getUsername())
                .setPassword(userRequest.getPassword())
                .setEmail(userRequest.getEmail())
                .setName(userRequest.getName())
                .setNationalCode(userRequest.getNationalCode())
                .build();
    }

    @GetMapping("/details/{id}")
    public String showLoanDetails(@PathVariable long id, Model model){
        UserLoan userLoan = userLoanService.findById(id).orElseThrow(() -> new RuleException(ErrorMessage.error("user.loan.not.found")));
        List<Installments> byLoanUserId = installmentsService.findByLoanUserId(userLoan);
        model.addAttribute("details",byLoanUserId);
        return "details";
    }

    private DetailsResponse createDetailsResponse(Users user, List<UserLoan> byUser) {
        return DetailsResponse.builder()
                .setUserResponse(createUserResponse(user))
                .setUserLoans(createUserLoanResponse(byUser))
                .setLoanResponses(createLoanResponse())
                .build();
    }

    private List<LoanResponse> createLoanResponse() {
        List<Loan> all = loanService.findAll();
        return all.stream().map(loan -> LoanResponse.builder()
                        .setName(loan.getName())
                        .setAmount(loan.getAmount())
                        .build())
                .collect(Collectors.toList());
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

    private List<UserResponse> createUserResponse(List<Users> all) {
        return all.stream().map(user-> UserResponse.builder()
                        .setName(user.getName())
                        .setId(user.getId())
                        .setNationalCode(user.getNationalCode())
                        .build())
                .collect(Collectors.toList());
    }
}
