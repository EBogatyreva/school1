package ru.hogwarts.school1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import ru.hogwarts.school1.model.Student;
import ru.hogwarts.school1.repository.StudentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private StudentController studentController;

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private TestRestTemplate restTemplate;//как они связанны?!)
    // при добавлении (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) перестала выдаваться ошибка про бины

    private static final ObjectMapper om = new ObjectMapper();

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
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();

    }

    @Test
    void getStudent() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class))
                .isNotNull();
    }

    @Test
    void updateStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("L");
        student.setAge(17);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(om.writeValueAsString(student), headers);

        ResponseEntity<String> response = restTemplate.exchange("/student/update/1", HttpMethod.PUT, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(om.writeValueAsString(student), response.getBody(), false);

        verify(studentRepository, times(1)).save(any(Student.class));


    }

    @Test
    void deleteStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("L");
        student.setAge(14);

        Assertions
                .assertThat(this.restTemplate.exchange("/delete/1", HttpMethod.DELETE, null, Void.class))
                .isNotNull();


    }

    @Test
    void findByAgeBetween() {
        Student student = new Student();
        student.setId(1L);
        student.setName("L");
        student.setAge(14);

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/user", String.class))
                .isNotNull();
    }

}
