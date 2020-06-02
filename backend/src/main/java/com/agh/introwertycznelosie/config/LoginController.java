package com.agh.introwertycznelosie.config;

import com.agh.introwertycznelosie.data.RegistrationToken;
import com.agh.introwertycznelosie.data.User;
import com.agh.introwertycznelosie.mockups.UserMockup;
import com.agh.introwertycznelosie.repositories.RegistrationTokenRepository;
import com.agh.introwertycznelosie.repositories.UserRepository;
import com.agh.introwertycznelosie.services.SecurityService;
import com.agh.introwertycznelosie.services.UserDetailsServiceImpl;
import com.agh.introwertycznelosie.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

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
    @Autowired
    RegistrationTokenRepository registrationTokenRepository;

    Logger logger = LogManager.getLogger(LoginController.class);


    @GetMapping("/home")
    public String home() {
        return "home";
    }

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
            logger.info("Logged in user " + user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found", e);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> addUser(@RequestBody UserMockup user) {
        RegistrationToken registrationToken = new RegistrationToken(user.getUsername(), user.getRole());
        registrationTokenRepository.save(registrationToken);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getUsername());
        mailMessage.setSubject("Registration!");
        mailMessage.setFrom("introwertycznelosie@gmail.com");
        mailMessage.setText("Your registration "
                    +"http://localhost:8080/registration?token="+registrationToken.getToken());
        emailSenderService.sendEmail(mailMessage);
        logger.info("Sent registration mail to: " + user);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @RequestMapping(value = "/registration", method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> registerUser(@RequestParam("token") String registrationToken, @RequestBody UserMockup userMockup) {
        RegistrationToken token = registrationTokenRepository.findByToken(registrationToken);
        if(token ==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token Not Found");
        }
        if(userService.findByUsername(token.getEmail()) != null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token Not Found");
        }
        User user = new User(token.getEmail(), userMockup.getPassword(), token.getRole());
        user.setPassword(bCryptPasswordEncoder.encode(userMockup.getPassword()));
        userRepository.save(user);
        logger.info("Added user " + user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = {"/", "/login", "/majors", "/registration", "/logout", "/rooms", "/exams"}, method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("../static/index");
        return modelAndView;
    }
}
