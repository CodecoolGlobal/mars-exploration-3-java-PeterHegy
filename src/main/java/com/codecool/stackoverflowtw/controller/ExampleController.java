package com.codecool.stackoverflowtw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class ExampleController {

    @GetMapping
    public String index(Model model) {
        String html = "<p>hello there!</p>";

        model.addAttribute("questions", "hello");
        return "all";
    }

    @GetMapping("/path/{name}")
    public String exampleWithPathVariable(@PathVariable String name, Model model) {


        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/param")
    public String exampleWithRequestParam(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }
}

