package ru.hogwarts.school1.controller;

import liquibase.pro.packaged.S;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/name")
public class InfoController {

    @Value("${server.port}")
    private String name;
    @RequestMapping("/port")
    public String getValue(){
        return name;
    }

}
