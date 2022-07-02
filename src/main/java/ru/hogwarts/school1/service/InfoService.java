package ru.hogwarts.school1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("development")
public class InfoService {

    @Value("${test1.name}")
    private String name;

    @Value("${test1.code}")
    private Integer code;

    @Value("${test1.number}")
    private Integer number;

}
