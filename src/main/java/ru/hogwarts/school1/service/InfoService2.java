package ru.hogwarts.school1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("production")
public class InfoService2 {

    @Value("${test2.name}")
    private String name;

    @Value("${test2.code}")
    private Integer code;

    @Value("${test2.number}")
    private Integer number;
}
