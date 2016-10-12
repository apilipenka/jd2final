package by.pwt.pilipenko.jd2final.controllers;

import by.pwt.pilipenko.jd2final.dao.VO.CommandVO;
import by.pwt.pilipenko.jd2final.dao.entities.Command;
import by.pwt.pilipenko.jd2final.services.interfaces.ICommandService;
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
public class CommandController {

    @Autowired
    ICommandService commandService;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/insertCommand", method = RequestMethod.POST, params = "action1")
    public ModelAndView insertCommand(@ModelAttribute CommandVO commandVO, Locale locale, HttpSession httpSession, HttpServletRequest request) {

        String errormessage = null;

        try {
            if (commandVO != null) {
                Command command = commandService.getEntity(commandVO.getId());
                httpSession.setAttribute("message", "command.update.success");
                if (command == null) {
                    command = new Command();


                    httpSession.setAttribute("message", "command.add.success");
                }

                command.setCommand(commandVO.getCommandp());
                command.setUrl(commandVO.getUrl());
                command.setLabel(commandVO.getLabel());
                command.setComment(commandVO.getComment());
                commandService.updateEntity(command);


            }


            return new ModelAndView("redirect:/command-list");

        } catch (DataIntegrityViolationException e) {
            errormessage = "error.duplicate.command";
        }

        httpSession.removeAttribute("message");
        ModelAndView modelAndView = new ModelAndView("new-command");
        modelAndView.addObject("command", commandVO);
        modelAndView.addObject("error", errormessage);

        return modelAndView;

    }

    @RequestMapping(value = "/insertCommand", method = RequestMethod.POST, params = "action2")
    public ModelAndView cancelInsertCommand(HttpServletRequest request) {


        return new ModelAndView("redirect:/command-list");


    }

    @RequestMapping(value = "/insertCommand", method = RequestMethod.GET)
    public ModelAndView insertCommandLang(@ModelAttribute CommandVO commandVO, Locale locale, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("new-command");
        modelAndView.addObject("command", commandVO);

        return modelAndView;
    }

    @RequestMapping(value = "/command-list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView commandList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("command-list");
        modelAndView.addObject("command", "command-list");
        List<Command> commandList = new ArrayList<Command>();

        Object name = request.getAttribute("commandName");

        if (name != null) {
            commandList = commandService.searchEntityByName(name.toString());
        } else {
            name = request.getParameter("commandName");
            if (name != null) {
                commandList = commandService.searchEntityByName(name.toString());
            } else {
                commandList = commandService.getAllEntities();
            }
        }

        if (commandList != null) {
            List<CommandVO> commandVOList = new ArrayList<CommandVO>();

            for (Command command : commandList) {
                commandVOList.add(command.createCommandVO());
            }

            if (commandVOList != null)
                modelAndView.addObject("commandList", commandVOList);
        }


        return modelAndView;
    }

    @RequestMapping(value = "/editcommand", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editCommand(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("new-command");
        modelAndView.addObject("command", "command-list");
        Command command;


        Object name = request.getAttribute("commandID");

        if (name != null) {
            command = commandService.getEntity(new Integer(name.toString()));
        } else {
            name = request.getParameter("commandID");
            command = commandService.getEntity(new Integer(name.toString()));
        }

        modelAndView.addObject("command", command.createCommandVO());


        return modelAndView;
    }


    @RequestMapping(value = "/deletecommand", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView deleteCommand(HttpServletRequest request, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("redirect:/command-list");
        modelAndView.addObject("command", "command-list");

        Object name = request.getAttribute("commandID");

        if (name == null) {
            name = request.getParameter("commandID");

        }

        commandService.deleteEntity(new Integer(name.toString()));
        httpSession.setAttribute("message", "command.delete.success");

        return modelAndView;
    }


}
