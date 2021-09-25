package com.java.connection.entity;

import com.java.connection.dao.ClassDao;
import com.java.connection.dao.StudentDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class Student {
    private int ID;
    private String last_name;
    private String first_name;
    private String city;
    private double average_score;
    private char gender;
    private LocalDate dob;
    private Class studentClass;

    public Student(int ID, String first_name, String last_name, String city, double average_score, char gender, LocalDate dob) {
        this.ID = ID;
        this.last_name = last_name;
        this.first_name = first_name;
        this.city = city;
        this.average_score = average_score;
        this.gender = gender;
        this.dob = dob;

    }


    public Student(ResultSet resultSet) throws SQLException {
        this.ID = resultSet.getInt(1);
        this.last_name = resultSet.getString(2);
        this.first_name = resultSet.getString(3);
        this.city = resultSet.getString(4);
        this.average_score = resultSet.getDouble(5);
        this.gender = resultSet.getString(6).charAt(0);
        String dobString=resultSet.getString(7);
        LocalDate dob=LocalDate.parse(dobString,DateTimeFormatter.ISO_LOCAL_DATE);
        this.dob=dob;
        int class_id = resultSet.getInt(8);
        ClassDao classDao=new ClassDao();
        Class studentClass=classDao.findByID(class_id);
        this.studentClass=studentClass;

    }

    public Student() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return ID == student.ID && Double.compare(student.average_score, average_score) == 0 && gender == student.gender && first_name.equals(student.first_name) && last_name.equals(student.last_name) && city.equals(student.city) && dob.equals(student.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, first_name, last_name, city, average_score, gender, dob);
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID=" + ID +
                ", last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", city='" + city + '\'' +
                ", average_score=" + average_score +
                ", gender=" + gender +
                ", dob=" + dob +
                ", studentClass=" + studentClass +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) throws SQLException {
        this.ID=ID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getAverage_score() {
        return average_score;
    }

    public boolean setAverage_score(float average_score) {
        if (average_score>10 || average_score<0){
            System.err.println("Score is [0-10]");
            return false;
        }
        else {
            this.average_score = average_score;
            return true;
        }
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setAverage_score(double average_score) {
        this.average_score = average_score;
    }

    public Class getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(Class studentClass) {
        this.studentClass = studentClass;
    }
}
