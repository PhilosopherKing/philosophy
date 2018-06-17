package com.example.philosophy.controllers;


import com.example.philosophy.models.Sage;
import com.example.philosophy.models.data.SageDao;
import com.example.philosophy.models.data.WisdomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequestMapping("sage")
public class SageController {

    @Autowired
    SageDao sageDao;

    @Autowired
    WisdomDao wisdomDao;


    // Request path: /sage
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("sages", sageDao.findAll());
        model.addAttribute("title", "Sages");
        return "sage/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addForm (Model model) {
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

    @RequestMapping(value = "upload-file", method = RequestMethod.GET)
    public String uploadFileForm(Model model) {
        model.addAttribute("title", "Upload a new file");
        model.addAttribute("files", wisdomDao.findAll());
        return "sage/upload";
    }

    @RequestMapping(value = "upload-file", method = RequestMethod.POST)
    public String uploadFile(Model model, @RequestParam("file") MultipartFile file) {
        model.addAttribute("title", "Upload a new file");

        // do something with 'file'
        model.addAttribute("file", file);
        wisdomDao.save(file);

        // Redirect to upload page
        model.addAttribute("files", wisdomDao.findAll());
        return "redirect:/sage/upload-file";
    }

}
