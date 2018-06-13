package com.example.philosophy.controllers;


import com.example.philosophy.models.Sage;
import com.example.philosophy.models.data.SageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("sage")
public class SageController {

    @Autowired
    SageDao sageDao;

    // Request path: /sage
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("sages", sageDao.findAll());
        model.addAttribute("title", "Sages");
        return "sage/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add a new Sage");
        model.addAttribute(new Sage());
        return "sage/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Sage newSage, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a new Sage");
            return "sage/add";
        } else {
            model.addAttribute("sage", newSage);
            sageDao.save(newSage);
            return "redirect:";
        }
    }

}
