package com.home.basic.rabbitmq.controller;

import com.home.basic.rabbitmq.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {

    @Autowired
    private ProducerService producerService;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    @ResponseBody
    public String sendMessage() {
        producerService.sendToMessageQueue("I am a spy!");
        return "send ok!";
    }
}
