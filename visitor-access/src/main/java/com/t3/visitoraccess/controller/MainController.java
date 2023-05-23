package com.t3.visitoraccess.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.t3.visitoraccess.entity.SecurityUser;
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
    public String saveVisitor(Visitor visitor, @AuthenticationPrincipal SecurityUser user){
        userService.addVisitor(user.getUsername(), visitor);
        return "redirect:/visitor";
    }

    @GetMapping("/visitor")
    public String listVisitor(Model model,  @AuthenticationPrincipal SecurityUser user){
        Set<Visitor> visitors = userService.returnAllVisitorsFromUser(user.getUsername());
        model.addAttribute("visitors", visitors);
        return "visitorList";
    }

    @GetMapping("/visitor/delete/{id}")
    public String deleteVisitor(@PathVariable(value = "id") long id, @AuthenticationPrincipal SecurityUser user){
        userService.removeVisitor(id, user.getUsername());
        return "redirect:/visitor";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/visitors")
    public String adminListVisitors(Model model){
        List<Visitor> visitors = visitorService.returnAllVisitors();
        model.addAttribute("visitors", visitors);
        return "visitorList";
    }
}
