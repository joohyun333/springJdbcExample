package com.example.springjdbc.service;

import com.example.springjdbc.config.ClientDatabase;
import com.example.springjdbc.config.ClientDatabaseContextHolder;
import com.example.springjdbc.dao.ClientMapper;
import com.example.springjdbc.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired ClientMapper clientMapper;

    public void getClientName(ClientDatabase clientDb) {
        ClientDatabaseContextHolder.set(clientDb);
        List<Employee> clientName = this.clientMapper.selectClient();
        System.out.println(clientName);
        ClientDatabaseContextHolder.clear();
    }
}
