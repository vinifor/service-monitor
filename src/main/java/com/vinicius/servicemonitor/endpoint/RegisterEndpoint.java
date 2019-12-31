/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinicius.servicemonitor.endpoint;

import com.vinicius.servicemonitor.ServiceMonitor;
import com.vinicius.servicemonitor.model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vinif
 */
@RestController
@RequestMapping("register")
public class RegisterEndpoint {

    @Autowired
    private ServiceMonitor serviceMonitor;
    
    @PostMapping
    public void register(@RequestBody Service service){
        service.setGraceTimeLeft(service.getGraceTime());
        serviceMonitor.addServiceMonitor(service);
    }
}
