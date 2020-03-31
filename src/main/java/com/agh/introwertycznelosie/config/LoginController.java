package com.agh.introwertycznelosie.config;

import com.agh.introwertycznelosie.Validators.UserValidator;
import com.agh.introwertycznelosie.data.Role;
import com.agh.introwertycznelosie.data.User;
import com.agh.introwertycznelosie.repositories.RoleRepository;
import com.agh.introwertycznelosie.services.SecurityService;
import com.agh.introwertycznelosie.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    @GetMapping("/home")
    public String home(){
        return "home";
    }
}
