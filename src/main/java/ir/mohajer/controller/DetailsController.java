package ir.mohajer.controller;

import ir.mohajer.dto.request.DetailsRequest;
import ir.mohajer.dto.response.DetailsFindResponse;
import ir.mohajer.dto.response.InstallmentsResponse;
import ir.mohajer.exception.ErrorMessage;
import ir.mohajer.exception.RuleException;
import ir.mohajer.model.Installments;
import ir.mohajer.model.UserLoan;
import ir.mohajer.service.installment.InstallmentsService;
import ir.mohajer.service.userloan.UserLoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/details")
public class DetailsController {

    private final UserLoanService userLoanService;
    private final InstallmentsService installmentsService;

    public DetailsController(UserLoanService userLoanService, InstallmentsService installmentsService) {
        this.userLoanService = userLoanService;
        this.installmentsService = installmentsService;
    }

    @PostMapping
    public String index(@Valid @ModelAttribute DetailsRequest detailsRequest, BindingResult bindingResult, Model model){
        if(!bindingResult.hasFieldErrors()){
            UserLoan userLoan = userLoanService.findById(detailsRequest.getLoanId()).orElseThrow(() -> new RuleException(ErrorMessage.error("nt.found")));
            Installments installments= createInstallments(userLoan,detailsRequest.getAmount());
            installmentsService.save(installments);
        }
        UserLoan userLoan = userLoanService.findById(detailsRequest.getLoanId()).orElseThrow(() -> new RuleException(ErrorMessage.error("user.loan.not.found")));
        List<Installments> byLoanUserId = installmentsService.findByLoanUserId(userLoan);
        DetailsFindResponse detailsFindResponse=createDetailsFindResponse(userLoan,byLoanUserId);
        model.addAttribute("details",detailsFindResponse);
        return "details";
    }

    private Installments createInstallments(UserLoan userLoan, Integer amount) {
       return Installments.builder()
                .setUserLoan(userLoan)
                .setAmount(amount)
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
}
