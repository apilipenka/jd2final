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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @RequestMapping(value = "/insertUser", method = RequestMethod.POST, params = "action1")
    public ModelAndView insertUser(@ModelAttribute UserVO userVO, Locale locale, HttpSession httpSession, HttpServletRequest request) {

        String errormessage = null;

        try {
            if (userVO != null) {
                User user = userService.getEntity(userVO.getId());
                httpSession.setAttribute("message", "user.update.success");
                if (user==null) {
                    user = new User();

                    httpSession.setAttribute("message", "user.add.success");
                }


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
                userService.updateEntity(user);




            }


            Object command;
            command = request.getAttribute("command");


            if (command == null) {
                command = request.getParameter("userID");
            }
            if (command != null && command.toString().equals("register"))
                return new ModelAndView("redirect:/login");
            else
                return new ModelAndView("redirect:/user-list");

        } catch (ParseException e) {
            errormessage = "error.parseexception";
        } catch (DataIntegrityViolationException e) {
            errormessage = "error.duplicate.user";
        }

        httpSession.removeAttribute("message");
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("command", "register");
        modelAndView.addObject("roles", userRoleService.getAllEntities());
        modelAndView.addObject("user", userVO);
        modelAndView.addObject("error", errormessage);

        return modelAndView;

    }

    @RequestMapping(value = "/insertUser", method = RequestMethod.POST, params = "action2")
    public ModelAndView cancelInsertUser(HttpServletRequest request) {

        Object command;
        command = request.getAttribute("command");


        if (command == null) {
            command = request.getParameter("command");
        }
        if (command != null && command.toString().equals("register"))
            return new ModelAndView("redirect:/login");
        else
            return new ModelAndView("redirect:/user-list");


    }

    @RequestMapping(value = "/insertUser", method = RequestMethod.GET)
    public ModelAndView insertUserLang(@ModelAttribute UserVO userVO, Locale locale, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("command", "register");
        modelAndView.addObject("roles", userRoleService.getAllEntities());
        modelAndView.addObject("user", userVO);

        return modelAndView;
    }

    @RequestMapping(value = "/user-list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView userList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("user-list");
        modelAndView.addObject("command", "user-list");
        List<User> userList = new ArrayList<User>();

        Object name = request.getAttribute("userName");

        if (name != null) {
            userList = userService.searchEntityByName(name.toString());
        } else {
            name = request.getParameter("userName");
            if (name != null) {
                userList = userService.searchEntityByName(name.toString());
            } else {
                userList = userService.getAllEntities();
            }
        }

        if (userList != null) {
            List<UserVO> userVOList = new ArrayList<UserVO>();

            for (User user : userList) {
                userVOList.add(user.createUserVO());
            }

            if (userVOList != null)
                modelAndView.addObject("userList", userVOList);
        }


        return modelAndView;
    }

    @RequestMapping(value = "/edituser", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editUser(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("new-user");
        modelAndView.addObject("command", "user-list");
        User user;


        Object name = request.getAttribute("userID");

        if (name != null) {
            user = userService.getEntity(new Integer(name.toString()));
        } else {
            name = request.getParameter("userID");
            user = userService.getEntity(new Integer(name.toString()));
        }

        modelAndView.addObject("user", user.createUserVO());

        modelAndView.addObject("roles", userRoleService.getAllEntities());

        return modelAndView;
    }


    @RequestMapping(value = "/deleteuser", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView deleteUser(HttpServletRequest request, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("redirect:/user-list");
        modelAndView.addObject("command", "user-list");

        Object name = request.getAttribute("userID");

        if (name == null) {
            name = request.getParameter("userID");

        }

        userService.deleteEntity(new Integer(name.toString()));
        httpSession.setAttribute("message", "user.delete.success");

        return modelAndView;
    }


}
