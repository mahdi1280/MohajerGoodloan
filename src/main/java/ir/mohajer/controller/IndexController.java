package ir.mohajer.controller;

import ir.mohajer.dto.response.UserResponse;
import ir.mohajer.model.Users;
import ir.mohajer.service.userservice.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IndexController {

    private final UsersService usersService;

    public IndexController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/")
    public String index(Model model){
        List<Users> all = usersService.findAll();
        List<UserResponse> userResponses = createUserResponse(all);
        model.addAttribute("users",userResponses);
        return "index";
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
