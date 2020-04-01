package com.boai.springbootrbq.Controller;

import com.boai.springbootrbq.Model.User;
import com.boai.springbootrbq.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService uService;


    @Autowired
    public UserController(UserService uService) {
        this.uService = uService;
    }

    @GetMapping("/getUser")
    @ResponseBody
    public User getUser() {
        return uService.getUserById(1);
    }
}
