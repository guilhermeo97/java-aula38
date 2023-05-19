package com.t3.visitoraccess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.t3.visitoraccess.entity.User;
import com.t3.visitoraccess.entity.Visitor;
import com.t3.visitoraccess.service.UserService;
import com.t3.visitoraccess.service.VisitorService;

@Controller
public class MainController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private VisitorService visitorService;

    @GetMapping({"/", "/index"})
    public String index(){
        return "index";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String createUser(User user){
        userService.createUser(user);
        return "redirect:/";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String userPage(){
        return "user";
    }

    @GetMapping("/visitor/add")
    public String newVisitor(Model model){
        model.addAttribute("visitor", new Visitor());
        return "addVisitor";
    }

    @PostMapping("/visitor/save")
    public String saveVisitor(Visitor visitor){
        visitorService.newVisit(visitor);
        return "redirect:/";
    }
}
