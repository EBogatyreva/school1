package ru.hogwarts.school1.model;

import javax.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @OneToOne
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //__________________________
    public Faculty getFaculty() {
        return faculty;
    }

    public Faculty setFaculty(Faculty faculty) {
        return this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "{" + getId() + "Имя " + getName() + " возраст " + getAge() + " факультет " + getFaculty() + '}';
    }
}
