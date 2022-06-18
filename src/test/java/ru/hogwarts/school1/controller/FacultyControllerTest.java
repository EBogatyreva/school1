package ru.hogwarts.school1.controller;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school1.model.Faculty;
import ru.hogwarts.school1.model.Student;
import ru.hogwarts.school1.repository.FacultyRepository;
import ru.hogwarts.school1.service.FacultyService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class FacultyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;

    @Test
    public void createfacultyTest() throws Exception {
        Long id = 1L;
        String name = "S";
        String color = "red";

        JSONObject userObject = new JSONObject();
        userObject.put(name, color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty") //send
                        .content(userObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect((ResultMatcher) jsonPath("$.id").value(id))
                .andExpect((ResultMatcher) jsonPath("$.name").value(name))
                .andExpect((ResultMatcher) jsonPath("$.color").value(color));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + id) //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect((ResultMatcher) jsonPath("$.id").value(id))
                .andExpect((ResultMatcher) jsonPath("$.name").value(name))
                .andExpect((ResultMatcher) jsonPath("$.color").value(color));
    }

    @Test
    public void getfacultyTest() throws Exception {
        Long id = 1L;
        String name = "S";
        String color = "red";

        JSONObject userObject = new JSONObject();
        userObject.put(name, color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + id) //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect((ResultMatcher) jsonPath("$.id").value(id))
                .andExpect((ResultMatcher) jsonPath("$.name").value(name))
                .andExpect((ResultMatcher) jsonPath("$.color").value(color));
    }

    @Test
    public void deletefacultyTest() throws Exception {
        Long id = 1L;
        String name = "S";
        String color = "red";

        JSONObject userObject = new JSONObject();
        userObject.put(name, color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.deleteById(any(Long.class))).thenReturn(faculty);
        //как тут правильно вернуть то? ))
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + id) //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect((ResultMatcher) jsonPath("$.id").value(id))
                .andExpect((ResultMatcher) jsonPath("$.name").value(name))
                .andExpect((ResultMatcher) jsonPath("$.color").value(color));
    }

    @Test
    public void getFacultyByNameOrColorTest() throws Exception {
        Long id = 1L;
        String name = "S";
        String color = "red";

        JSONObject userObject = new JSONObject();
        userObject.put(name, color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.findFacultyByNameOrColor(name,color)).thenReturn((List<Faculty>) faculty);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + id) //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect((ResultMatcher) jsonPath("$.id").value(id))
                .andExpect((ResultMatcher) jsonPath("$.name").value(name))
                .andExpect((ResultMatcher) jsonPath("$.color").value(color));
    }

    @Test
    public void getStudentsByFacultyTest() throws Exception {
        Long id = 1L;
        String name = "S";
        String color = "red";

        Student student = new Student();
        student.setId(1L);
        student.setName("L");
        student.setAge(14);

        JSONObject userObject = new JSONObject();
        userObject.put(name, color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);
        faculty.setStudents(1L);

        when(facultyRepository.findById(id)).thenReturn(Optional.of(faculty));
        //как мне вызвать студента привязанного к факультету
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + id) //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect((ResultMatcher) jsonPath("$.id").value(id))
                .andExpect((ResultMatcher) jsonPath("$.name").value(name))
                .andExpect((ResultMatcher) jsonPath("$.color").value(color));
    }

}
