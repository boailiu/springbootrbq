package com.boai.springbootrbq.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boai.springbootrbq.Model.Inner;
import com.boai.springbootrbq.Model.Person;
import com.boai.springbootrbq.Model.User;
import com.boai.springbootrbq.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

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

    @GetMapping("/getPerson")
    public String getPerson() throws JsonProcessingException {
        final Person person = new Person();
        final Inner inner = new Inner();
        inner.setId(2);
        inner.setContent("yyyyssss");
        final List<Inner> inners = Collections.singletonList(inner);
        person.setId(1);
        person.setName("ssssyyyy");
        person.setInners(inners);
        final String s1 = JSON.toJSONString(person);
        System.out.println(s1);
        final String s = JSONObject.toJSONString(person);
        System.out.println(s);
        return s;
    }
}
