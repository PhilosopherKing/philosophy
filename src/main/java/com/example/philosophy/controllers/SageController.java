package com.example.philosophy.controllers;

import com.example.philosophy.models.Branch;
import com.example.philosophy.models.Sage;
import com.example.philosophy.models.Wisdom;
import com.example.philosophy.models.data.BranchDao;
import com.example.philosophy.models.data.SageDao;
import com.example.philosophy.models.data.WisdomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    BranchDao branchDao;

    // Request path: /sage
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("sages", sageDao.findAll());
        model.addAttribute("title", "Sages");
        return "sage/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addForm (Model model, @CookieValue(value = "philosopher", defaultValue = "none") String username) {

        if(username.equals("PhilosopherKing")) {

            model.addAttribute("title", "Add a new Sage");
            model.addAttribute(new Sage());
            return "sage/add";

        } else {

            return "redirect:";
        }
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

    @RequestMapping(value = "add-wisdom", method = RequestMethod.GET)
    public String listUploadedFiles(Model model, @CookieValue(value = "philosopher", defaultValue = "none") String username,
                                    RedirectAttributes redirectAttributes) throws IOException {

        if(username.equals("PhilosopherKing")) {

            model.addAttribute("title", "Add Wisdom");
            model.addAttribute(new Wisdom());
            model.addAttribute("sages", sageDao.findAll());
            model.addAttribute("branch", branchDao.findAll());
            model.addAttribute("sources", wisdomDao.findAll());

            return "sage/add-wisdom";

        } else {

            return "redirect:";
        }
    }

    @RequestMapping(value = "add-wisdom", method = RequestMethod.POST)
    public String handleFileUpload(@ModelAttribute @Valid Wisdom newWisdom, @RequestParam int sageId,
                                   @RequestParam int branchId, RedirectAttributes redirectAttributes) throws IOException {

        Sage sage = sageDao.findById(sageId).orElse(null);

        Branch branch = branchDao.findById(branchId).orElse(null);

        newWisdom.setSage(sage);
        newWisdom.setBranch(branch);

        wisdomDao.save(newWisdom);

        redirectAttributes.addFlashAttribute("message",
                "You successfully added " + newWisdom.getName() + "!");

        return "redirect:/sage/add-wisdom";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String sagePage(Model model, @PathVariable int id){

        Sage sage = sageDao.findById(id).orElse(null);
        List<Wisdom> philosophies = sage.getPhilosophies();

        model.addAttribute("philosophies", philosophies);
        model.addAttribute("title", sage.getName());

        return "sage/view";
    }

}


