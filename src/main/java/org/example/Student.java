package org.example;

public class Student {
    public Student(String name, String stuId) {
        this.name = name;
        this.stuId = stuId;
    }

    private String name;
    private String stuId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", stuId='" + stuId + '\'' +
                '}';
    }
}
