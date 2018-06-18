package com.example.philosophy.controllers;


import com.example.philosophy.models.Sage;
import com.example.philosophy.models.data.SageDao;
import com.example.philosophy.models.data.wisdom.StorageFileNotFoundException;
import com.example.philosophy.models.data.WisdomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.stream.Collectors;

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
       // model.addAttribute("title", "Sages");
        return "sage/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addForm (Model model) {
        // model.addAttribute("title", "Add a new Sage");
        model.addAttribute(new Sage());
        return "sage/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Sage newSage, Errors errors) {

        if (errors.hasErrors()) {
            // model.addAttribute("title", "Add a new Sage");
            return "sage/add";
        } else {
            model.addAttribute("sage", newSage);
            sageDao.save(newSage);
            return "redirect:";
        }
    }

    @RequestMapping(value = "upload-file", method = RequestMethod.GET)
    public String listUploadedFiles(Model model) throws IOException {

        // model.addAttribute("files", wisdomDao.loadAll());
        model.addAttribute("files", wisdomDao.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(SageController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        // model.addAttribute("title", "Upload a New File");

        return "sage/upload";
    }

    @RequestMapping(value = "/files/{filename:.+}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = wisdomDao.loadAsResource(filename);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @RequestMapping(value = "upload-file", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        wisdomDao.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/sage/upload-file";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
