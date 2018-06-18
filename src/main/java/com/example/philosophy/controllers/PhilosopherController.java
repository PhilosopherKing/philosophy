package com.example.philosophy.controllers;

import com.example.philosophy.models.Philosopher;
import com.example.philosophy.models.data.PhilosopherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("philosopher")
public class PhilosopherController {

    @Autowired
    PhilosopherDao philosopherDao;

    // Request path: /philosopher
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("philosophers", philosopherDao.findAll());
        // model.addAttribute("title", "Philosophers");
        return "philosopher/index";
    }

    @RequestMapping(value = "sign-up", method = RequestMethod.GET)
    public String add(Model model) {
        // model.addAttribute("title", "Philosopher Sign-up");
        model.addAttribute(new Philosopher());
        return "philosopher/add";
    }

    @RequestMapping(value = "sign-up", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute Philosopher newPhilosopher, String verify) {

        if(verify.equals(newPhilosopher.getPassword())) {
            model.addAttribute("philosopher", newPhilosopher);
            philosopherDao.save(newPhilosopher);
            return "redirect:";
        } else {
            model.addAttribute("username", newPhilosopher.getUsername());
            model.addAttribute("email", newPhilosopher.getEmail());
            // model.addAttribute("title", "Philosopher Sign-up");
            model.addAttribute("message", "Passwords do not match");
            return "philosopher/add";
        }
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model) {
       // model.addAttribute("title", "Philosopher Login");
        return "philosopher/login";
    }

}
