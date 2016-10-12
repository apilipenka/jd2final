package by.pwt.pilipenko.jd2final.controllers;

import by.pwt.pilipenko.jd2final.dao.VO.ExchangeRateVO;
import by.pwt.pilipenko.jd2final.dao.entities.ExchangeRate;
import by.pwt.pilipenko.jd2final.services.interfaces.ICurrencyService;
import by.pwt.pilipenko.jd2final.services.interfaces.IExchangeRateService;
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
public class ExchangeRateController {

    @Autowired
    IExchangeRateService exchangeRateService;
    @Autowired
    ICurrencyService currencyService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/insertExchangeRate", method = RequestMethod.POST, params = "action1")
    public ModelAndView insertUser(@ModelAttribute ExchangeRateVO exchangeRateVO, Locale locale, HttpSession httpSession, HttpServletRequest request) {

        String errormessage = null;

        try {
            if (exchangeRateVO != null) {
                ExchangeRate exchangeRate = exchangeRateService.getEntity(exchangeRateVO.getId());
                httpSession.setAttribute("message", "exchangerate.update.success");
                if (exchangeRate == null) {
                    exchangeRate = new ExchangeRate();


                    httpSession.setAttribute("message", "exchangeRate.add.success");
                }


                exchangeRate.setRate(exchangeRateVO.getRate());
                DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                Date rateDate = format.parse(exchangeRateVO.getRateDate());
                exchangeRate.setRateDate(rateDate);
                exchangeRate.setCurrency(currencyService.getEntity(exchangeRateVO.getCurrencyID()));
                exchangeRate.setTargetCurrency(currencyService.getEntity(exchangeRateVO.getTargetCurrencyID()));

                exchangeRateService.updateEntity(exchangeRate);


            }


            return new ModelAndView("redirect:/exchangeRate-list");

        } catch (ParseException e) {
            errormessage = "error.parseexception";
        } catch (DataIntegrityViolationException e) {
            errormessage = "error.duplicate.exchangeRate";
        }

        httpSession.removeAttribute("message");
        ModelAndView modelAndView = new ModelAndView("new-exchangeRate");
        modelAndView.addObject("exchangeRate", exchangeRateVO);
        modelAndView.addObject("currencies", currencyService.getAllEntities());
        modelAndView.addObject("error", errormessage);

        return modelAndView;

    }

    @RequestMapping(value = "/insertExchangeRate", method = RequestMethod.POST, params = "action2")
    public ModelAndView cancelInsertUser(HttpServletRequest request) {


        return new ModelAndView("redirect:/exchangeRate-list");


    }

    @RequestMapping(value = "/insertExchangeRate", method = RequestMethod.GET)
    public ModelAndView insertUserLang(@ModelAttribute ExchangeRateVO exchangeRateVO, Locale locale, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("new-exchangeRate");
        modelAndView.addObject("exchangeRate", exchangeRateVO);

        modelAndView.addObject("currencies", currencyService.getAllEntities());


        return modelAndView;
    }

    @RequestMapping(value = "/exchangeRate-list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView userList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("exchangeRate-list");
        modelAndView.addObject("command", "exchangeRate-list");
        List<ExchangeRate> userList = new ArrayList<ExchangeRate>();

        Object name = request.getAttribute("exchangeRateName");

        if (name != null) {
            userList = exchangeRateService.searchEntityByName(name.toString());
        } else {
            name = request.getParameter("exchangeRateName");
            if (name != null) {
                userList = exchangeRateService.searchEntityByName(name.toString());
            } else {
                userList = exchangeRateService.getAllEntities();
            }
        }

        if (userList != null) {
            List<ExchangeRateVO> userVOList = new ArrayList<ExchangeRateVO>();

            for (ExchangeRate exchangeRate : userList) {
                userVOList.add(exchangeRate.createExchangeRateVO());
            }

            if (userVOList != null)
                modelAndView.addObject("exchangeRateList", userVOList);
        }


        return modelAndView;
    }

    @RequestMapping(value = "/editexchangeRate", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editUser(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("new-exchangeRate");
        modelAndView.addObject("command", "exchangeRate-list");
        ExchangeRate exchangeRate;


        Object name = request.getAttribute("exchangeRateID");

        if (name != null) {
            exchangeRate = exchangeRateService.getEntity(new Integer(name.toString()));
        } else {
            name = request.getParameter("exchangeRateID");
            exchangeRate = exchangeRateService.getEntity(new Integer(name.toString()));
        }

        modelAndView.addObject("exchangeRate", exchangeRate.createExchangeRateVO());
        modelAndView.addObject("currencies", currencyService.getAllEntities());

        return modelAndView;
    }


    @RequestMapping(value = "/deleteexchangeRate", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView deleteUser(HttpServletRequest request, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("redirect:/exchangeRate-list");
        modelAndView.addObject("command", "exchangeRate-list");

        Object name = request.getAttribute("exchangeRateID");

        if (name == null) {
            name = request.getParameter("exchangeRateID");

        }

        exchangeRateService.deleteEntity(new Integer(name.toString()));
        httpSession.setAttribute("message", "exchangeRate.delete.success");

        return modelAndView;
    }


}
