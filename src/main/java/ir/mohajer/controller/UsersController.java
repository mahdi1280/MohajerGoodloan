package ir.mohajer.controller;

import ir.mohajer.dto.request.DetailsRequest;
import ir.mohajer.dto.request.UserLoanRequest;
import ir.mohajer.dto.request.UserPropertyRequest;
import ir.mohajer.dto.request.UserRequest;
import ir.mohajer.dto.response.*;
import ir.mohajer.exception.ErrorMessage;
import ir.mohajer.exception.RuleException;
import ir.mohajer.model.*;
import ir.mohajer.service.installment.InstallmentsService;
import ir.mohajer.service.loan.LoanService;
import ir.mohajer.service.userloan.UserLoanService;
import ir.mohajer.service.userproperty.UserPropertyService;
import ir.mohajer.service.userservice.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserLoanService userLoanService;
    private final UsersService usersService;
    private final InstallmentsService installmentsService;
    private final LoanService loanService;
    private final UserPropertyService userPropertyService;

    public UsersController(UserLoanService userLoanService, UsersService usersService, InstallmentsService installmentsService, LoanService loanService, UserPropertyService userPropertyService) {
        this.userLoanService = userLoanService;
        this.usersService = usersService;
        this.installmentsService = installmentsService;
        this.loanService = loanService;
        this.userPropertyService = userPropertyService;
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


    @GetMapping("/details/{id}")
    public String showLoanDetails(@PathVariable long id, Model model, DetailsRequest detailsRequest){
        UserLoan userLoan = userLoanService.findById(id).orElseThrow(() -> new RuleException(ErrorMessage.error("user.loan.not.found")));
        List<Installments> byLoanUserId = installmentsService.findByLoanUserId(userLoan);
        DetailsFindResponse detailsFindResponse=createDetailsFindResponse(userLoan,byLoanUserId);
        model.addAttribute("details",detailsFindResponse);
        return "details";
    }

    @GetMapping("/price/{userId}")
    public String showPrice(@PathVariable long userId, Model model, UserPropertyRequest userPropertyRequest) {
        Users users = usersService.findById(userId).orElseThrow(() -> new RuleException(ErrorMessage.error("not.found.user")));
        List<UserProperty> allByUser = userPropertyService.findAllByUser(users);
        model.addAttribute("userPrice", createUserPropertyResponse(users, allByUser));
        return "userPrice";
    }

    @PostMapping("/userProperty")
    public String save(@ModelAttribute UserPropertyRequest userPropertyRequest, Model model, BindingResult bindingResult) {
        if(userPropertyRequest.getPrice()==null)
            bindingResult.rejectValue("price","error.users","price is null");
        else if(userPropertyRequest.getUserId()==null)
            bindingResult.rejectValue("userId","error.users","user id is null");
        Users users = usersService.findById(userPropertyRequest.getUserId()).orElseThrow(() -> new RuleException(ErrorMessage.error("user.not.found")));
        if(!bindingResult.hasFieldErrors()) {
            UserProperty userProperty = createUserProperty(userPropertyRequest, users);
            userPropertyService.save(userProperty);
        }
        List<UserProperty> allByUser = userPropertyService.findAllByUser(users);
        model.addAttribute("userPrice", createUserPropertyResponse(users, allByUser));
        return "userPrice";
    }

    private UserProperty createUserProperty(UserPropertyRequest userPropertyRequest, Users users) {
        return UserProperty.builder()
                .user(users)
                .price(userPropertyRequest.getPrice())
                .build();
    }


    private UserPropertyResponse createUserPropertyResponse(Users users, List<UserProperty> allByUser) {
        return UserPropertyResponse.builder()
                .id(users.getId())
                .username(users.getUsername())
                .userProperties(allByUser)
                .build();
    }

    private DetailsFindResponse createDetailsFindResponse(UserLoan userLoan, List<Installments> byLoanUserId) {
        return DetailsFindResponse.builder()
                .setInstallmentsResponses(createInstallmentsResponses(byLoanUserId))
                .setUserLoanId(userLoan.getId())
                .build();
    }

    private List<InstallmentsResponse> createInstallmentsResponses(List<Installments> byLoanUserId) {
        return byLoanUserId.stream().map(installments ->
                InstallmentsResponse.builder()
                        .setCreatedDate(installments.getCreationDate())
                        .setId(installments.getId())
                        .setAmount(installments.getAmount())
                        .build())
                .collect(Collectors.toList());
    }

    private DetailsResponse createDetailsResponse(Users user, List<UserLoan> byUser) {
        return DetailsResponse.builder()
                .setUserResponse(createUserResponse(user))
                .setUserLoans(createUserLoanResponse(byUser))
                .setLoanResponses(createLoanResponse())
                .setAllPrice(userPropertyService.getAllPriceByUser(user))
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

    private Users createUsers(UserRequest userRequest) {
        return Users.builder()
                .setUsername(userRequest.getUsername())
                .setPassword(userRequest.getPassword())
                .setEmail(userRequest.getEmail())
                .setName(userRequest.getName())
                .setNationalCode(userRequest.getNationalCode())
                .build();
    }
}
