package com.vinicius.servicemonitor;

import static org.assertj.core.api.Assertions.assertThat;

import com.vinicius.servicemonitor.model.Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServiceMonitorTests {

    @Autowired
    ServiceMonitor serviceMonitor;
        
    @Test
    void serviceShouldBeOnline() {
        assertThat(serviceMonitor.checkService(Service.builder()
                .host("localhost")
                .port(5432)
                .build())).isTrue();
    }

    @Test
    void serviceShouldBeOffline() {
        assertThat(serviceMonitor.checkService(Service.builder()
                .host("localhost")
                .port(8081)
                .build())).isFalse();
    }
}
