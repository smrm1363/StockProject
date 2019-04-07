package com.mohammadreza_mirali.stockApplication.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This is for returning the home page of our web application
 */
@org.springframework.stereotype.Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String index() {
        return "index.html";
    }
}
