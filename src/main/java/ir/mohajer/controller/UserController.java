package ir.mohajer.controller;

import ir.mohajer.service.userservice.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("users",usersService.findAll());
        return "index";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
}
