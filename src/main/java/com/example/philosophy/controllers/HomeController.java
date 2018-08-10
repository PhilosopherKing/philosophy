package com.example.philosophy.controllers;

import com.example.philosophy.models.Branch;
import com.example.philosophy.models.Knowledge;
import com.example.philosophy.models.data.BranchDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    BranchDao branchDao;

    // Request path: /
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("branches", branchDao.findAll());
        model.addAttribute("title", "Philosophy");
        return "branch/index";
    }

    @RequestMapping(value = "add-branch", method = RequestMethod.GET)
    public String displayAddBranchForm(Model model) {
        model.addAttribute("title", "Add a Branch");
        model.addAttribute(new Branch());
        return "branch/add";
    }

    @RequestMapping(value = "add-branch", method = RequestMethod.POST)
    public String processAddBranchForm(@ModelAttribute @Valid Branch newBranch,
                                          Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a Branch");
            return "branch/add";
        }
        else {
            model.addAttribute("branch", newBranch);
            branchDao.save(newBranch);
            return "redirect:";
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String branchPage(Model model, @PathVariable int id){

        Branch branch = branchDao.findById(id).orElse(null);
        List<Knowledge> philosophies = branch.getPhilosophies();

        model.addAttribute("philosophies", philosophies);
        model.addAttribute("title", branch.getName());

        return "branch/view";
    }

}
