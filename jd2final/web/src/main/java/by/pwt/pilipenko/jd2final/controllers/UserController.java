package by.pwt.pilipenko.jd2final.controllers;

import by.pwt.pilipenko.jd2final.dao.VO.UserVO;
import by.pwt.pilipenko.jd2final.dao.entities.User;
import by.pwt.pilipenko.jd2final.dao.entities.UserRole;
import by.pwt.pilipenko.jd2final.services.interfaces.IUserRoleService;
import by.pwt.pilipenko.jd2final.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    //public ModelAndView insertUser(@RequestParam("login") String login, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
    //                               @RequestParam("password") String password, @RequestParam("personalNumber") String personalNumber,
     //                              @RequestParam("role") int userRoleId, @RequestParam("birthDate") String birthDateStr) {
    @ResponseStatus(value= HttpStatus.OK)
    public ModelAndView insertUser(@RequestBody UserVO userVO) {
        try {
            User user = new User();
            user.setLogin(userVO.getLogin());
            user.setFirstName(userVO.getFirstName());
            user.setLastName(userVO.getLastName());
            user.setPassword(userVO.getPassword());
            user.setPersonalNumber(userVO.getPersonalNumber());

            UserRole userRole = userRoleService.getEntity(userVO.getUserRoleID());
            user.setUserRole(userRole);

            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date birthDate = format.parse(userVO.getBirthDate());

            user.setBirthDate(birthDate);


            userService.insertEntity(user);
            ModelAndView modelAndView = new ModelAndView("redirect:login");
            return modelAndView;

        } catch (ParseException e) {
            ModelAndView modelAndView = new ModelAndView("register");
            modelAndView.addObject("command", "register");
            modelAndView.addObject("roles", userRoleService.getAllEntities());
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }


    }
}
