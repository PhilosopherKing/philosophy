package com.example.philosophy.controllers;

import com.example.philosophy.models.Knowledge;
import com.example.philosophy.models.Philosopher;
import com.example.philosophy.models.data.PhilosopherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("philosopher")
public class PhilosopherController {

    @Autowired
    PhilosopherDao philosopherDao;

    // Request path: /philosopher
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("philosophers", philosopherDao.findAll());
        model.addAttribute("title", "Philosophers");
        return "philosopher/index";
    }

    @RequestMapping(value = "sign-up", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Philosopher Sign-up");
        model.addAttribute(new Philosopher());
        return "philosopher/add";
    }

    @RequestMapping(value = "sign-up", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute Philosopher newPhilosopher, String verify) {

        if(verify.equals(newPhilosopher.getPassword())) {
            model.addAttribute("philosopher", newPhilosopher);
            philosopherDao.save(newPhilosopher);
            return "redirect:/philosopher/login";
        } else {
            model.addAttribute("username", newPhilosopher.getUsername());
            model.addAttribute("email", newPhilosopher.getEmail());
            model.addAttribute("title", "Philosopher Sign-up");
            model.addAttribute("message", "Passwords do not match");
            return "philosopher/add";
        }
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model, @CookieValue(value = "philosopher", defaultValue = "none") String username) {

        if(username.equals("none")) {
            model.addAttribute("title", "Philosopher Login");
            model.addAttribute(new Philosopher());
            return "philosopher/login";
        }
        return "redirect:";

    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String loginPhilosopher(Model model, @ModelAttribute Philosopher philosopher, HttpServletResponse response) {

        List<Philosopher> u = philosopherDao.findByUsername(philosopher.getUsername());

        if(u.isEmpty()) {
            model.addAttribute("message", "Invalid Username");
            model.addAttribute("title", "Philosopher Login");
            return "philosopher/login";
        }

        Philosopher loggedIn = u.get(0);

        if(loggedIn.getPassword().equals(philosopher.getPassword())) {
            Cookie c = new Cookie("philosopher", philosopher.getUsername());
            c.setPath("/");
            response.addCookie(c);
            return "redirect:";
        } else {
            model.addAttribute("message", "Invalid Password");
            model.addAttribute("title", "Philosopher Login");
            return "philosopher/login";
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String PhilosopherPage(Model model, @PathVariable int id){

        Philosopher philosopher = philosopherDao.findById(id).orElse(null);
        List<Knowledge> philosophies = philosopher.getPhilosophies();

        model.addAttribute("philosophies", philosophies);
        model.addAttribute("title", philosopher.getUsername());

        return "philosopher/view";
    }

    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie c : cookies) {
                c.setMaxAge(0);
                c.setPath("/");
                response.addCookie(c);
            }
        }
        return "philosopher/login";
    }

}
