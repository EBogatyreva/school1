package ru.hogwarts.school1.model;

public class Faculty {
    private Long id;
    private String name;
    private String color;

 /*   public Faculty(String name, String color) {
        this.name = name;
        this.color = color;
    }*/

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return getName()+getColor();
    }
}
