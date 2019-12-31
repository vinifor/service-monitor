/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinicius.servicemonitor;

import com.vinicius.servicemonitor.model.Service;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.stereotype.Controller;

/**
 * @author vinif
 */
@Controller
public class ServiceMonitor {


    public void addServiceMonitor(Service service) {
        System.out.println(service);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (checkService(service)) {
                    service.setGraceTimeLeft(service.getGraceTime());
                    System.out.println(service.getHost() + ":" + service.getPort() + " is running");
                } else {
                    LocalTime now = LocalTime.now();
                    if(now.isAfter(service.getStart()) && now.isBefore(service.getEnd())){
                        System.out.println(service.getHost() + ":" + service.getPort() + " is not running. Planned service outage");
                    }else{
                        if (service.getGraceTimeLeft().compareTo(LocalTime.of(0, 0, 0)) <= 0) {
                            System.out.println(service.getHost() + ":" + service.getPort() + " is not running. Sending notification");
                        } else {
                            service.setGraceTimeLeft(service.getGraceTimeLeft().minus(service.getPollingFrequency(), ChronoUnit.SECONDS));
                            System.out.println(service.getHost() + ":" + service.getPort() + " is not running. Grace time: " + service.getGraceTimeLeft());
                        }
                    }
                }
            }
        }, service.getPollingFrequency() * 1000, service.getPollingFrequency() * 1000);
    }

    private boolean checkService(Service service) {
        try (Socket s = new Socket(service.getHost(), service.getPort())) {
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

}
