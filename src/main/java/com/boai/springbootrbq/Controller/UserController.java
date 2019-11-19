package com.boai.springbootrbq.Controller;

import com.boai.springbootrbq.Model.User;
import com.boai.springbootrbq.Service.UserService;
import com.boai.springbootrbq.Util.LogUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService uService;
    private RabbitTemplate rbqTemplate;

    @Autowired
    public UserController(UserService uService,RabbitTemplate rbqTemplate) {
        this.uService = uService;
        this.rbqTemplate = rbqTemplate;
    }

    @GetMapping("/getUser")
    @ResponseBody
    public User getUser() throws InterruptedException {
//        rbqTemplate.convertAndSend("springbootrbq","test content");
        for (int i = 0; i < 10000; i++) {
            LogUtil.info("tgisfhisidh");
            Thread.sleep(100);
        }
        return new User();
    }
}
