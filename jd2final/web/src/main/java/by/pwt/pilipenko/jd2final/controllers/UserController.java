package by.pwt.pilipenko.jd2final.controllers;

import by.pwt.pilipenko.jd2final.dao.VO.UserVO;
import by.pwt.pilipenko.jd2final.dao.entities.User;
import by.pwt.pilipenko.jd2final.dao.entities.UserRole;
import by.pwt.pilipenko.jd2final.services.interfaces.IUserRoleService;
import by.pwt.pilipenko.jd2final.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by apilipenka on 10/7/2016.
 */
@Controller
public class UserController {

    @Autowired
    IUserService userService;
    @Autowired
    IUserRoleService userRoleService;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/insertUser", method = RequestMethod.POST, params="action1")
    public ModelAndView insertUser(@ModelAttribute UserVO userVO, Locale locale, HttpSession httpSession) {

        String errormessage = null;

        try {
            if (userVO!=null) {
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

                httpSession.setAttribute("message", "user.add.success");
            }

            return new ModelAndView("redirect:/login");

        } catch (ParseException e) {
            errormessage = "error.parseexception";
        }


        catch (DataIntegrityViolationException  e) {
            errormessage = "error.duplicate,user";
        }


        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("command", "register");
        modelAndView.addObject("roles", userRoleService.getAllEntities());
        modelAndView.addObject("user", userVO);
        modelAndView.addObject("error", errormessage);

        return modelAndView;

    }

    @RequestMapping(value = "/insertUser", method = RequestMethod.POST, params="action2")
    public ModelAndView cancelInsertUser() {
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/insertUser", method = RequestMethod.GET)
    public ModelAndView insertUserLang(@ModelAttribute UserVO userVO, Locale locale, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("command", "register");
        modelAndView.addObject("roles", userRoleService.getAllEntities());
        modelAndView.addObject("user", userVO);

        return modelAndView;
    }

}
