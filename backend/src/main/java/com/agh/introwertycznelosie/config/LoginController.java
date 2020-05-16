package com.agh.introwertycznelosie.config;

import com.agh.introwertycznelosie.data.User;
import com.agh.introwertycznelosie.services.SecurityService;
import com.agh.introwertycznelosie.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private SecurityService securityService;
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ResponseEntity<HttpStatus> index(@RequestBody User user){
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        securityService.autoLogin(userDetails.getUsername(), userDetails.getPassword());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
