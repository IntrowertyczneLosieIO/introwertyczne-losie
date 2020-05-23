package com.agh.introwertycznelosie.config;

import com.agh.introwertycznelosie.data.RegistrationToken;
import com.agh.introwertycznelosie.data.Role;
import com.agh.introwertycznelosie.data.User;
import com.agh.introwertycznelosie.repositories.UserRepository;
import com.agh.introwertycznelosie.services.SecurityService;
import com.agh.introwertycznelosie.services.UserDetailsServiceImpl;
import com.agh.introwertycznelosie.services.UserService;
import org.hibernate.type.MetaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.UUID;

@Controller
public class LoginController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailSenderService emailSenderService;


    @RequestMapping(value = "/getCurrentUserRole", method = RequestMethod.GET)
    public String getRole() {
        UserDetails userDetails = securityService.findLoggedInUser();
        User user = userService.findByUsername(userDetails.getUsername());
        return user.getRoles().iterator().next().getName();
    }

    @RequestMapping(value = "/login", method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> index(@RequestBody User user) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            securityService.autoLogin(userDetails.getUsername(), user.getPassword());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found", e);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> addUser(@RequestBody User user) {
        RegistrationToken registrationToken = new RegistrationToken();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getUsername());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("introwertycznelosie@gmail.com");
        mailMessage.setText("Your registration "
                    +"http://localhost:8080/registration?token="+registrationToken.getToken());
        emailSenderService.sendEmail(mailMessage);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registerUser(@RequestParam("token") String registrationToken) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("../static/index");
        return modelAndView;

    }



    @RequestMapping(value = "/register", method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> register(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @RequestMapping(value = {"/*"}, method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("../static/index");
        return modelAndView;
    }
}
