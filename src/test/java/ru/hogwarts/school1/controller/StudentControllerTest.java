package ru.hogwarts.school1.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import ru.hogwarts.school1.model.Student;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;//как они связанны?!)
    // при добавлении (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) перестала выдаваться ошибка про бины

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    void testDefoltNessage() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class))
                .isEqualTo("Web API is working");
    }

    @Test
    void createStudent() throws Exception {//исходя из видео-урока, если мы
        // при простом тестировании создаем сущность она тоже записывается в Бд, а здесь такого не произошло!
        //почему?

        Student student = new Student();
        student.setId(1L);
        student.setName("L");
        student.setAge(14);

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/user", student, String.class))
                .isNotNull();

    }

    @Test
    void getStudent() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/user", String.class))
                .isNotNull();
    }

    @Test
    void updateStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setName("L");
        student.setAge(17);

        HttpEntity<String> entity = new HttpEntity<String>("parameters");

        Assertions
                .assertThat(this.restTemplate.exchange("http://localhost:", HttpMethod.GET, entity, String.class))
                .isNotNull();
    }

    @Test
    void deleteStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("L");
        student.setAge(14);

        Assertions
                .assertThat(this.restTemplate.exchange("/user", HttpMethod.DELETE, null, Void.class))
                .isNotNull();


    }

        @Test
        void findByAgeBetween () {
            Student student = new Student();
            student.setId(1L);
            student.setName("L");
            student.setAge(14);

            Student student1 = new Student();
            student.setId(2L);
            student.setName("S");
            student.setAge(16);

            Assertions
                    .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/user", String.class))
                    .isNotNull();

        }

        @Test
        void findFaculty () {
            Student student = new Student();
            student.setId(1L);
            student.setName("L");
            student.setAge(14);


        }
    }
