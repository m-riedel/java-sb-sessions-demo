package de.mriedel.sessionsdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrivateController {
    @GetMapping("/private")
    public String privatePage() {
        return "private";
    }
}
