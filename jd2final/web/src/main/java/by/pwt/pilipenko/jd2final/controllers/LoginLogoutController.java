package by.pwt.pilipenko.jd2final.controllers;

/**
 * Created by apilipenka on 10/7/2016.
 */
import java.security.Principal;

import by.pwt.pilipenko.jd2final.services.interfaces.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginLogoutController {

    @Autowired
    IUserRoleService roleService;

    @RequestMapping("/helloworld")
    public ModelAndView hello(ModelMap model,Principal principal) {

        String loggedInUserName=principal.getName();

        return new ModelAndView("admin", "userName", loggedInUserName);
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(ModelMap model) {

        return "login";

    }

    @RequestMapping(value="/loginError", method = RequestMethod.GET)
    public String loginError(ModelMap model) {
        model.addAttribute("error", "Wrong login or password");
        return "login";

    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String register(ModelMap model) {
        model.addAttribute("command", "register");
        model.addAttribute("roles", roleService.getAllEntities());
        return "new-user";

    }

}

