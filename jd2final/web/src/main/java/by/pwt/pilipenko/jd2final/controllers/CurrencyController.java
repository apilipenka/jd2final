package by.pwt.pilipenko.jd2final.controllers;

import by.pwt.pilipenko.jd2final.dao.entities.Currency;
import by.pwt.pilipenko.jd2final.services.interfaces.ICurrencyService;
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
public class CurrencyController {

    @Autowired
    ICurrencyService currencyService;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/insertCurrency", method = RequestMethod.POST, params = "action1")
    public ModelAndView insertCurrency(@ModelAttribute Currency currencyVO, Locale locale, HttpSession httpSession, HttpServletRequest request) {

        String errormessage = null;

        try {
            if (currencyVO != null) {
                Currency currency = currencyService.getEntity(currencyVO.getId());
                httpSession.setAttribute("message", "currency.update.success");
                if (currency == null) {
                    currency = new Currency();


                    httpSession.setAttribute("message", "currency.add.success");
                }

                currency.setCode(currencyVO.getCode());
                currency.setMnemoCode(currencyVO.getMnemoCode());
                currency.setName(currencyVO.getName());
                currencyService.updateEntity(currency);


            }


            return new ModelAndView("redirect:/currency-list");

        } catch (DataIntegrityViolationException e) {
            errormessage = "error.duplicate.currency";
        }

        httpSession.removeAttribute("message");
        ModelAndView modelAndView = new ModelAndView("new-currency");
        modelAndView.addObject("currency", currencyVO);
        modelAndView.addObject("error", errormessage);

        return modelAndView;

    }

    @RequestMapping(value = "/insertCurrency", method = RequestMethod.POST, params = "action2")
    public ModelAndView cancelInsertCurrency(HttpServletRequest request) {


        return new ModelAndView("redirect:/currency-list");


    }

    @RequestMapping(value = "/insertCurrency", method = RequestMethod.GET)
    public ModelAndView insertCurrencyLang(@ModelAttribute Currency currencyVO, Locale locale, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("new-currency");
        modelAndView.addObject("currency", currencyVO);

        return modelAndView;
    }

    @RequestMapping(value = "/currency-list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView currencyList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("currency-list");
        modelAndView.addObject("currency", "currency-list");
        List<Currency> currencyList = new ArrayList<Currency>();

        Object name = request.getAttribute("currencyName");

        if (name != null) {
            currencyList = currencyService.searchEntityByName(name.toString());
        } else {
            name = request.getParameter("currencyName");
            if (name != null) {
                currencyList = currencyService.searchEntityByName(name.toString());
            } else {
                currencyList = currencyService.getAllEntities();
            }
        }

        if (currencyList != null) {
            modelAndView.addObject("currencyList", currencyList);
        }


        return modelAndView;
    }

    @RequestMapping(value = "/editcurrency", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editCurrency(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("new-currency");
        modelAndView.addObject("currency", "currency-list");
        Currency currency;


        Object name = request.getAttribute("currencyID");

        if (name != null) {
            currency = currencyService.getEntity(new Integer(name.toString()));
        } else {
            name = request.getParameter("currencyID");
            currency = currencyService.getEntity(new Integer(name.toString()));
        }

        modelAndView.addObject("currency", currency);


        return modelAndView;
    }


    @RequestMapping(value = "/deletecurrency", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView deleteCurrency(HttpServletRequest request, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("redirect:/currency-list");
        modelAndView.addObject("currency", "currency-list");

        Object name = request.getAttribute("currencyID");

        if (name == null) {
            name = request.getParameter("currencyID");

        }

        currencyService.deleteEntity(new Integer(name.toString()));
        httpSession.setAttribute("message", "currency.delete.success");

        return modelAndView;
    }


}
