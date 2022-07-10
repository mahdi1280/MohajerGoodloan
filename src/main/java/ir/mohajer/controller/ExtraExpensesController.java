package ir.mohajer.controller;

import ir.mohajer.dto.request.ExtraRequest;
import ir.mohajer.dto.response.ExtraExpensesResponse;
import ir.mohajer.model.ExtraExpenses;
import ir.mohajer.service.extraexpenses.ExtraExpensesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/extra")
public class ExtraExpensesController {

    private final ExtraExpensesService extraExpensesService;

    public ExtraExpensesController(ExtraExpensesService extraExpensesService) {
        this.extraExpensesService = extraExpensesService;
    }

    @GetMapping
    public String getExtra(Model model, ExtraRequest extraRequest){
        List<ExtraExpenses> all = extraExpensesService.findAll();
        model.addAttribute("extra",createExtraExpensesResponse(all));
        return "extra";
    }

    @PostMapping
    public String save(@ModelAttribute ExtraRequest extraRequest, Model model, BindingResult bindingResult){
        if(extraRequest.getPrice()==null){
            bindingResult.rejectValue("price", "error.user", "price is null");
        }if (extraRequest.getDetails()==null){
            bindingResult.rejectValue("details", "error.user", "details is null");
        }
        if(!bindingResult.hasFieldErrors()){
            ExtraExpenses extraExpenses=createExtra(extraRequest);
            extraExpensesService.save(extraExpenses);
        }
        List<ExtraExpenses> all = extraExpensesService.findAll();
        model.addAttribute("extra",createExtraExpensesResponse(all));
        return "extra";
    }

    private ExtraExpensesResponse createExtraExpensesResponse(List<ExtraExpenses> all) {
       return new ExtraExpensesResponse(all,extraExpensesService.getAllPrice());
    }


    private ExtraExpenses createExtra(ExtraRequest extraRequest) {
        return ExtraExpenses.builder()
                .price(extraRequest.getPrice())
                .details(extraRequest.getDetails())
                .build();
    }

}
