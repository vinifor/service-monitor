/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinicius.servicemonitor.model;

import java.time.LocalTime;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author vinif
 */
@Data
@Builder(builderClassName = "Builder")
public class Service {

    private String host;
    private int port;
    private int pollingFrequency;
    private LocalTime start;
    private LocalTime end;
    private LocalTime graceTime;
    private LocalTime graceTimeLeft;

}
