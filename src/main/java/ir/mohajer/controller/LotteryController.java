package ir.mohajer.controller;

import ir.mohajer.dto.request.LotteryRequest;
import ir.mohajer.dto.response.LotteryFindResponse;
import ir.mohajer.dto.response.LotteryResponse;
import ir.mohajer.exception.ErrorMessage;
import ir.mohajer.exception.RuleException;
import ir.mohajer.model.Loan;
import ir.mohajer.model.UserLoan;
import ir.mohajer.service.loan.LoanService;
import ir.mohajer.service.userloan.UserLoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/lottery")
public class LotteryController {

    private final LoanService loanService;
    private final UserLoanService userLoanService;

    public LotteryController(LoanService loanService, UserLoanService userLoanService) {
        this.loanService = loanService;
        this.userLoanService = userLoanService;
    }

    @GetMapping
    public String lottery(Model model, LotteryRequest lotteryRequest) {
        List<Loan> all = loanService.findAll();
        LotteryResponse lotteryResponses = createLotteryResponse(all,Collections.emptyList());
        model.addAttribute("lotteryFindResponse", lotteryResponses);
        return "lottery";
    }

    @PostMapping("/name")
    public String get(Model model, @ModelAttribute LotteryRequest lotteryRequest, BindingResult bindingResult){
        List<Loan> all = loanService.findAll();
        Loan loan = loanService.fidByName(lotteryRequest.getName()).orElseThrow(() -> new RuleException(ErrorMessage.error("not.found")));
        List<UserLoan> byLoan = userLoanService.findByLoanAndWinnerFalse(loan);
        Collections.shuffle(byLoan);
        createLotteryFindResponse(byLoan);
        LotteryResponse lotteryResponses = createLotteryResponse(all,createLotteryFindResponse(byLoan));
        model.addAttribute("lotteryFindResponse", lotteryResponses);
        return "lottery";
    }

    @GetMapping("/winner/{id}")
    public String winner(@PathVariable long id,Model model,LotteryRequest lotteryRequest){
        UserLoan userLoan = userLoanService.findById(id).orElseThrow(() -> new RuleException(ErrorMessage.error("not.found")));
        userLoan.setWinner(true);
        userLoanService.save(userLoan);
        List<Loan> all = loanService.findAll();
        LotteryResponse lotteryResponses = createLotteryResponse(all,Collections.emptyList());
        model.addAttribute("lotteryFindResponse", lotteryResponses);
        return "lottery";
    }

    private List<LotteryFindResponse> createLotteryFindResponse(List<UserLoan> byLoan) {
        return byLoan.stream().map(userLoan -> LotteryFindResponse.builder()
                .setId(userLoan.getId())
                .setNationalCode(userLoan.getUsers().getNationalCode())
                .setUsername(userLoan.getUsers().getUsername())
                .setWinner(userLoan.isWinner())
                .setAmount(userLoan.getLoan().getAmount())
                .build())
                .collect(Collectors.toList());
    }

    private LotteryResponse createLotteryResponse(List<Loan> all,List<LotteryFindResponse> lotteryFindResponses) {
       return LotteryResponse.builder()
                .setLotteryFindResponses(lotteryFindResponses)
                .setName(all.stream().map(Loan::getName).collect(Collectors.toList()))
                .build();
    }

}
