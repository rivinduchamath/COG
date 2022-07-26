package com.cloudofgoods.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @RequestMapping(value = "/registration/client", method = RequestMethod.POST)
    public String registerClient(){
        return "register done";
    }

    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public String blockClient(){
        return "client";

    }


}
