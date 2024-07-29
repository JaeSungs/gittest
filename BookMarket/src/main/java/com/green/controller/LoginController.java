package com.green.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")  
    public String login() {  
        return "login";  
    }  

    @GetMapping("/loginfailed")  
    public String loginerror(Model model) {
    	//loginfailed 가 요청 되면 Model 에 키값 : error , value :true 값을 담아 login.jsp 로 return 된다.
        model.addAttribute("error", "true");  
        return "login"; 
    } 
    
    @GetMapping("/logout")
    public String logout(Model model) {  
        return "login";  
    } 
}