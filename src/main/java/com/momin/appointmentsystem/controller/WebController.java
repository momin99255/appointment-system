package com.momin.appointmentsystem.controller;
import org.springframework.stereotype.Controller; import org.springframework.web.bind.annotation.GetMapping;
@Controller public class WebController {
 @GetMapping({"/","/login"}) public String login(){return "login";}
 @GetMapping("/register") public String register(){return "register";}
 @GetMapping({"/dashboard","/doctors"}) public String dashboard(){return "dashboard";}
}
