package com.example.springjdbc.controller;

import com.example.springjdbc.config.ClientDatabase;
import com.example.springjdbc.dto.Employee;
import com.example.springjdbc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    ClientService service;

    @RequestMapping("/a")
    public void clientRequest(){
        service.getClientName(ClientDatabase.CLIENT_A);
    }
}
