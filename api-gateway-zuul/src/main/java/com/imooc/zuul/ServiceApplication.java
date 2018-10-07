package com.imooc.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

/**
 * Created by Michael on 2017/10/28.
 */
@SpringBootApplication
@EnableZuulServer
public class ServiceApplication {

    public static void main(String args[]) {
        SpringApplication.run(ServiceApplication.class, args);
    }
}
