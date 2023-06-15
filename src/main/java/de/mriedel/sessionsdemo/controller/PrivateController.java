package de.mriedel.sessionsdemo.controller;

import de.mriedel.sessionsdemo.entity.SessionsUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrivateController {
    @GetMapping("/private")
    public String privatePage(Model model) {
        SessionsUser user = (SessionsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "private";
    }
}
