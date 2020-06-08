package ru.javamentor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping("/login")
    public ModelAndView getLoginPage(){
        return new ModelAndView("login");
    }

    @GetMapping("/admin")
    public ModelAndView getAdminPage(){
        return new ModelAndView("Users");
    }


}
