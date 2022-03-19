package ir.mohajer.controller;

import ir.mohajer.dto.response.DetailsResponse;
import ir.mohajer.exception.ErrorMessage;
import ir.mohajer.exception.RuleException;
import ir.mohajer.model.UserLoan;
import ir.mohajer.model.Users;
import ir.mohajer.service.userloan.UserLoanService;
import ir.mohajer.service.userservice.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserLoanService userLoanService;
    private final UsersService usersService;

    public UsersController(UserLoanService userLoanService, UsersService usersService) {
        this.userLoanService = userLoanService;
        this.usersService = usersService;
    }

    @GetMapping("/{id}")
    public String showLoan(@PathVariable long id, Model model){
        Users user = usersService.findById(id).orElseThrow(()->new RuleException(ErrorMessage.error("user.not.found")));
        List<UserLoan> byUser = userLoanService.findByUser(user);
        DetailsResponse detailsResponse= createDetailsResponse(user,byUser);
        model.addAttribute("userLoan",detailsResponse);
        return "loans";
    }

    @GetMapping("/details/{id}")
    public String showLoanDetails(@PathVariable long id, Model model){
        return "details";
    }

        private DetailsResponse createDetailsResponse(Users user, List<UserLoan> byUser) {
        return DetailsResponse.builder()
                .setUsers(user)
                .setUserLoans(byUser)
                .build();
    }
}
