package ir.mohajer.controller;

import ir.mohajer.service.userservice.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final UsersService usersService;

    public IndexController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("users",usersService.findAll());
        model.addAttribute("serverTime", 212);
        return "index";
    }

}
