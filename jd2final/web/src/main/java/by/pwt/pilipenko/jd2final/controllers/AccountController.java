package by.pwt.pilipenko.jd2final.controllers;

import by.pwt.pilipenko.jd2final.dao.VO.AccountVO;
import by.pwt.pilipenko.jd2final.dao.VO.AgreementVO;
import by.pwt.pilipenko.jd2final.dao.entities.Account;
import by.pwt.pilipenko.jd2final.dao.entities.Agreement;
import by.pwt.pilipenko.jd2final.dao.entities.Currency;
import by.pwt.pilipenko.jd2final.services.interfaces.*;
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
public class AccountController {

    @Autowired
    IUserService userService;
    @Autowired
    IUserRoleService userRoleService;
    @Autowired
    IAccountService accountService;
    @Autowired
    IAgreementService agreementService;
    @Autowired
    ICurrencyService currencyService;

    @Autowired
    private MessageSource messageSource;


    @RequestMapping(value = "/insertAccount", method = RequestMethod.POST, params = "action1")
    public ModelAndView insertAccount(@ModelAttribute AccountVO accountVO, Locale locale, HttpSession httpSession, HttpServletRequest request) {

        String errormessage = null;

        try {
            if (accountVO != null) {
                Account account = accountService.getEntity(accountVO.getId());
                httpSession.setAttribute("message", "account.update.success");
                if (account == null) {
                    account = new Account();



                    httpSession.setAttribute("message", "account.add.success");
                }

                account.setNumber(accountVO.getNumber());
                account.setAmount(new Double(accountVO.getAmount()));
                account.setAgreement(agreementService.getEntity(accountVO.getAgreementID()));
                account.setCurrency(currencyService.getEntity(accountVO.getCurrencyID()));
                accountService.updateEntity(account);

            }

            return new ModelAndView("redirect:/account-list");

        } catch (DataIntegrityViolationException e) {
            errormessage = "error.duplicate.account";
        }

        httpSession.removeAttribute("message");
        ModelAndView modelAndView = new ModelAndView("new-account");
        modelAndView.addObject("command", "account-list");

        List<Agreement> agreements = agreementService.getAllEntities();
        if (agreements != null) {
            List<AgreementVO> agreementvos = new ArrayList<AgreementVO>();
            for (Agreement agreement : agreements
                    ) {
                agreementvos.add(agreement.createAgreementVO());
            }
            modelAndView.addObject("agreements",agreementvos);

        }


        modelAndView.addObject("currencies",currencyService.getAllEntities());
        modelAndView.addObject("account",accountVO);
        modelAndView.addObject("error",errormessage);

        return modelAndView;

}

    @RequestMapping(value = "/insertAccount", method = RequestMethod.POST, params = "action2")
    public ModelAndView cancelInsertAccount(HttpServletRequest request) {

        return new ModelAndView("redirect:/account-list");


    }

    @RequestMapping(value = "/insertAccount", method = RequestMethod.GET)
    public ModelAndView insertAccountLang(@ModelAttribute AccountVO accountVO, Locale locale, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("new-account");
        modelAndView.addObject("command", "new-account");
        List<Agreement> agreements = agreementService.getAllEntities();
        if (agreements != null) {
            List<AgreementVO> agreementvos = new ArrayList<AgreementVO>();
            for (Agreement agreement : agreements
                    ) {
                agreementvos.add(agreement.createAgreementVO());
            }
            modelAndView.addObject("agreements",agreementvos);

        }
        modelAndView.addObject("currencies", currencyService.getAllEntities());
        modelAndView.addObject("account", accountVO);

        return modelAndView;
    }

    @RequestMapping(value = "/account-list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView accountList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("account-list");
        modelAndView.addObject("command", "account-list");
        List<Account> accountList = new ArrayList<Account>();

        Object pg = request.getAttribute("pg");
        Object rpp = request.getAttribute("rpp");
        String name = (String) request.getAttribute("accountName");
        long count = 0;

        if (pg == null) {
            pg = request.getParameter("pg");
        }
        if (pg == null || pg.equals("")) {
            pg = 1;
        }

        if (rpp == null) {
            rpp = request.getParameter("rpp");
        }
        if (rpp == null || rpp.equals("")) {
            rpp = 1;
        }

        if (name != null && !name.equals("")) {
            accountList = accountService.searchEntityByNameWithPagination(name.toString(), new Integer(pg.toString()), new Integer(rpp.toString()));
            count = accountService.getCountEntityByNameWithPagination(name.toString());
            request.setAttribute("accountName", name);
        } else {
            name = request.getParameter("accountName");
            if (name != null && !name.equals("")) {
                accountList = accountService.searchEntityByNameWithPagination(name.toString(), new Integer(pg.toString()), new Integer(rpp.toString()));
                count = accountService.getCountEntityByNameWithPagination(name.toString());

                request.setAttribute("accountName", name);
            } else {
                accountList = accountService.getAllEntitiesWithPagination(new Integer(pg.toString()), new Integer(rpp.toString()));
                count = accountService.getCountAllEntitiesWithPagination();
            }
        }


        if (accountList != null) {
            List<AccountVO> accountVOList = new ArrayList<>();

            for (Account account : accountList) {
                accountVOList.add(account.createAccountVO());
            }

            modelAndView.addObject("accountList", accountVOList);
        }

        modelAndView.addObject("pg", new Integer(pg.toString()));
        modelAndView.addObject("rpp", new Integer(rpp.toString()));
        modelAndView.addObject("recordsCount", count);
        modelAndView.addObject("maxPage", (int) Math.ceil((float) count / new Integer(rpp.toString())));

        return modelAndView;
    }

    @RequestMapping(value = "/editaccount", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editAccount(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("new-account");
        modelAndView.addObject("command", "account-list");
        Account account;


        Object name = request.getAttribute("accountID");

        if (name != null) {
            account = accountService.getEntity(new Integer(name.toString()));
        } else {
            name = request.getParameter("accountID");
            account = accountService.getEntity(new Integer(name.toString()));
        }

        modelAndView.addObject("account", account.createAccountVO());

        List<Agreement> agreements = agreementService.getAllEntities();
        if (agreements != null) {
            List<AgreementVO> agreementvos = new ArrayList<AgreementVO>();
            for (Agreement agreement : agreements
                    ) {
                agreementvos.add(agreement.createAgreementVO());
            }
            modelAndView.addObject("agreements",agreementvos);

        }
        modelAndView.addObject("currencies", currencyService.getAllEntities());

        return modelAndView;
    }


    @RequestMapping(value = "/deleteaccount", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView deleteAccount(HttpServletRequest request, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("redirect:/account-list");
        modelAndView.addObject("command", "account-list");

        Object name = request.getAttribute("accountID");

        if (name == null) {
            name = request.getParameter("accountID");

        }

        accountService.deleteEntity(new Integer(name.toString()));
        httpSession.setAttribute("message", "account.delete.success");

        return modelAndView;
    }


}
