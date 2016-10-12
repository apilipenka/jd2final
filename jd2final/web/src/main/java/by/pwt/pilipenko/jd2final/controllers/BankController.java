package by.pwt.pilipenko.jd2final.controllers;

import by.pwt.pilipenko.jd2final.dao.VO.BankVO;
import by.pwt.pilipenko.jd2final.dao.VO.UserVO;
import by.pwt.pilipenko.jd2final.dao.entities.Bank;
import by.pwt.pilipenko.jd2final.dao.entities.User;
import by.pwt.pilipenko.jd2final.services.interfaces.IBankService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by apilipenka on 10/7/2016.
 */
@Controller
public class BankController {

    @Autowired
    IBankService bankService;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/insertBank", method = RequestMethod.POST, params = "action1")
    public ModelAndView insertUser(@ModelAttribute BankVO bankVO, Locale locale, HttpSession httpSession, HttpServletRequest request) {

        String errormessage = null;

        try {
            if (bankVO != null) {
                Bank bank = bankService.getEntity(bankVO.getId());
                httpSession.setAttribute("message", "bank.update.success");
                if (bank == null) {
                    bank = new Bank();


                    httpSession.setAttribute("message", "bank.add.success");
                }

                bank.setName(bankVO.getName());
                bank.setUNN(bankVO.getUNN());
                bankService.updateEntity(bank);


            }


            return new ModelAndView("redirect:/bank-list");

        } catch (DataIntegrityViolationException e) {
            errormessage = "error.duplicate.bank";
        }

        httpSession.removeAttribute("message");
        ModelAndView modelAndView = new ModelAndView("new-bank");
        modelAndView.addObject("bank", bankVO);
        modelAndView.addObject("error", errormessage);

        return modelAndView;

    }

    @RequestMapping(value = "/insertBank", method = RequestMethod.POST, params = "action2")
    public ModelAndView cancelInsertUser(HttpServletRequest request) {


        return new ModelAndView("redirect:/bank-list");


    }

    @RequestMapping(value = "/insertBank", method = RequestMethod.GET)
    public ModelAndView insertUserLang(@ModelAttribute BankVO bankVO, Locale locale, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("new-bank");
        modelAndView.addObject("bank", bankVO);

        return modelAndView;
    }

    @RequestMapping(value = "/bank-list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView userList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("bank-list");
        modelAndView.addObject("command", "bank-list");
        List<Bank> userList = new ArrayList<Bank>();

        Object name = request.getAttribute("bankName");

        if (name != null) {
            userList = bankService.searchEntityByName(name.toString());
        } else {
            name = request.getParameter("bankName");
            if (name != null) {
                userList = bankService.searchEntityByName(name.toString());
            } else {
                userList = bankService.getAllEntities();
            }
        }

        if (userList != null) {
            List<BankVO> userVOList = new ArrayList<BankVO>();

            for (Bank bank : userList) {
                userVOList.add(bank.createBankVO());
            }

            if (userVOList != null)
                modelAndView.addObject("bankList", userVOList);
        }


        return modelAndView;
    }

    @RequestMapping(value = "/editbank", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editUser(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("new-bank");
        modelAndView.addObject("command", "bank-list");
        Bank bank;


        Object name = request.getAttribute("bankID");

        if (name != null) {
            bank = bankService.getEntity(new Integer(name.toString()));
        } else {
            name = request.getParameter("bankID");
            bank =bankService.getEntity(new Integer(name.toString()));
        }

        modelAndView.addObject("bank", bank.createBankVO());


        return modelAndView;
    }


    @RequestMapping(value = "/deletebank", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView deleteUser(HttpServletRequest request, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("redirect:/bank-list");
        modelAndView.addObject("command", "bank-list");

        Object name = request.getAttribute("bankID");

        if (name == null) {
            name = request.getParameter("bankID");

        }

        bankService.deleteEntity(new Integer(name.toString()));
        httpSession.setAttribute("message", "bank.delete.success");

        return modelAndView;
    }


}
