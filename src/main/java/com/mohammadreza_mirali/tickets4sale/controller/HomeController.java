package com.mohammadreza_mirali.tickets4sale.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String index() {
        return "index.html";
    }
}
