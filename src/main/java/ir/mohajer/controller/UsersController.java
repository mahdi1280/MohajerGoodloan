package ir.mohajer.controller;

import ir.mohajer.dto.response.DetailsResponse;
import ir.mohajer.dto.response.UserLoanResponse;
import ir.mohajer.exception.ErrorMessage;
import ir.mohajer.exception.RuleException;
import ir.mohajer.model.Installments;
import ir.mohajer.model.UserLoan;
import ir.mohajer.model.Users;
import ir.mohajer.service.installment.InstallmentsService;
import ir.mohajer.service.userloan.UserLoanService;
import ir.mohajer.service.userservice.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserLoanService userLoanService;
    private final UsersService usersService;
    private final InstallmentsService installmentsService;

    public UsersController(UserLoanService userLoanService, UsersService usersService, InstallmentsService installmentsService) {
        this.userLoanService = userLoanService;
        this.usersService = usersService;
        this.installmentsService = installmentsService;
    }

    @GetMapping("/{id}")
    public String showLoan(@PathVariable long id, Model model){
        Users user = usersService.findById(id).orElseThrow(()->new RuleException(ErrorMessage.error("user.not.found")));
        List<UserLoan> byUser = userLoanService.findByUser(user);
        DetailsResponse detailsResponse= createDetailsResponse(user,byUser);
        model.addAttribute("userLoan",detailsResponse);
        return "userLoan";
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
                .setUsers(user)
                .setUserLoans(createUserLoanResponse(byUser))
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
}
