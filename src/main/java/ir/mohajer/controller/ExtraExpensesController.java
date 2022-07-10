package ir.mohajer.controller;

import ir.mohajer.dto.request.ExtraRequest;
import ir.mohajer.model.ExtraExpenses;
import ir.mohajer.service.extraexpenses.ExtraExpensesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
        model.addAttribute("extra",all);
        return "extra";
    }

}
