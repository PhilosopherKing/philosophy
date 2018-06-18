package com.example.philosophy.controllers;

import com.example.philosophy.models.Knowledge;
import com.example.philosophy.models.data.KnowledgeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("knowledge")
public class KnowledgeController {

    @Autowired
    KnowledgeDao knowledgeDao;

    // Request path: /knowledge
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("knowledge", knowledgeDao.findAll());
        // model.addAttribute("title", "Knowledge");
        return "knowledge/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddKnowledgeForm(Model model) {
            // model.addAttribute("title", "Add Knowledge");
            model.addAttribute(new Knowledge());
            return "knowledge/add";
        }

        @RequestMapping(value = "add", method = RequestMethod.POST)
        public String processAddKnowledgeForm(@ModelAttribute @Valid Knowledge newKnowledge,
                                              Errors errors, Model model) {

            if (errors.hasErrors()) {
                // model.addAttribute("title", "Add Knowledge");
                return "knowledge/add";
            }
            else {
                model.addAttribute("knowledge", newKnowledge);
                knowledgeDao.save(newKnowledge);
                return "redirect:";
            }
        }

    }
