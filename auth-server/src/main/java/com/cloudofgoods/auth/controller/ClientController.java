package com.cloudofgoods.auth.controller;

import com.cloudofgoods.auth.dto.ClientDTO;
import com.cloudofgoods.auth.service.ClientDetailService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ClientController {

     final ClientDetailService clientDetailService;

    @RequestMapping(value = "/registration/client", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO registerClient(@RequestBody ClientDTO clientDTO){
        System.out.printf("dddddddddddddddddddddd" + clientDTO);
        return clientDetailService.saveClient(clientDTO);
    }

    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public String blockClient(){
        return "client";

    }


}
