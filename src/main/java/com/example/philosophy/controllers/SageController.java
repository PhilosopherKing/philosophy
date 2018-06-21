package com.example.philosophy.controllers;

import com.example.philosophy.models.Sage;
import com.example.philosophy.models.Wisdom;
import com.example.philosophy.models.data.SageDao;
import com.example.philosophy.models.data.WisdomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

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
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("title", "Upload a New File");
        model.addAttribute(new Wisdom());
        model.addAttribute("sages", sageDao.findAll());
        model.addAttribute("files", wisdomDao.findAll());

        return "sage/upload";
    }

    @RequestMapping(value = "upload-file", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile wisdom, @RequestParam int sageId, RedirectAttributes redirectAttributes) throws IOException {

        Wisdom uploadFile = new Wisdom();
        Sage sage = sageDao.findById(sageId).orElse(null);

        uploadFile.setFileName(wisdom.getOriginalFilename());
        uploadFile.setSage(sage);
        // uploadFile.setFile(wisdom.getBytes());
        wisdomDao.save(uploadFile);

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + wisdom.getOriginalFilename() + "!");

        return "redirect:/sage/upload-file";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String category(Model model, @PathVariable int id){

        Sage sage = sageDao.findById(id).orElse(null);
        List<Wisdom> uploads = sage.getUploads();

        model.addAttribute("uploads", uploads);
        model.addAttribute("title", sage.getName());

        return "sage/view";
    }

}


