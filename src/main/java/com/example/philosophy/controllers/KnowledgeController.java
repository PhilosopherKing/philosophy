package com.example.philosophy.controllers;

import com.example.philosophy.models.Branch;
import com.example.philosophy.models.Enlightenment;
import com.example.philosophy.models.Knowledge;
import com.example.philosophy.models.Philosopher;
import com.example.philosophy.models.data.BranchDao;
import com.example.philosophy.models.data.EnlightenmentDao;
import com.example.philosophy.models.data.KnowledgeDao;
import com.example.philosophy.models.data.PhilosopherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("knowledge")
public class KnowledgeController {

    @Autowired
    KnowledgeDao knowledgeDao;

    @Autowired
    EnlightenmentDao enlightenmentDao;

    @Autowired
    BranchDao branchDao;

    @Autowired
    PhilosopherDao philosopherDao;

    // Request path: /knowledge
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("knowledge", knowledgeDao.findAll());
        model.addAttribute("philosophers", philosopherDao.findAll());
        model.addAttribute("title", "Knowledge");
        return "knowledge/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddKnowledgeForm(Model model,
                                          @CookieValue(value = "philosopher", defaultValue = "none") String username) {

        if(username.equals("none")) {
            return "redirect:/philosopher/login";
        }

        model.addAttribute("title", "Add Knowledge");
        model.addAttribute("branch", branchDao.findAll());
        model.addAttribute(new Knowledge());
        return "knowledge/add";

        }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddKnowledgeForm(@ModelAttribute @Valid Knowledge newKnowledge,
                                          @RequestParam int branchId,
                                          Errors errors, Model model,
                                          @CookieValue(value = "philosopher", defaultValue = "none") String username) {

        if(username.equals("none")) {
            return "redirect:/philosopher/login";
        }

        Philosopher u = philosopherDao.findByUsername(username).get(0);

        Branch branch = branchDao.findById(branchId).orElse(null);

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Knowledge");
            return "knowledge/add";
        } else {
            model.addAttribute("knowledge", newKnowledge);
            newKnowledge.setPhilosopher(u);
            newKnowledge.setBranch(branch);
            knowledgeDao.save(newKnowledge);
            return "redirect:/{id}";
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String knowledgePage(Model model, @PathVariable int id,
                                @CookieValue(value = "philosopher", defaultValue = "none") String username) {

        if(username.equals("none")) {
            return "redirect:/philosopher/login";
        }

        Knowledge knowledge = knowledgeDao.findById(id).orElse(null);
        List<Enlightenment> comments = knowledge.getComments();

        model.addAttribute("philosophy", knowledge);
        model.addAttribute("enlightenment", comments);
        model.addAttribute("title", knowledge.getName());

        return "knowledge/view";
    }

}
