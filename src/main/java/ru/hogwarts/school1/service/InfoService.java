package ru.hogwarts.school1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
@Service
@Profile("development")
public class InfoService {

    @Value("${server.port}")
    private String name;


}
