package com.daniyel.si1lab04.ws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Daniyel on 01/02/2017.
 */

@Controller
public class HomeController {

    @RequestMapping("/")
    public String goToHomePage() {
        return "index";
    }
}
