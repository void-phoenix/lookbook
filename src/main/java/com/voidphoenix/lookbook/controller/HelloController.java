package com.voidphoenix.lookbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String sayHello() {
        return "Привет!";
    }

    @RequestMapping(value = "login")
    public String login(){
        return "login";
    }

}
