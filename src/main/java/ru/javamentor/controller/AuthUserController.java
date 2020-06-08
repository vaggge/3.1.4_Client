package ru.javamentor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.javamentor.model.User;
import ru.javamentor.service.UserService;


@Controller
public class AuthUserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/user")
    public String userInfo() {
        return "User";
    }

    @GetMapping(value = "/user/authuser")
    @ResponseBody
    public User getUser(Authentication authentication){
        return (User) authentication.getPrincipal();
    }
}
