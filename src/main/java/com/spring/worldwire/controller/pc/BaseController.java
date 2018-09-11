package com.spring.worldwire.controller.pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Locale;

@Controller
public class BaseController {

    @Autowired
    private MessageSource messageSource;


    public void navigateMessage(Model model){

        Locale locale = LocaleContextHolder.getLocale();

        model.addAttribute("home", messageSource.getMessage("home", null, locale));
        model.addAttribute("requestlist", messageSource.getMessage("requestlist", null, locale));
        model.addAttribute("productdesc", messageSource.getMessage("productdesc", null, locale));
        model.addAttribute("aboutus", messageSource.getMessage("aboutus", null, locale));
        model.addAttribute("helpcenter", messageSource.getMessage("helpcenter", null, locale));
    }
}
