package by.pwt.pilipenko.jd2final.controllers;

import by.pwt.pilipenko.jd2final.dao.entities.User;
import by.pwt.pilipenko.jd2final.dao.entities.UserRole;
import by.pwt.pilipenko.jd2final.services.interfaces.IUserRoleService;
import by.pwt.pilipenko.jd2final.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by apilipenka on 10/7/2016.
 */
@Controller
public class UserController {

    @Autowired
    IUserService userService;
    @Autowired
    IUserRoleService userRoleService;

    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    public ModelAndView insertUser(@RequestParam("login") String login, @RequestParam("firstName") String firstName, @RequestParam("lastName")String lastName,
                                   @RequestParam("password")String password, @RequestParam("personalNumber")String personalNumber,
                                   @RequestParam("role")int userRoleId, @RequestParam("birthDate")String birthDateStr) throws ParseException {
        User user = new User();
        user.setLogin(login);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setPersonalNumber(personalNumber);

        UserRole userRole = userRoleService.getEntity(userRoleId);
        user.setUserRole(userRole);

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date birthDate = format.parse(birthDateStr);
        user.setBirthDate(birthDate);


        userService.insertEntity(user);
        ModelAndView modelAndView = new ModelAndView("login");
        return   modelAndView;

    }
}
