package main.java.com.agh.introwertycznelosie.config;

import com.agh.introwertycznelosie.Validators.UserValidator;
import main.java.com.agh.introwertycznelosie.data.Role;
import main.java.com.agh.introwertycznelosie.data.User;
import main.java.com.agh.introwertycznelosie.repositories.RoleRepository;
import com.agh.introwertycznelosie.services.SecurityService;
import com.agh.introwertycznelosie.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value={"/index"}, method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("../static/index");
        return modelAndView;
    }
}
