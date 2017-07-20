package com.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Roy on 16/07/2017.
 */
@Controller
public class MainController {
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/denied")
    public String denied(){
        return "denied";
    }

    @RequestMapping("/404")
    public String notFound(){
        return "not-found";
    }
}
